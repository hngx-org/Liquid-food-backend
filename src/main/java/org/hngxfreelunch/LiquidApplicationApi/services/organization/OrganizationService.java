package org.hngxfreelunch.LiquidApplicationApi.services.organization;


import org.hngxfreelunch.LiquidApplicationApi.data.dtos.CreateOrganizationRequest;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.CreateOrganizationResponse;
import org.hngxfreelunch.LiquidApplicationApi.data.entities.Organization;

public interface OrganizationService {
    // TODO: CREATE ORGANIZATION
    // TODO: UPDATE ORGANIZATION DETAILS
CreateOrganizationResponse createOrganization(CreateOrganizationRequest organization);
    // TODO: ADD STAFF
    // TODO: REMOVE STAFF
    // TODO: DELETE ORGANIZATION
}
