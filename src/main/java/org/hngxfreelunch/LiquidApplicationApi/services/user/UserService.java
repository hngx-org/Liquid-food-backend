package org.hngxfreelunch.LiquidApplicationApi.services.user;


import org.hngxfreelunch.LiquidApplicationApi.data.dtos.UserDto;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.payload.AdminSignupDto;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.payload.BankRequestDto;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.payload.UserSignupDto;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.response.ApiResponseDto;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {
    // TODO: CREATE USER
    ApiResponseDto<?> createUser(UserSignupDto signUpRequest);

    // TODO: RETURN ALL USERS WITH THIS KEYWORD WHICH COULD BE A NAME OR EMAIL
    ApiResponseDto<?> searchByNameOrEmail(String keyword);


    // TODO: ADD BANK DETAILS
    ApiResponseDto<?> addBankDetails(BankRequestDto bankRequestDto);

    String uploadProfileImage(MultipartFile file);

    ApiResponseDto<?> createAdmin(AdminSignupDto adminSignupDto);

    ApiResponseDto<?> getUserBankDetails();

    ApiResponseDto<UserDto> getLoggedInUser();
}
