package org.hngxfreelunch.LiquidApplicationApi.services.loginService;

import org.hngxfreelunch.LiquidApplicationApi.data.dtos.payload.LoginRequestDto;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.response.ApiResponseDto;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.response.LoginResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface LoginService {

    ApiResponseDto<LoginResponseDto> loginUser(LoginRequestDto loginRequest);

    ApiResponseDto<LoginResponseDto> refreshUserToken(String refreshToken);
}
