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
public class StaffSIgnupDto {

    @NotBlank(message = "email is required")
    private String email;
    @NotBlank(message = "password is required")
    private String password;
    @NotBlank(message = "otp is required")
    private String otpToken;
    @NotBlank(message = "name is required")
    private String firstName;
    private String lastName;
    private String phoneNumber;
}
