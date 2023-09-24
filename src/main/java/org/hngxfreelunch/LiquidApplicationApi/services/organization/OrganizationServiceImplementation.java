package org.hngxfreelunch.LiquidApplicationApi.services.organization;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpStatus;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.UserDto;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.payload.OrganizationInviteDto;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.payload.OrganizationRegistrationDto;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.response.ApiResponseDto;
import org.hngxfreelunch.LiquidApplicationApi.data.entities.Organizations;
import org.hngxfreelunch.LiquidApplicationApi.data.entities.OrganizationInvites;
import org.hngxfreelunch.LiquidApplicationApi.data.entities.User;
import org.hngxfreelunch.LiquidApplicationApi.data.repositories.OrganizationInvitesRepository;
import org.hngxfreelunch.LiquidApplicationApi.data.repositories.OrganizationRepository;
import org.hngxfreelunch.LiquidApplicationApi.data.repositories.UserRepository;
import org.hngxfreelunch.LiquidApplicationApi.exceptions.FreeLunchException;
import org.hngxfreelunch.LiquidApplicationApi.exceptions.InvalidCredentials;
import org.hngxfreelunch.LiquidApplicationApi.services.email.EmailEvent;
import org.hngxfreelunch.LiquidApplicationApi.utils.DateUtils;
import org.hngxfreelunch.LiquidApplicationApi.utils.UserUtils;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

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
//    private final EmailService emailService;
    private final ApplicationEventPublisher publisher;

    private final UserUtils userUtils;
    private final UserRepository userRepository;

//    @Value("${url_prefix}")
//    private String url_prefix;

    @Override
    public Organizations createOrganization(OrganizationRegistrationDto request) {
        if (organizationRepository.existsByName(request.getOrganizationName())) {
            throw new FreeLunchException("Organization Already Exists");
        }
        Organizations organizations = Organizations.builder()
                .name(request.getOrganizationName())
                .lunchPrice(1000.00)
                .currencyCode("NGN")
                .build();

        return organizationRepository.save(organizations);
    }

    @Override
    public ApiResponseDto<?> sendOrganizationInviteToStaff(OrganizationInviteDto request) {
        User sender = userUtils.getLoggedInUser();
        if(!sender.getIsAdmin()){
            throw new InvalidCredentials("Can't make this request");
        }
        String token = RandomStringUtils.randomNumeric(5);

        LocalDateTime expirationTime = LocalDateTime.now().plusHours(24);
        String subject = "Lunch Invite";
        String from = "liquid.freelunch@gmail.com";
        String htmlContent =
                "<p>We're delighted to invite you to a complimentary lunch as a token of our appreciation for your hard work and dedication.</p> " +
                        "<p>Download the FREE LUNCH app and register as a staff using this OTP below</p> " +
                        "<p>Date: " + LocalDate.now()+"</p>" +
                        "<p>Time: " + LocalTime.now()+"</p>" +
                        "\n" +
                        "<p>RSVP before " + DateUtils.saveDate(expirationTime) + " hours with this unique RSVP Token: </p>" +
                        "<p style='font-size:30px'>" +token+"</p>";
        publisher.publishEvent(new EmailEvent(request.getEmail(),subject,from,htmlContent));
        OrganizationInvites organizationInvites = organizationInvitesRepository.save(OrganizationInvites.builder()
                .email(request.getEmail())
                .token(token)
                .TTL(expirationTime)
                .updatedAt(LocalDateTime.now())
                .organizations(sender.getOrganizations())
                .build());
        return new ApiResponseDto<>("Success", HttpStatus.SC_OK,organizationInvites);
    }

    @Override
    public Organizations verifyOrganizationInvite(String token, String email) {
        OrganizationInvites organizationInvites = organizationInvitesRepository.findByTokenAndEmail(token, email)
                .orElseThrow(()-> new FreeLunchException("Invalid Credentials"));
        if(organizationInvites.getTTL().isAfter(LocalDateTime.now())){
            return organizationInvites.getOrganizations();
        }else{
            organizationInvitesRepository.delete(organizationInvites);
            return null;
        }
    }

    @Override
    public ApiResponseDto<?> getAllStaffInOrganization() {
        User loggedInUser = userUtils.getLoggedInUser();
        List<UserDto> users = userRepository.findAllByOrganizations(loggedInUser.getOrganizations())
                .stream().map(userUtils::mapUserToDto).toList();
        return new ApiResponseDto<>("All users in this Organization", HttpStatus.SC_OK,users);
    }

}
