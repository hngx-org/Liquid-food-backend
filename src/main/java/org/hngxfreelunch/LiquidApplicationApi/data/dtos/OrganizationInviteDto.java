package org.hngxfreelunch.LiquidApplicationApi.data.dtos;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationInviteDto {

    @NotBlank(message = "email is required")
    private String email;
}
