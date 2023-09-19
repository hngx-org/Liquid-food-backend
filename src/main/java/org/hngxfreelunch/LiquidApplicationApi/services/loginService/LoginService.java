package org.hngxfreelunch.LiquidApplicationApi.services.loginService;


import org.hngxfreelunch.LiquidApplicationApi.data.dtos.login.LoginRequest;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.login.LoginResponse;
import org.springframework.stereotype.Service;

@Service
public interface LoginService {
    LoginResponse loginUser(LoginRequest loginRequest);

}
