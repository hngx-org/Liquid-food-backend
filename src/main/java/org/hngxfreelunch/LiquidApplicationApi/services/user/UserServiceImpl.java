package org.hngxfreelunch.LiquidApplicationApi.services.user;

import lombok.RequiredArgsConstructor;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.payload.BankRequestDto;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.payload.UserSignupDto;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.response.ApiResponseDto;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.response.UsersResponseDto;
import org.hngxfreelunch.LiquidApplicationApi.data.entities.User;
import org.hngxfreelunch.LiquidApplicationApi.data.repositories.UserRepository;
import org.hngxfreelunch.LiquidApplicationApi.exceptions.UserNotFoundException;
import org.hngxfreelunch.LiquidApplicationApi.services.organization.OrganizationService;
import org.hngxfreelunch.LiquidApplicationApi.utils.UserUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private UserRepository userRepository;
    private OrganizationService organizationService;
    private PasswordEncoder passwordEncoder;
    private UserUtils userUtils;

    @Override
    public ApiResponseDto createUser(UserSignupDto signUpRequest) {
        // verify the invite otp
        ApiResponseDto inviteResponse = organizationService
                .verifyOrganizationInvite(signUpRequest.getOtpToken());
        int status_code = inviteResponse.getStatusCode();
        if(status_code != 200){return inviteResponse;}
        // check if user has already signed up
        boolean isExists = checkIfStaffAlreadyExists(signUpRequest.getEmail());
        if (isExists){
            return new ApiResponseDto<>(null, "Staff already exists", HttpStatus.BAD_REQUEST.value());
        }
        // create new user
        User staff = new User();
        staff.setFirstName(signUpRequest.getFirstName());
        staff.setLastName(signUpRequest.getLastName());
        staff.setEmail(signUpRequest.getEmail());
        //staff.setRefresh_token(signUpRequest.getOtpToken());
        staff.setPhoneNumber(signUpRequest.getPhoneNumber());
        staff.setPasswordHash(passwordEncoder.encode(signUpRequest.getPassword())); // hash password
        User savedStaff = userRepository.save(staff);
        return new ApiResponseDto<>(null, "Staff created successfully", HttpStatus.CREATED.value());
    }

    private boolean checkIfStaffAlreadyExists(String email) {

        return userRepository.findByEmail(email).isPresent();
    }

    @Override
    public ApiResponseDto getUserByName(String firstName) {
        Optional<User> userByName = userRepository.findByFirstName(firstName);
        UsersResponseDto usersResponseDto = new UsersResponseDto();
        if (userByName.isPresent()){
            usersResponseDto.setEmail(userByName.get().getEmail());
            usersResponseDto.setFullName(userByName.get().getFirstName()
                    + " " + userByName.get().getLastName());
            usersResponseDto.setOrganizationName(userByName.get().getOrganization().getName());

            return new ApiResponseDto(usersResponseDto,"Successfully returned user profile", HttpStatus.OK.value());
        }
//        Optional<User> userByEmail = userRepository.findByEmail(email);
//        if (userByEmail.isPresent()){
//            return userByEmail.get();
//        }
        throw  new UserNotFoundException("Staff with name '" + firstName + "' not found");
    }

    @Override
    public ApiResponseDto addBankDetails(BankRequestDto bankRequestDto) {
        User loggedInUser = userUtils.getLoggedInUser();
        loggedInUser.setBankCode(bankRequestDto.getBankCode());
        loggedInUser.setBankName(bankRequestDto.getBankName());
        loggedInUser.setBankNumber(bankRequestDto.getBankNumber());
        loggedInUser.setBankRegion("NGN");
        userRepository.save(loggedInUser);

        UsersResponseDto usersResponseDto = new UsersResponseDto();
        usersResponseDto.setEmail(loggedInUser.getEmail());
        usersResponseDto.setFullName(loggedInUser.getFirstName() + " " + loggedInUser.getLastName());
        usersResponseDto.setOrganizationName(loggedInUser.getOrganization().getName());

        return new ApiResponseDto(usersResponseDto,"Successfully added user bank details", HttpStatus.OK.value());
    }
}
