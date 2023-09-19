package org.hngxfreelunch.LiquidApplicationApi.data.dtos;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationDtos {

    private @NotBlank(message = "Input your Organization name") String name;
    private Integer lunch_price;
    private @NotNull(message = "Please input your currency") String currency;
}
