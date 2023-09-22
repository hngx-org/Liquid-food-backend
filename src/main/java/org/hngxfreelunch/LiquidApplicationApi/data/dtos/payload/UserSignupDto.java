package org.hngxfreelunch.LiquidApplicationApi.data.dtos.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSignupDto {

    @NotBlank(message = "email is required")
    private String email;
    @NotBlank(message = "password is required")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[!@#$%^&*+=/,.;:|?])(?=.*[0-9]).{8,}$",
            message = "Password must be minimum 8 characters, and must contain a number and special character")
    private String password;
    @NotBlank(message = "otp is required")
    private String otpToken;
    @NotBlank(message = "firstname is required")
    private String firstName;
    @NotBlank(message = "lastname is required")
    private String lastName;
    @NotBlank(message = "phone number is required")
    private String phoneNumber;
}
