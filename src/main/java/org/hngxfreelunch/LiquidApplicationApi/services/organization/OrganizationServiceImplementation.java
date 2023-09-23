package org.hngxfreelunch.LiquidApplicationApi.services.organization;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpStatus;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.payload.OrganizationInviteDto;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.payload.OrganizationRegistrationDto;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.payload.SendLunchCreditToAllStaffRequest;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.response.ApiResponse;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.response.ApiResponseDto;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.response.UsersResponseDto;
import org.hngxfreelunch.LiquidApplicationApi.data.entities.Organizations;
import org.hngxfreelunch.LiquidApplicationApi.data.entities.OrganizationInvites;
import org.hngxfreelunch.LiquidApplicationApi.data.entities.User;
import org.hngxfreelunch.LiquidApplicationApi.data.repositories.OrganizationInvitesRepository;
import org.hngxfreelunch.LiquidApplicationApi.data.repositories.OrganizationRepository;
import org.hngxfreelunch.LiquidApplicationApi.data.repositories.UserRepository;
import org.hngxfreelunch.LiquidApplicationApi.exceptions.FreeLunchException;
import org.hngxfreelunch.LiquidApplicationApi.exceptions.InvalidCredentials;
import org.hngxfreelunch.LiquidApplicationApi.exceptions.OrganizationNotFoundException;
import org.hngxfreelunch.LiquidApplicationApi.exceptions.UserNotFoundException;
import org.hngxfreelunch.LiquidApplicationApi.services.email.EmailService;
import org.hngxfreelunch.LiquidApplicationApi.services.lunch.LunchService;
import org.hngxfreelunch.LiquidApplicationApi.utils.UserUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrganizationServiceImplementation implements OrganizationService {

    private final OrganizationRepository organizationRepository;
    private final OrganizationInvitesRepository organizationInvitesRepository;
    private final EmailService emailService;

    private final LunchService lunchService;
    private final UserUtils userUtils;
    private final UserRepository userRepository;

    @Value("${url_prefix}")
    private String url_prefix;

    @Override
    public Organizations createOrganization(OrganizationRegistrationDto request) {
        boolean isExists = organizationRepository.existsByName(request.getOrganizationName());
        if (isExists) {
            throw new FreeLunchException();
        }
        Organizations organizations = Organizations.builder()
                .name(request.getOrganizationName())
                .lunchPrice(1000.00)
                .currencyCode("NGN")
                .build();

        return organizationRepository.save(organizations);
    }

    @Override
    public ApiResponseDto sendOrganizationInviteToStaff(OrganizationInviteDto request) {
        String token = RandomStringUtils.randomNumeric(5);

        LocalDateTime expirationTime = LocalDateTime.now().plusHours(24);
        String subject = "Lunch Invite";
        String htmlContent =
                "We're delighted to invite you to a complimentary lunch as a token of our appreciation for your hard work and dedication.\n" +
                        "\n" +
                        "\nDate: " + LocalDate.now() +
                        "\nTime: " + LocalTime.now() +
                        "\n" +
                        "RSVP before " + expirationTime + " hours with this unique RSVP Token: " +
                        "<p><a href=\"" + url_prefix + "?token=?" + token + "\">Accept Invitation<a/>";

        emailService.sendEmail(request.getEmail(), subject, htmlContent);
        OrganizationInvites organizationInvites = new OrganizationInvites();
        organizationInvites.setToken(token);
        organizationInvites.setEmail(request.getEmail());
        organizationInvites.setTTL(expirationTime);
        organizationInvitesRepository.save(organizationInvites);
        organizationInvites.setOrganizations(organizationRepository.findById(request.getOrganizationId()).orElseThrow(OrganizationNotFoundException::new));
        organizationInvitesRepository.save(organizationInvites);
        return new ApiResponseDto("Success", HttpStatus.SC_OK,organizationInvites);
    }

    @Override
    public Organizations verifyOrganizationInvite(String token, String email) {
        OrganizationInvites organizationInvites = organizationInvitesRepository.findByToken(token).orElseThrow(() -> new InvalidCredentials("Token not valid"));
        if (!organizationInvites.getEmail().equals(email)) {
            throw new InvalidCredentials("OTP not valid for user");
        }
        LocalDateTime expirationTime = organizationInvites.getTTL();
        LocalDateTime currentTime = LocalDateTime.now();
        if (currentTime.isBefore(expirationTime)) {
            Organizations organizations = organizationInvites.getOrganizations();
            log.info(String.valueOf(organizations));
            String subject = "Lunch Invite";
            String htmlContent =
                    "We're delighted to invite you to a complimentary lunch as a token of our appreciation for your hard work and dedication.\n" +
                            "\n" +
                            "\nDate: " + LocalDate.now() +
                            "\nTime: " + LocalTime.now() +
                            "\n" +
                            "RSVP before " + expirationTime + " hours with this unique RSVP Token: " +
                            "<p><a href=\"" + url_prefix + "?token=?" + token + "\">Accept Invitation<a/>";
            emailService.sendEmail(organizationInvites.getEmail(), subject, htmlContent);
            organizationInvitesRepository.delete(organizationInvites);
            log.info(String.valueOf(organizations));
            return organizations;
        } else {
            throw new InvalidCredentials("OTP is expired");
        }
    }

    @Override
    public Organizations findById(Long id) {
        return organizationRepository.findById(id).orElseThrow(OrganizationNotFoundException::new);
    }

    @Override
    public ApiResponseDto sendLunchCreditToAllStaffs(SendLunchCreditToAllStaffRequest sendLunchCreditToAllStaffRequest) {
        User sender = userUtils.getLoggedInUser();
        if(!sender.getIsAdmin()) throw new InvalidCredentials("Can't make this request");
        return new ApiResponseDto<>("Sent Lunch credit to all staff in this Org", HttpStatus.SC_OK,
                lunchService.sendLunch(sendLunchCreditToAllStaffRequest.getNote(),sendLunchCreditToAllStaffRequest.getQuantity(), sender));
    }

    @Override
    public ApiResponseDto sendLunchCredit(OrganizationInviteDto request) {
        User foundUser = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new UserNotFoundException("User  with email address not found"));
        foundUser.setLunchCreditBalance(foundUser.getLunchCreditBalance().and(BigInteger.valueOf(4)));
        userRepository.save(foundUser);
        return new ApiResponseDto<>("SUCCESSFUL",HttpStatus.SC_OK,null);
    }

    @Override
    public ApiResponseDto getAllStaffInOrganization() {
        User loggedInUser = userUtils.getLoggedInUser();
        List<User> users = userRepository.findAllByOrganizations_Id(loggedInUser.getOrganizations().getId());
        List<UsersResponseDto> usersResponseDtoList = users.stream().map(this::mapToDto).toList();
        return new ApiResponseDto<>("All users in this Organization", HttpStatus.SC_OK,usersResponseDtoList);
    }

    private UsersResponseDto mapToDto(User user){
        UsersResponseDto usersResponseDto = new UsersResponseDto();
        usersResponseDto.setEmail(user.getEmail());
        usersResponseDto.setFullName(user.getFirstName() + " " + user.getLastName());
        usersResponseDto.setOrganizationName(user.getOrganizations().getName());
        return usersResponseDto;
    }

}
