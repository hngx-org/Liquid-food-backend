package org.hngxfreelunch.LiquidApplicationApi.controller;

import jakarta.validation.Valid;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.payload.OrganizationInviteDto;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.payload.OrganizationRegistrationDto;
import org.hngxfreelunch.LiquidApplicationApi.services.organization.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/organization/")
@CrossOrigin(origins = "*")
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;

    @PostMapping("create")
    public ResponseEntity<?> createOrganization(@Valid @RequestBody OrganizationRegistrationDto organizationRegistrationDto){
        return ResponseEntity.ok(organizationService.createOrganization(organizationRegistrationDto));
    }


    @PostMapping("invite")
    public ResponseEntity<?> inviteStaffToOrganization(@Valid @RequestBody OrganizationInviteDto organizationInviteDto){
        return ResponseEntity.ok(organizationService.sendOrganizationInviteToStaff(organizationInviteDto));
    }

    @PostMapping("lunch")
    public ResponseEntity<?> sendLunchCredit(@Valid @RequestBody OrganizationInviteDto organizationInviteDto){
        return ResponseEntity.ok(organizationService.sendLunchCredit(organizationInviteDto));
    }

}
