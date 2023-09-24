package org.hngxfreelunch.LiquidApplicationApi.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.payload.OrganizationInviteDto;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.payload.OrganizationRegistrationDto;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.payload.SendLunchCreditToAllStaffRequest;
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

    @Operation(summary = "Invite new staff to this organization",
            description = "Returns an ApiResponse Response entity")
    @PostMapping("invite")
    public ResponseEntity<?> inviteStaffToOrganization(@Valid @RequestBody OrganizationInviteDto organizationInviteDto){
        return ResponseEntity.ok(organizationService.sendOrganizationInviteToStaff(organizationInviteDto));
    }

    @Operation(summary = "Send Lunch credits to a particular member of this organization",
            description = "Returns an ApiResponse Response entity")
    @PostMapping("lunch")
    public ResponseEntity<?> sendLunchCredit(@Valid @RequestBody OrganizationInviteDto organizationInviteDto){
        return ResponseEntity.ok(organizationService.sendLunchCredit(organizationInviteDto));
    }

    @Operation(summary = "Send Lunch credits to all members of an organization",
            description = "Returns an ApiResponse Response entity")
    @PostMapping("lunch/staffs")
    public ResponseEntity<?>
    sendLunchCreditToAllStaffInOrganization(@Valid @RequestBody SendLunchCreditToAllStaffRequest sendLunchCreditToAllStaffRequest){
        return ResponseEntity.ok(organizationService.sendLunchCreditToAllStaffs(sendLunchCreditToAllStaffRequest));
    }

}
