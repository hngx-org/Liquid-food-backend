package org.hngxfreelunch.LiquidApplicationApi.services.user;


import org.hngxfreelunch.LiquidApplicationApi.data.dtos.payload.BankRequestDto;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.payload.UserSignupDto;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.response.ApiResponseDto;

public interface UserService {
    // TODO: CREATE USER
    ApiResponseDto createUser(UserSignupDto signUpRequest);
    // TODO: GET STAFF DETAILS BY NAME
    ApiResponseDto getUserByName(String name);
    // TODO: ADD BANK DETAILS
    ApiResponseDto addBankDetails(BankRequestDto bankRequestDto);
}