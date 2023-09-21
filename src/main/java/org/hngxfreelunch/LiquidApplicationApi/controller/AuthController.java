package org.hngxfreelunch.LiquidApplicationApi.controller;

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


    @PostMapping("/staff/signup")
    public ResponseEntity<?>  signUpForStaff(@Valid @RequestBody UserSignupDto userSignupDto){

        return ResponseEntity.ok(userService.createUser(userSignupDto));
    }

}

