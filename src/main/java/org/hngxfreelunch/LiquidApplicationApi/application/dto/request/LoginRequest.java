package org.hngxfreelunch.LiquidApplicationApi.application.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class LoginRequest {
    @Email(message = "Must be a valid email")
    @NotBlank(message = "Email must not be blank")
    private String email;
    @NotBlank(message = "Password must not be blank")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[!@#$%^&*+=/,.;:|?])(?=.*[0-9]).{8,}$",
            message = "Password must be minimum 8 characters, and must contain a number and special character")
    private String password;
}
