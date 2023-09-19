package org.hngxfreelunch.LiquidApplicationApi.application.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hngxfreelunch.LiquidApplicationApi.application.dto.request.LoginRequest;
import org.hngxfreelunch.LiquidApplicationApi.application.dto.response.LoginResponse;
import org.hngxfreelunch.LiquidApplicationApi.services.LoginService;
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
    public ResponseEntity<LoginResponse> loginStaff(@Valid @RequestBody LoginRequest request){
        return new ResponseEntity<>(loginService.loginUser(request), HttpStatus.OK);
    }
}
