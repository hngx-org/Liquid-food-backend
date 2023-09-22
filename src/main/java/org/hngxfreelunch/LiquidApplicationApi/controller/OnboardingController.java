package org.hngxfreelunch.LiquidApplicationApi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class OnboardingController {
    private final LoginService loginService;

    @Operation(summary = "User attempts to login")
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody
            @Parameter(required = true, description = "Emailand password")
                                           LoginRequestDto request){
        return ResponseEntity.ok(new ApiResponseDto<>(loginService.loginUser(request),
                "User logged in successfully", 200));
    }
    @PostMapping("/refresh-token")
    @Operation(summary = "User wants to update the refresh token")
    public ResponseEntity<?> loginUser(@RequestHeader("refresh-token")
            @Parameter(description = "Refresh token to be activated")
                                           String refreshToken){
        return ResponseEntity.ok(new ApiResponseDto<>(loginService.refreshUserToken(refreshToken),
                "User logged in successfully", 200));
    }
}
