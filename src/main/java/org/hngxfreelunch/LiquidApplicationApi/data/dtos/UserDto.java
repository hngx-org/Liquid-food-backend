package org.hngxfreelunch.LiquidApplicationApi.data.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    @NotNull(message = "Enter first name")
    private  String firstName;
    private String lastName;
    private String profilePicture;
    @NotNull(message = "Enter your email")
    private String email;
    private String phoneNumber;
    @NotNull(message = "Enter a password")
    private String passwordHash;
    private String refreshToken;
    private String bankNumber;
    private String bankCode;
    private String bankName;
    private String organizationName;
    private Long id;
    private Boolean isAdmin;

}
