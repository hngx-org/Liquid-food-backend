package org.hngxfreelunch.LiquidApplicationApi.services.password;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.payload.ChangePasswordDto;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.payload.PasswordResetDto;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.response.ApiResponseDto;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.response.ResetResponse;
import org.hngxfreelunch.LiquidApplicationApi.data.entities.OrganizationInvites;
import org.hngxfreelunch.LiquidApplicationApi.data.entities.User;
import org.hngxfreelunch.LiquidApplicationApi.data.repositories.OrganizationInvitesRepository;
import org.hngxfreelunch.LiquidApplicationApi.data.repositories.UserRepository;
import org.hngxfreelunch.LiquidApplicationApi.exceptions.FreeLunchException;
import org.hngxfreelunch.LiquidApplicationApi.services.email.EmailEvent;
import org.hngxfreelunch.LiquidApplicationApi.utils.UserUtils;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
@RequiredArgsConstructor
public class PasswordServiceImpl implements PasswordService{
    private final UserRepository userRepository;
    private final OrganizationInvitesRepository invitesRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserUtils userUtils;
    private final ApplicationEventPublisher publisher;

    @Override
    public ApiResponseDto<ResetResponse> forgotPassword(String email){
        User user = userRepository.findByEmail(email)
                .orElseThrow(()-> new FreeLunchException("User is not registered"));
        String token = RandomStringUtils.randomNumeric(5);

        LocalDateTime expirationTime = LocalDateTime.now().plusHours(24);
        String subject = "Password Reset";
        String from = "liquid.freelunch@gmail.com";
        String htmlContent =
                "<p>Please Use the OTP below to reset your password</p> " +
                        "<p>Date: " + LocalDate.now()+"</p>" +
                        "<p>Time: " + LocalTime.now()+"</p>" +
                        "\n" +
                        "<p>RSVP before " + expirationTime + " hours with this unique RSVP Token: </p>" +
                        "<p style='font-size:30px'>" +token+"</p>";

        publisher.publishEvent(new EmailEvent(user.getEmail(), subject, from, htmlContent));
        OrganizationInvites theOrganizationInvite;
        if(invitesRepository.existsByEmail(user.getEmail())){
            OrganizationInvites organizationInvites = invitesRepository.findByEmail(user.getEmail());
            organizationInvites.setToken(token);
            organizationInvites.setTTL(LocalDateTime.now().plusMinutes(10));
            theOrganizationInvite = invitesRepository.save(organizationInvites);
        }else{
            theOrganizationInvite = invitesRepository.save(OrganizationInvites.builder()
                            .email(user.getEmail())
                            .token(token)
                            .TTL(LocalDateTime.now().plusMinutes(10))
                            .organizations(user.getOrganizations())
                            .updatedAt(LocalDateTime.now())
                    .build());
        }
        return new ApiResponseDto<>("Request Processed Successfully", 200, ResetResponse.builder()
                .id(theOrganizationInvite.getId())
                .token(theOrganizationInvite.getToken())
                .email(theOrganizationInvite.getEmail())
                .build());
    }

    @Override
    public ApiResponseDto<String> resetPassword(PasswordResetDto passwordResetDto){
        OrganizationInvites organizationInvites = invitesRepository.findByTokenAndEmail
                (passwordResetDto.getOtp(),passwordResetDto.getEmail())
                .orElseThrow(()-> new FreeLunchException("Invalid reset Credentials"));
        User user = userRepository.findByEmail(organizationInvites.getEmail())
                .orElseThrow(()-> new FreeLunchException("User not found"));
        user.setPasswordHash(passwordEncoder.encode(passwordResetDto.getNewPassword()));
        userRepository.save(user);
        return new ApiResponseDto<>("Request Processed Successfully", 200,
                "Password Changed Successfully, Proceed to Login");
    }

    @Override
    public ApiResponseDto<String> changePassword(ChangePasswordDto changePasswordDto){
        User user = userUtils.getLoggedInUser();
        if(!user.getPasswordHash().equals(passwordEncoder.encode(changePasswordDto.getOldPassword()))){
            throw new FreeLunchException("Password is incorrect");
        }
        user.setPasswordHash(passwordEncoder.encode(changePasswordDto.getNewPassword()));
        userRepository.save(user);
        return new ApiResponseDto<>("Request Processed Successfully", 200,
                "Password Changed Successfully");
    }
}
