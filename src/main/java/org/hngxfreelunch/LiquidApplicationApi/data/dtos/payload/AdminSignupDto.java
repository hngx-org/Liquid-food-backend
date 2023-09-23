package org.hngxfreelunch.LiquidApplicationApi.data.dtos.payload;

import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminSignupDto {

    @NotBlank(message = "full name is required")
    private String fullName;

    @NotBlank(message = "email is required")
    private String organizationEmail;

    @NotBlank(message = "organization name is required")
    private String organizationName;

    @NotBlank(message = "password is required")
    private String password;

    @NotBlank(message = "confirm password is required")
    private String confirmPassword;

    @NotNull(message = "please up")
    private MultipartFile photo;

}
