package org.hngxfreelunch.LiquidApplicationApi.services.password;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.payload.PasswordResetDto;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.response.ApiResponseDto;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.response.ResetResponse;
import org.hngxfreelunch.LiquidApplicationApi.data.entities.PasswordReset;
import org.hngxfreelunch.LiquidApplicationApi.data.entities.User;
import org.hngxfreelunch.LiquidApplicationApi.data.repositories.PasswordResetRepository;
import org.hngxfreelunch.LiquidApplicationApi.data.repositories.UserRepository;
import org.hngxfreelunch.LiquidApplicationApi.exceptions.FreeLunchException;
import org.hngxfreelunch.LiquidApplicationApi.services.email.EmailService;
import org.hngxfreelunch.LiquidApplicationApi.utils.DateUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
@RequiredArgsConstructor
public class PasswordServiceImpl implements PasswordService{
    private final EmailService emailService;
    private final UserRepository userRepository;
    private final PasswordResetRepository resetRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ApiResponseDto<ResetResponse> forgotPassword(String email){
        User user = userRepository.findByEmail(email)
                .orElseThrow(()-> new FreeLunchException("User is not registered"));
        String token = RandomStringUtils.randomNumeric(5);

        LocalDateTime expirationTime = LocalDateTime.now().plusHours(24);
        String subject = "Password Reset";
        String htmlContent =
                "Please Use the OTP below to reset your password\n" +
                        "\n" +
                        "\nDate: " + LocalDate.now() +
                        "\nTime: " + LocalTime.now() +
                        "\n" +
                        "RSVP before " + expirationTime + " hours with this unique RSVP Token: " +
                        "<p style='font-size:30px'>" +token+"</p>";

        emailService.sendEmail(user.getEmail(), subject, htmlContent);
        PasswordReset passwordReset;
        if(resetRepository.existsByEmail(user.getEmail())){
            PasswordReset thePasswordReset = resetRepository.findByEmail(user.getEmail());
            thePasswordReset.setToken(token);
            thePasswordReset.setResetAt(DateUtils.saveDate(LocalDateTime.now()));
            thePasswordReset.setResetBy(user.getFirstName() +" "+user.getLastName());
            thePasswordReset.setTTL(LocalDateTime.now().plusMinutes(10));
            passwordReset = resetRepository.save(thePasswordReset);
        }else{
            passwordReset = resetRepository.save(PasswordReset.builder()
                    .token(token)
                    .email(user.getEmail())
                    .resetAt(DateUtils.saveDate(LocalDateTime.now()))
                    .TTL(LocalDateTime.now().plusMinutes(10))
                    .resetBy(user.getFirstName() +" "+user.getLastName())
                    .build());
        }

        return new ApiResponseDto<>("Request Processed Successfully", 200, ResetResponse.builder()
                .id(passwordReset.getId())
                .token(passwordReset.getToken())
                .email(passwordReset.getEmail())
                .build());
    }

    @Override
    public ApiResponseDto<String> resetPassword(PasswordResetDto passwordResetDto){
        PasswordReset passwordReset = resetRepository.findByTokenAndEmail
                (passwordResetDto.getOtp(),passwordResetDto.getEmail())
                .orElseThrow(()-> new FreeLunchException("Invalid reset Credentials"));
        User user = userRepository.findByEmail(passwordReset.getEmail())
                .orElseThrow(()-> new FreeLunchException("User not found"));
        user.setPasswordHash(passwordEncoder.encode(passwordResetDto.getNewPassword()));
        userRepository.save(user);
        return new ApiResponseDto<>("Request Processed Successfully", 200, "Password Changed Successfully, Proceed to Login");
    }
}
