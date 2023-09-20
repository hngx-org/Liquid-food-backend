package org.hngxfreelunch.LiquidApplicationApi.data.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationRegistrationDto {

    @NotBlank(message = "Input your Organization name")
    private String organizationName;

    private BigInteger lunch_price;
}
