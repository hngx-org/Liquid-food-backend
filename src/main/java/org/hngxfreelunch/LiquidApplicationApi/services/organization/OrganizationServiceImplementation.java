package org.hngxfreelunch.LiquidApplicationApi.services.organization;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpStatus;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.payload.OrganizationInviteDto;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.payload.OrganizationRegistrationDto;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.response.ApiResponseDto;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.response.UsersResponseDto;
import org.hngxfreelunch.LiquidApplicationApi.data.entities.Organization;
import org.hngxfreelunch.LiquidApplicationApi.data.entities.OrganizationInvites;
import org.hngxfreelunch.LiquidApplicationApi.data.entities.User;
import org.hngxfreelunch.LiquidApplicationApi.data.repositories.OrganizationInvitesRepository;
import org.hngxfreelunch.LiquidApplicationApi.data.repositories.OrganizationRepository;
import org.hngxfreelunch.LiquidApplicationApi.services.email.EmailService;
import org.hngxfreelunch.LiquidApplicationApi.utils.UserUtils;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrganizationServiceImplementation implements OrganizationService {

    private OrganizationRepository organizationRepository;
    private OrganizationInvitesRepository organizationInvitesRepository;
    private EmailService emailService;
    private UserUtils userUtils;

    @Override
    public ApiResponseDto createOrganization(OrganizationRegistrationDto request) {
        boolean isExists = organizationRepository.existsByOrganizationName(request.getOrganizationName());
        if (isExists) {
            return new ApiResponseDto(null,"Organization name already exists", HttpStatus.SC_BAD_REQUEST);
        }
        Organization newOrganization = Organization.builder()
                .name(request.getOrganizationName())
                .lunch_price(BigInteger.valueOf(1000))
                .currency("NGN")
                .build();
        organizationRepository.save(newOrganization);

        return new ApiResponseDto(newOrganization,"Organization Created successfully", HttpStatus.SC_CREATED);

    }

    @Override
    public ApiResponseDto sendOrganizationInviteToStaff(OrganizationInviteDto request) {
        String token = RandomStringUtils.randomNumeric(5);

        LocalDateTime expirationTime = LocalDateTime.now().plusHours(24);
        int expirationTimeInHours = expirationTime.getHour();

        String subject = "Lunch Invite";
        String htmlContent =
                "We're delighted to invite you to a complimentary lunch as a token of our appreciation for your hard work and dedication.\n" +
                        "\n" +
                        "\nDate: " + LocalDate.now() +
                        "\nTime: " + LocalTime.now() +
                        "\n" +
                        "RSVP before " + expirationTime + " hours with this unique RSVP Token: " +
                        "<p><a href=\"www.google.com?token=?" + token + "\">Accept Invitation<a/>";

        emailService.sendEmail(request.getEmail(), subject, htmlContent);
        OrganizationInvites organizationInvites = new OrganizationInvites();
        organizationInvites.setToken(token);
        organizationInvites.setEmail(request.getEmail());
        organizationInvites.setTTL(expirationTime);
        organizationInvitesRepository.save(organizationInvites);
        return new ApiResponseDto(organizationInvites,"Success", HttpStatus.SC_OK);
    }

    @Override
    public ApiResponseDto verifyOrganizationInvite(String token) {
        OrganizationInvites organizationInvites = organizationInvitesRepository.findByToken(token);
        LocalDateTime expirationTime = organizationInvites.getTTL();
        LocalDateTime currentTime = LocalDateTime.now();
        if (currentTime.isAfter(expirationTime)) {
            String subject = "Lunch Invite";
            String htmlContent =
                    "We're delighted to invite you to a complimentary lunch as a token of our appreciation for your hard work and dedication.\n" +
                            "\n" +
                            "\nDate: " + LocalDate.now() +
                            "\nTime: " + LocalTime.now() +
                            "\n" +
                            "RSVP before " + expirationTime + " hours with this unique RSVP Token: " + token;

            emailService.sendEmail(organizationInvites.getEmail(), subject, htmlContent);
            return new ApiResponseDto(null,"Invitation has expired new invitation email sent successfully", HttpStatus.SC_OK);
        }

        return new ApiResponseDto(null,"Email successfully verified", HttpStatus.SC_OK);
    }

    @Override
    public ApiResponseDto sendLunchCredit(OrganizationInviteDto request) {
        return null;
    }

    @Override
    public ApiResponseDto getAllStaffInOrganization() {
        User loggedInUser = userUtils.getLoggedInUser();
        List<User> users = loggedInUser.getOrganization().getStaff();
        List<UsersResponseDto> usersResponseDtoList = users.stream().map(user -> mapToDto(user)).toList();
        return new ApiResponseDto<>(usersResponseDtoList,"All users in this Organization", HttpStatus.SC_OK);
    }

    private UsersResponseDto mapToDto(User user){
        UsersResponseDto usersResponseDto = new UsersResponseDto();
        usersResponseDto.setEmail(user.getEmail());
        usersResponseDto.setFullName(user.getFirstName() + " " + user.getLastName());
        usersResponseDto.setOrganizationName(user.getOrganization().getName());
        return usersResponseDto;
    }

}
