package org.hngxfreelunch.LiquidApplicationApi.services.user;

import lombok.RequiredArgsConstructor;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.UserDto;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.payload.AdminSignupDto;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.payload.BankRequestDto;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.payload.OrganizationRegistrationDto;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.payload.UserSignupDto;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.response.ApiResponseDto;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.response.UsersResponseDto;
import org.hngxfreelunch.LiquidApplicationApi.data.entities.Organizations;
import org.hngxfreelunch.LiquidApplicationApi.data.entities.User;
import org.hngxfreelunch.LiquidApplicationApi.data.repositories.OrganizationInvitesRepository;
import org.hngxfreelunch.LiquidApplicationApi.data.repositories.UserRepository;
import org.hngxfreelunch.LiquidApplicationApi.exceptions.InvalidCredentials;
import org.hngxfreelunch.LiquidApplicationApi.exceptions.UserNotFoundException;
import org.hngxfreelunch.LiquidApplicationApi.security.JwtService;
import org.hngxfreelunch.LiquidApplicationApi.services.cloud.CloudService;
import org.hngxfreelunch.LiquidApplicationApi.services.organization.OrganizationService;
import org.hngxfreelunch.LiquidApplicationApi.utils.UserUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final OrganizationService organizationService;
    private final PasswordEncoder passwordEncoder;
    private final UserUtils userUtils;
    private final CloudService cloudService;

    @Override
    public ApiResponseDto createUser(UserSignupDto signUpRequest) {
        // verify the invite otp
        Organizations inviteResponse = organizationService
                .verifyOrganizationInvite(signUpRequest.getOtpToken(), signUpRequest.getEmail());
        if(inviteResponse == null){throw new InvalidCredentials("Organization not found");}

        // check if user has already signed up
        boolean isExists = checkIfStaffAlreadyExists(signUpRequest.getEmail());
        if (isExists){
            return new ApiResponseDto<>(null, "Staff already exists", HttpStatus.BAD_REQUEST.value());
        }

        Organizations foundOrganizations = organizationService.findById(inviteResponse.getId());
        // create new user
        User staff = new User();
        staff.setFirstName(signUpRequest.getFirstName());
        staff.setLastName(signUpRequest.getLastName());
        staff.setEmail(signUpRequest.getEmail());
        staff.setPhone(signUpRequest.getPhoneNumber());
        staff.setPasswordHash(passwordEncoder.encode(signUpRequest.getPassword())); // hash password
        staff.setIsAdmin(false);
        return getApiResponseDto(foundOrganizations, staff);
    }

    private ApiResponseDto getApiResponseDto(Organizations foundOrganizations, User staff) {
        staff.setOrganizations(foundOrganizations);
        staff.setLunchCreditBalance(BigInteger.ZERO);
        staff.setCurrencyCode("NGN");
        staff.setBankRegion("NGN");
        User savedUser = userRepository.save(staff);

        UserDto userDto = UserDto.builder()
                .id(savedUser.getId())
                .firstName(savedUser.getFirstName())
                .lastName(savedUser.getLastName())
                .organizationName(savedUser.getOrganizations().getName())
                .email(savedUser.getEmail())
                .refreshToken(savedUser.getRefreshToken())
                .phoneNumber(savedUser.getPhone())
                .profilePicture(savedUser.getProfilePic())
                .isAdmin(savedUser.getIsAdmin())
                .build();
        return new ApiResponseDto<>(userDto, "Staff created successfully", HttpStatus.CREATED.value());
    }

    private boolean checkIfStaffAlreadyExists(String email) {

        return userRepository.findByEmail(email).isPresent();
    }

    @Override
    public ApiResponseDto getUserByEmail(String email) {
        Optional<User> userByName = userRepository.findByEmail(email);
        UsersResponseDto usersResponseDto = new UsersResponseDto();
        if (userByName.isPresent()){
            User user = userByName.get();
            UserDto userDto = UserDto.builder()
                    .firstName(user.getFirstName())
                    .lastName(user.getLastName())
                    .email(user.getEmail())
                    .bankName(user.getBankName())
                    .bankCode(user.getBankCode())
                    .bankNumber(user.getBankNumber())
                    .profilePicture(user.getProfilePic())
                    .refreshToken(user.getRefreshToken())
                    .organizationName(user.getOrganizations().getName())
                    .build();
            return new ApiResponseDto(userDto,"Successfully returned user profile", HttpStatus.OK.value());
        }
        throw  new UserNotFoundException("Staff with name " + email + " not found");
    }


    @Override
    public ApiResponseDto getUsersByName(String name) {
        List<User> usersByName = userRepository.findByFirstNameContainsIgnoreCaseOrLastNameContainsIgnoreCase(name, name);
        List<UsersResponseDto> usersResponseDtoList = new ArrayList<>();
        if (!usersByName.isEmpty()){
            for (User user: usersByName) {
                UsersResponseDto usersResponseDto = new UsersResponseDto();
                usersResponseDto.setEmail(user.getEmail());
                usersResponseDto.setFullName(user.getFirstName()
                        + " " + user.getLastName());
                usersResponseDto.setOrganizationName(user.getOrganizations().getName());
                usersResponseDtoList.add(usersResponseDto);
            }

            return new ApiResponseDto(usersResponseDtoList,"Successfully returned users profile", HttpStatus.OK.value());
        }
        throw  new UserNotFoundException("Staff with name " + name + " not found");
    }

    @Override
    public ApiResponseDto addBankDetails(BankRequestDto bankRequestDto) {
        User user = userUtils.getLoggedInUser();
        user.setBankCode(bankRequestDto.getBankCode());
        user.setBankName(bankRequestDto.getBankName());
        user.setBankNumber(bankRequestDto.getBankNumber());
        user.setBankRegion("NGN");
        userRepository.save(user);

        userRepository.save(user);

        UserDto userDto = UserDto.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .bankName(user.getBankName())
                .bankCode(user.getBankCode())
                .bankNumber(user.getBankNumber())
                .profilePicture(user.getProfilePic())
                .organizationName(user.getOrganizations().getName())
                .build();

        return new ApiResponseDto(userDto,"Successfully added user bank details", HttpStatus.OK.value());
    }

    @Override
    public String uploadProfileImage(MultipartFile profileImage) {
        User foundUser = userUtils.getLoggedInUser();
        String imageUrl = cloudService.upload(profileImage);
        foundUser.setProfilePic(imageUrl);
        userRepository.save(foundUser);
        return imageUrl;
    }

    @Override
    public ApiResponseDto createAdmin(AdminSignupDto signUpRequest) {
        boolean isExists = checkIfStaffAlreadyExists(signUpRequest.getEmail());
        if (isExists){
            return new ApiResponseDto<>(null, "Staff already exists", HttpStatus.BAD_REQUEST.value());
        }

        ApiResponseDto<Organizations> organization = organizationService.createOrganization(new OrganizationRegistrationDto(signUpRequest.getOrganizationName(), BigInteger.valueOf(1000)));
        Organizations foundOrganizations = organizationService.findById(organization.getData().getId());

        // create new user
        User staff = new User();
        String[] names = signUpRequest.getFullName().split(" ");
        staff.setFirstName(names[0]);
        staff.setLastName(names[1]);
        staff.setEmail(signUpRequest.getEmail());
        staff.setPhone(signUpRequest.getPhoneNumber());
        staff.setPasswordHash(passwordEncoder.encode(signUpRequest.getPassword())); // hash password
        staff.setIsAdmin(true);
        return getApiResponseDto(foundOrganizations, staff);
    }
}
