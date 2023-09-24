package org.hngxfreelunch.LiquidApplicationApi.services.user;

import lombok.RequiredArgsConstructor;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.UserDto;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.payload.AdminSignupDto;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.payload.BankRequestDto;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.payload.OrganizationRegistrationDto;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.payload.UserSignupDto;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.response.ApiResponseDto;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.response.BankResponseDto;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.response.UsersResponseDto;
import org.hngxfreelunch.LiquidApplicationApi.data.entities.Organizations;
import org.hngxfreelunch.LiquidApplicationApi.data.entities.User;
import org.hngxfreelunch.LiquidApplicationApi.data.repositories.UserRepository;
import org.hngxfreelunch.LiquidApplicationApi.exceptions.FreeLunchException;
import org.hngxfreelunch.LiquidApplicationApi.exceptions.InvalidCredentials;
import org.hngxfreelunch.LiquidApplicationApi.exceptions.UserNotFoundException;
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
import java.util.Objects;
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
    public ApiResponseDto<UserDto> createUser(UserSignupDto signUpRequest) {
        // verify the invite otp
        Organizations inviteResponse = organizationService
                .verifyOrganizationInvite(signUpRequest.getOtpToken(), signUpRequest.getEmail());
        if(inviteResponse == null){
            throw new FreeLunchException("Token Expired.");
        }
        // check if user has already signed up
        boolean isExists = checkIfStaffAlreadyExists(signUpRequest.getEmail());
        if (isExists){
            return new ApiResponseDto<>("Staff already exists", HttpStatus.BAD_REQUEST.value(),null);
        }
        // create new user
        User staff = new User();
        staff.setFirstName(signUpRequest.getFirstName());
        staff.setLastName(signUpRequest.getLastName());
        staff.setEmail(signUpRequest.getEmail());
        staff.setPhone(signUpRequest.getPhoneNumber());
        staff.setPasswordHash(passwordEncoder.encode(signUpRequest.getPassword())); // hash password
        staff.setIsAdmin(false);
        return getApiResponseDto(inviteResponse, staff);
    }

    private ApiResponseDto<UserDto> getApiResponseDto(Organizations foundOrganizations, User staff) {
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
        return new ApiResponseDto<>("Staff created successfully", HttpStatus.CREATED.value(), userDto);
    }

    private boolean checkIfStaffAlreadyExists(String email) {

        return userRepository.findByEmail(email).isPresent();
    }

    @Override
    public ApiResponseDto<?> getUserByEmail(String email) {
        Optional<User> userByName = userRepository.findByEmail(email);
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
            return new ApiResponseDto<>("Successfully returned user profile", HttpStatus.OK.value(), userDto);
        }
        throw  new UserNotFoundException("Staff with name " + email + " not found");
    }


    @Override
    public ApiResponseDto<?> getUsersByName(String name) {
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

            return new ApiResponseDto<>("Successfully returned users profile", HttpStatus.OK.value(), usersResponseDtoList);
        }
        throw  new UserNotFoundException("Staff with name " + name + " not found");
    }

    @Override
    public ApiResponseDto<UserDto> addBankDetails(BankRequestDto bankRequestDto) {
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

        return new ApiResponseDto<>("Successfully added user bank details", HttpStatus.OK.value(), userDto);
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
    public ApiResponseDto<?> createAdmin(AdminSignupDto signUpRequest) {
        boolean isExists = checkIfStaffAlreadyExists(signUpRequest.getEmail());
        if (isExists){
            return new ApiResponseDto<>("Staff already exists", HttpStatus.BAD_REQUEST.value(), null);
        }

        Organizations organization = organizationService.createOrganization(new OrganizationRegistrationDto(signUpRequest.getOrganizationName(), BigInteger.valueOf(1000)));

        // create new user
        User staff = new User();
        String[] names= signUpRequest.getFullName().split(" ");
        if(names.length < 2) {
            throw new FreeLunchException("Please enter your first name and last name");
        }
        staff.setFirstName(names[0]);
        staff.setLastName(names[1] == null ? "" : names[1]);
        staff.setEmail(signUpRequest.getEmail());
        staff.setPhone(signUpRequest.getPhoneNumber());
        staff.setPasswordHash(passwordEncoder.encode(signUpRequest.getPassword())); // hash password
        staff.setIsAdmin(true);
        return getApiResponseDto(organization, staff);
    }

    @Override
    public ApiResponseDto<BankResponseDto> getUserBankDetails() {
        User user = userUtils.getLoggedInUser();
        if (Objects.isNull(user)){
            return new ApiResponseDto<>( "No bank details",HttpStatus.BAD_REQUEST.value(), null);
        }

        BankResponseDto bankResponseDto = BankResponseDto.builder()
                .bankCode(user.getBankCode())
                .bankName(user.getBankName())
                .bankNumber(user.getBankNumber())
                .email(user.getEmail())
                .user_id(user.getId().toString())
                .org_id(user.getOrganizations().getId().toString())
                .build();

        return new ApiResponseDto<>("successfully got bank details",HttpStatus.OK.value(),bankResponseDto);    }
}
