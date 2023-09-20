package org.hngxfreelunch.LiquidApplicationApi.data.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsersDtos {
    @NotNull(message = "Enter first name")
    private  String firstName;
    private String lastName;
    private String profile_picture;
    @NotNull(message = "Enter your email")
    private String email;
    private int phoneNumber;
    @NotNull(message = "Enter a password")
    private String password_hash;
    private String refresh_token;
    private String bank_number;
    private String bank_code;
    private String bank_name;

}
