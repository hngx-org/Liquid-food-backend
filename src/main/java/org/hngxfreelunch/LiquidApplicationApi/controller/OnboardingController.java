package org.hngxfreelunch.LiquidApplicationApi.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.GlobalResponse;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.login.LoginRequest;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.login.LoginResponse;
import org.hngxfreelunch.LiquidApplicationApi.services.loginService.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class OnboardingController {
    private final LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<GlobalResponse<LoginResponse>> loginStaff(@Valid @RequestBody LoginRequest request){
        GlobalResponse<LoginResponse> response = new GlobalResponse<>(loginService.loginUser(request),
                "User logged in successfully", 200);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
