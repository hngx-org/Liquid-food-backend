package org.hngxfreelunch.LiquidApplicationApi.services.staff;

import lombok.RequiredArgsConstructor;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.UserDto;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.payload.BankRequestDto;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.response.ApiResponseDto;
import org.hngxfreelunch.LiquidApplicationApi.data.entities.User;
import org.hngxfreelunch.LiquidApplicationApi.data.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImplementation implements UserService {

    private UserRepository userRepository;

    @Override
    public ApiResponseDto addBankDetails(BankRequestDto bankRequestDto) {

        User user = new User();
        user.setBank_number(bankRequestDto.getBankNumber());
        user.setBank_code(bankRequestDto.getBankCode());
        user.setBank_name(bankRequestDto.getBankName());

        userRepository.save(user);
        return new ApiResponseDto(user, "successfully created bank account", 200);
    }

    @Override
    public ApiResponseDto getAllUsers() {

        List<User> users = userRepository.findAll();

        return new ApiResponseDto(users, "all users", 200);
    }
}
