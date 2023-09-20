package org.hngxfreelunch.LiquidApplicationApi.services.staff;

import lombok.RequiredArgsConstructor;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.UserDto;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.payload.BankRequestDto;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.response.ApiResponseDto;
import org.hngxfreelunch.LiquidApplicationApi.data.entities.User;
import org.hngxfreelunch.LiquidApplicationApi.data.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImplementation implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public ApiResponseDto addBankDetails(Long id,BankRequestDto bankRequestDto) {

        User existingUser = userRepository.findById(id).orElse(null);

        if (existingUser != null){
            existingUser.setBank_number(bankRequestDto.getBankNumber());
            existingUser.setBank_name(bankRequestDto.getBankName());
            existingUser.setBank_code(bankRequestDto.getBankCode());

            userRepository.save(existingUser);
            return new ApiResponseDto(existingUser, "success created bank account", 200);
        }else {
            return new ApiResponseDto(null, "user not found", 400);
        }
    }
}
