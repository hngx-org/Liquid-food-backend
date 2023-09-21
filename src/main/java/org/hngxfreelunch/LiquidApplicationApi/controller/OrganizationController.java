package org.hngxfreelunch.LiquidApplicationApi.controller;

import jakarta.validation.Valid;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.payload.OrganizationInviteDto;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.payload.OrganizationRegistrationDto;
import org.hngxfreelunch.LiquidApplicationApi.services.organization.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize(
            "hasAuthority('ADMIN')"
    )
    @PostMapping("invite")
    public ResponseEntity<?> inviteStaffToOrganization(@Valid @RequestBody OrganizationInviteDto organizationInviteDto){
        return ResponseEntity.ok(organizationService.sendOrganizationInviteToStaff(organizationInviteDto));
    }

    @PreAuthorize(
            "hasAuthority('ADMIN')"
    )
    @PostMapping("lunch")
    public ResponseEntity<?> sendLunchCredit(@Valid @RequestBody OrganizationInviteDto organizationInviteDto){
        return ResponseEntity.ok(organizationService.sendLunchCredit(organizationInviteDto));
    }

}
