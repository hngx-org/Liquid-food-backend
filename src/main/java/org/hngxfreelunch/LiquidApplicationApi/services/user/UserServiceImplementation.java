package org.hngxfreelunch.LiquidApplicationApi.services.user;

import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.apache.http.protocol.HTTP;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.payload.StaffSignupDto;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.payload.UpdateStaffRequest;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.response.ApiResponseDto;
import org.hngxfreelunch.LiquidApplicationApi.data.entities.User;
import org.hngxfreelunch.LiquidApplicationApi.data.repositories.UserRepository;
import org.hngxfreelunch.LiquidApplicationApi.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImplementation implements UserService {
    private UserRepository userRepository;

    @Override
    public ApiResponseDto<?> createStaff(StaffSignupDto signUpRequest) {
        boolean isExists = checkIfStaffAlreadyExists(signUpRequest.getEmail());
        if (isExists){
            return new ApiResponseDto<>(null, "Staff already exists", HttpStatus.BAD_REQUEST.value());
        }
        User staff = new User();
                staff.setFirst_name(signUpRequest.getFirstName());
                staff.setLast_name(signUpRequest.getLastName());
                staff.setEmail(signUpRequest.getEmail());
                staff.setRefresh_token(signUpRequest.getOtpToken());
                staff.setPhonenumber(signUpRequest.getPhoneNumber());
                staff.setPassword_hash(signUpRequest.getPassword());
                User savedStaff = userRepository.save(staff);
                return new ApiResponseDto<>(null, "Staff created successfully", HttpStatus.OK.value());
    }

    private boolean checkIfStaffAlreadyExists(String email) {

        return userRepository.findByEmail(email).isPresent();
    }


    @Override
    public User getStaffByNameOrEmail(String name, String email) {
        Optional<User> userByName = userRepository.findByName(name);
        if (userByName.isPresent()){
            return userByName.get();
        }
        Optional<User> userByEmail = userRepository.findByEmail(email);
        if (userByEmail.isPresent()){
            return userByEmail.get();
        }
        throw  new UserNotFoundException("Staff not found");
    }

}
