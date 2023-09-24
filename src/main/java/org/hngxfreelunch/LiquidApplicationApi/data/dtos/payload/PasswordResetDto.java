package org.hngxfreelunch.LiquidApplicationApi.data.dtos.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PasswordResetDto {
    @NotBlank(message = "Please input your reset OTP")
    private String otp;
    @NotBlank(message = "Please input your Email")
    @Email(message = "Must be a valid email")
    private String email;
    @NotBlank(message = "Please input your new password")
    private String newPassword;
}
