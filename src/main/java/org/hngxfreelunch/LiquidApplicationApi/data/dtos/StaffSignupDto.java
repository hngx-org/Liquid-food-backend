package org.hngxfreelunch.LiquidApplicationApi.data.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StaffSignupDto {

    @NotBlank(message = "email is required")
    private String email;
    @NotBlank(message = "password is required")
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
