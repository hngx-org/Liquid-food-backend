package org.hngxfreelunch.LiquidApplicationApi.services.organization;


import org.hngxfreelunch.LiquidApplicationApi.data.dtos.payload.OrganizationInviteDto;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.payload.OrganizationRegistrationDto;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.payload.SendLunchCreditToAllStaffRequest;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.response.ApiResponseDto;
import org.hngxfreelunch.LiquidApplicationApi.data.entities.Organizations;

public interface OrganizationService {
    // TODO: CREATE ORGANIZATION
    Organizations createOrganization(OrganizationRegistrationDto request);

    // TODO: ADD STAFF
    ApiResponseDto sendOrganizationInviteToStaff(OrganizationInviteDto request);
    // TODO: VERIFY STAFF

    // TODO: SEND LUNCH CREDIT TO STAFF
    ApiResponseDto sendLunchCredit(OrganizationInviteDto request);

    // TODO: GET ALL STAFF IN ORGANIZATION
    ApiResponseDto getAllStaffInOrganization();

    Organizations verifyOrganizationInvite(String otpToken, String email);

    Organizations findById(Long id);

    ApiResponseDto sendLunchCreditToAllStaffs(SendLunchCreditToAllStaffRequest sendLunchCreditToAllStaffRequest);
}
