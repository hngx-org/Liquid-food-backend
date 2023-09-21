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

import java.util.ArrayList;
import java.util.List;
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
        staff.setFirst_name(signUpRequest.getFirstName());
        staff.setLast_name(signUpRequest.getLastName());
        staff.setEmail(signUpRequest.getEmail());
        //staff.setRefresh_token(signUpRequest.getOtpToken());
        staff.setPhonenumber(signUpRequest.getPhoneNumber());
        staff.setPassword_hash(passwordEncoder.encode(signUpRequest.getPassword())); // hash password
        User savedStaff = userRepository.save(staff);
        return new ApiResponseDto<>(null, "Staff created successfully", HttpStatus.CREATED.value());
    }

    private boolean checkIfStaffAlreadyExists(String email) {

        return userRepository.findByEmail(email).isPresent();
    }

    @Override
    public ApiResponseDto getUserByName(String name) {
        Optional<User> userByName = userRepository.findByName(name);
        UsersResponseDto usersResponseDto = new UsersResponseDto();
        if (userByName.isPresent()){
            usersResponseDto.setEmail(userByName.get().getEmail());
            usersResponseDto.setFull_name(userByName.get().getFirst_name()
                    + " " + userByName.get().getLast_name());
            usersResponseDto.setOrganization_name(userByName.get().getOrganization().getName());

            return new ApiResponseDto(usersResponseDto,"Successfully returned user profile", HttpStatus.OK.value());
        }

        throw  new UserNotFoundException("Staff with name '" + name + "' not found");
    }


    @Override
    public ApiResponseDto getUsersByName(String name) {
        List<User> usersByName = userRepository.findByFirstNameOrLastName(name);
        List<UsersResponseDto> usersResponseDtoList = new ArrayList<>();
        if (!usersByName.isEmpty()){
            for (User user: usersByName) {
                UsersResponseDto usersResponseDto = new UsersResponseDto();
                usersResponseDto.setEmail(user.getEmail());
                usersResponseDto.setFull_name(user.getFirst_name()
                        + " " + user.getLast_name());
                usersResponseDto.setOrganization_name(user.getOrganization().getName());
                usersResponseDtoList.add(usersResponseDto);
            }

            return new ApiResponseDto(usersResponseDtoList,"Successfully returned users profile", HttpStatus.OK.value());
        }
        throw  new UserNotFoundException("Staff with name '" + name + "' not found");
    }

    @Override
    public ApiResponseDto addBankDetails(BankRequestDto bankRequestDto) {
        User loggedInUser = userUtils.getLoggedInUser();
        loggedInUser.setBank_code(bankRequestDto.getBankCode());
        loggedInUser.setBank_name(bankRequestDto.getBankName());
        loggedInUser.setBank_number(bankRequestDto.getBankNumber());
        loggedInUser.setBank_region("NGN");

        userRepository.save(loggedInUser);

        UsersResponseDto usersResponseDto = new UsersResponseDto();
        usersResponseDto.setEmail(loggedInUser.getEmail());
        usersResponseDto.setFull_name(loggedInUser.getFirst_name() + " " + loggedInUser.getLast_name());
        usersResponseDto.setOrganization_name(loggedInUser.getOrganization().getName());

        return new ApiResponseDto(usersResponseDto,"Successfully added user bank details", HttpStatus.OK.value());
    }
}
