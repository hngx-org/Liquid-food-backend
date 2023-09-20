package org.hngxfreelunch.LiquidApplicationApi.services.organization;

import lombok.RequiredArgsConstructor;
import org.apache.http.HttpStatus;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.CreateOrganizationRequest;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.CreateOrganizationResponse;
import org.hngxfreelunch.LiquidApplicationApi.data.entities.Organization;
import org.hngxfreelunch.LiquidApplicationApi.data.repositories.OrganizationRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrganizationServiceImplementation implements OrganizationService {

    private OrganizationRepository organizationRepository;

    @Override
    public CreateOrganizationResponse createOrganization(CreateOrganizationRequest request) {
        boolean isExists = organizationRepository.existsByOrganizationName(request.getOrganizationName());
        if (isExists) {
            throw new CustomException("Organization name already exists");
        }
        Organization newOrganization = Organization.builder()
                .name(request.getOrganizationName())
                .build();
        Organization savedOrganization = organizationRepository.save(newOrganization);

        return CreateOrganizationResponse.builder()
                .responseCode(String.valueOf(HttpStatus.SC_CREATED))
                .responseMessage("Successful")
                .organizationData(Organization.builder()
                        .id(savedOrganization.getId())
                        .name(savedOrganization.getName())
                        .build())
                .build();
    }

}
