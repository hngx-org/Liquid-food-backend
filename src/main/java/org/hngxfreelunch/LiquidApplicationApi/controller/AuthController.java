package org.hngxfreelunch.LiquidApplicationApi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.payload.AdminSignupDto;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.payload.UserSignupDto;
import org.hngxfreelunch.LiquidApplicationApi.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private UserService userService;


    @Operation(summary = "Staff member tries to sign up with token")
    @PostMapping("/staff/signup")
    public ResponseEntity<?>  signUpForStaff(@Valid @RequestBody @Parameter(required = true, description = "The staff member's details along with the token") UserSignupDto userSignupDto) {
        return ResponseEntity.ok(userService.createUser(userSignupDto));
    }

}

