<<<<<<< HEAD
package org.hngxfreelunch.LiquidApplicationApi.services.organization;


public interface OrganizationService {
    // TODO: CREATE ORGANIZATION
    // TODO: ADD STAFF
    // TODO: REMOVE STAFF
    // TODO: UPDATE ORGANIZATION DETAILS
    // TODO: DELETE ORGANIZATION
}
=======
package org.hngxfreelunch.LiquidApplicationApi.services.organization;


import org.hngxfreelunch.LiquidApplicationApi.data.dtos.payload.OrganizationInviteDto;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.payload.OrganizationRegistrationDto;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.response.ApiResponseDto;

public interface OrganizationService {
    // TODO: CREATE ORGANIZATION
    ApiResponseDto createOrganization(OrganizationRegistrationDto request);

    // TODO: ADD STAFF
    ApiResponseDto sendOrganizationInviteToStaff(OrganizationInviteDto request);
    // TODO: VERIFY STAFF
    ApiResponseDto verifyOrganizationInvite(String token);
    // TODO: SEND LUNCH CREDIT TO STAFF
    ApiResponseDto sendLunchCredit(OrganizationInviteDto request);

    // TODO: GET ALL STAFF IN ORGANIZATION
    ApiResponseDto getAllStaffInOrganization();
}
>>>>>>> 37617686d864be3d5717d47a2fb029fe19dd3361
