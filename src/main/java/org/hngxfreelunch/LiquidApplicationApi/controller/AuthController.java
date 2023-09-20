package org.hngxfreelunch.LiquidApplicationApi.controller;

import jakarta.validation.Valid;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.payload.*;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.response.ApiResponse;
import org.hngxfreelunch.LiquidApplicationApi.data.entities.OrganizationInvites;
import org.hngxfreelunch.LiquidApplicationApi.services.organization.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private OrganizationService organizationService;


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
        return ResponseEntity.ok(new ApiResponse(organizationService.createOrganization(organizationRegistrationDto), true));
    }
    @PostMapping("/organization/invite")
    public ResponseEntity<ApiResponse> inviteStaffToOrganization(@Valid @RequestBody OrganizationInviteDto organizationInviteDto){
        return ResponseEntity.ok(new ApiResponse(organizationService.sendOrganizationInviteToStaff(organizationInviteDto), true));
    }
}

