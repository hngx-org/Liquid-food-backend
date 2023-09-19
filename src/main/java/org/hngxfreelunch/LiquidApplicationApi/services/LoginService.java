package org.hngxfreelunch.LiquidApplicationApi.services;

import org.hngxfreelunch.LiquidApplicationApi.application.dto.request.LoginRequest;
import org.hngxfreelunch.LiquidApplicationApi.application.dto.response.LoginResponse;
import org.springframework.stereotype.Service;

@Service
public interface LoginService {
    LoginResponse loginUser(LoginRequest loginRequest);
}
