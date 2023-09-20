package org.hngxfreelunch.LiquidApplicationApi.services.loginService;

import org.hngxfreelunch.LiquidApplicationApi.data.dtos.payload.LoginRequestDto;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.response.LoginResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface LoginService {

    LoginResponseDto loginUser(LoginRequestDto loginRequest);

    LoginResponseDto refreshUserToken(String refreshToken);
}
