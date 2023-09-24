package org.hngxfreelunch.LiquidApplicationApi.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.payload.OrganizationInviteDto;
import org.hngxfreelunch.LiquidApplicationApi.services.organization.OrganizationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/organization/")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class OrganizationController {

    private final OrganizationService organizationService;

    @Operation(summary = "Invite new staff to this organization",
            description = "Returns an ApiResponse Response entity")
    @PostMapping("invite")
    public ResponseEntity<?> inviteStaffToOrganization(@Valid @RequestBody OrganizationInviteDto organizationInviteDto){
        return ResponseEntity.ok(organizationService.sendOrganizationInviteToStaff(organizationInviteDto));
    }

}
