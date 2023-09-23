package org.hngxfreelunch.LiquidApplicationApi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.payload.AdminSignupDto;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.payload.LoginRequestDto;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.payload.PasswordResetDto;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.payload.UserSignupDto;
import org.hngxfreelunch.LiquidApplicationApi.services.loginService.LoginService;
import org.hngxfreelunch.LiquidApplicationApi.services.password.PasswordService;
import org.hngxfreelunch.LiquidApplicationApi.services.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    private final LoginService loginService;

    private final PasswordService passwordService;

    @Operation(summary = "User attempts to login")
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody
                                       @Parameter(required = true, description = "Emailand password")
                                       LoginRequestDto request){
        return ResponseEntity.ok(loginService.loginUser(request));
    }
    @PostMapping("/refresh-token")
    @Operation(summary = "User wants to update the refresh token")
    public ResponseEntity<?> loginUser(@RequestHeader("refresh-token")
                                       @Parameter(description = "Refresh token to be activated")
                                       String refreshToken){
        return ResponseEntity.ok(loginService.refreshUserToken(refreshToken));
    }


    @Operation(summary = "Staff member tries to sign up with token")
    @PostMapping("/staff/signup")
    public ResponseEntity<?>  signUpForStaff(@Valid @RequestBody @Parameter(required = true, description = "The staff member's details along with the token") UserSignupDto userSignupDto) {
        return ResponseEntity.ok(userService.createUser(userSignupDto));
    }

    @Operation(summary = "Staff member tries to sign up as admin and create organization")
    @PostMapping("/admin/signup")
    public ResponseEntity<?>  signUpForAdmin(@Valid @RequestBody @Parameter(required = true, description = "An admin tries to signup for the organization") AdminSignupDto adminSignupDto) {
        return ResponseEntity.ok(userService.createAdmin(adminSignupDto));
    }
    @Operation(summary = "Member tries to send a request to reset password")
    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestParam("email") String email){
        return ResponseEntity.ok(passwordService.forgotPassword(email));
    }
    @Operation(summary = "Member tries to reset password")
    @PatchMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@Valid @RequestBody PasswordResetDto passwordResetDto){
        return ResponseEntity.ok(passwordService.resetPassword(passwordResetDto));
    }

}
