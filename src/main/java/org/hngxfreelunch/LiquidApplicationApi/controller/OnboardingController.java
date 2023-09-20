package org.hngxfreelunch.LiquidApplicationApi.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.payload.LoginRequestDto;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.response.ApiResponseDto;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.response.LoginResponseDto;
import org.hngxfreelunch.LiquidApplicationApi.services.loginService.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class OnboardingController {
    private final LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponseDto<LoginResponseDto>> loginUser(@Valid @RequestBody LoginRequestDto request){
        return new ResponseEntity<>(loginService.loginUser(request), HttpStatus.OK);
    }
    @PostMapping("/refresh-token")
    public ResponseEntity<ApiResponseDto<LoginResponseDto>> loginUser(@RequestHeader("Refresh-Token") String refreshToken){
        return new ResponseEntity<>(loginService.refreshUserToken(refreshToken), HttpStatus.OK);
    }
}
