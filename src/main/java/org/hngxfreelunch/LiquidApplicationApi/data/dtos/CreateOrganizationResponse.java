package org.hngxfreelunch.LiquidApplicationApi.data.dtos;

import lombok.Builder;
import lombok.Data;
import org.hngxfreelunch.LiquidApplicationApi.data.entities.Organization;

@Data
@Builder
public class CreateOrganizationResponse {
    private String responseCode;
    private String responseMessage;
    private Organization organizationData;
}
