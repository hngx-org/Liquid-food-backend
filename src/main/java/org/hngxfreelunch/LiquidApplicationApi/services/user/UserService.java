package org.hngxfreelunch.LiquidApplicationApi.services.user;


import org.hngxfreelunch.LiquidApplicationApi.data.dtos.payload.AdminSignupDto;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.payload.BankRequestDto;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.payload.UserSignupDto;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.response.ApiResponseDto;
import org.hngxfreelunch.LiquidApplicationApi.utils.UserUtils;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {
    // TODO: CREATE USER
    ApiResponseDto createUser(UserSignupDto signUpRequest);
    // TODO: GET STAFF DETAILS BY NAME
    ApiResponseDto getUserByEmail(String email);

    // TODO: RETURN ALL USERS WITH THIS NAME
    ApiResponseDto getUsersByName(String name);

    // TODO: ADD BANK DETAILS
    ApiResponseDto addBankDetails(BankRequestDto bankRequestDto);

    String uploadProfileImage(MultipartFile file);

    ApiResponseDto createAdmin(AdminSignupDto adminSignupDto);

    ApiResponseDto getUserBankDetails(UserUtils userUtils);
}
