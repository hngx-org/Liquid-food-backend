package org.hngxfreelunch.LiquidApplicationApi.controller;

import jakarta.validation.Valid;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.payload.AdminSignupDto;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.payload.LoginRequestDto;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.payload.OrganizationRegistrationDto;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.payload.UserSignupDto;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.response.ApiResponse;
import org.hngxfreelunch.LiquidApplicationApi.data.entities.OrganizationInvites;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {


    @PostMapping("login")
    public ResponseEntity<ApiResponse> login(@Valid @RequestBody LoginRequestDto loginRequestDto){
        return ResponseEntity.ok(new ApiResponse(null, true));
    }

    @PostMapping("/staff/signup")
    public ResponseEntity<ApiResponse>  signUpForStaff(@Valid @RequestBody UserSignupDto userSignupDto){
        return ResponseEntity.ok(new ApiResponse(null, true));
    }

    @PostMapping("/admin/signup")
    public ResponseEntity<ApiResponse> signUpForAdmin(@Valid @RequestBody AdminSignupDto adminSignupDto){
        return ResponseEntity.ok(new ApiResponse(null, true));
    }
    @PostMapping("/organization/create")
    public ResponseEntity<ApiResponse> createOrganization(@Valid @RequestBody OrganizationRegistrationDto organizationRegistrationDto){
        return ResponseEntity.ok(new ApiResponse(null, true));
    }
    @PostMapping("/organization/invite")
    public ResponseEntity<ApiResponse> inviteStaffToOrganization(@Valid @RequestBody OrganizationInvites organizationInvites){
        return ResponseEntity.ok(new ApiResponse(null, true));
    }
}

