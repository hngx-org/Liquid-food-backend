package org.hngxfreelunch.LiquidApplicationApi.data.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateOrganizationRequest {
    public String organizationName;
}
