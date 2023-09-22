package org.hngxfreelunch.LiquidApplicationApi.data.dtos.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminSignupDto {

    @NotBlank(message = "email is required")
    private String email;
    @NotBlank(message = "password is required")
    private String password;
    @NotBlank(message = "fullName is required")
    private String fullName;
    @NotBlank(message = "phone number is required")
    private String phoneNumber;
    @NotBlank(message = "organization name is required")
    private String organizationName;

}
