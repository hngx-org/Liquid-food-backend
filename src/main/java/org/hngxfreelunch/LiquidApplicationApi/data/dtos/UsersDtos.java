package org.hngxfreelunch.LiquidApplicationApi.data.dtos;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsersDtos {

    private @NotNull(message = "Enter first name") String firstName;
    private String lastName;
    private String profile_picture;
    private @NotNull(message = "Enter your email") String email;
    private int phoneNumber;
    private @NotNull(message = "Enter a password") String password_hash;
    private String refresh_token;
    private String bank_number;
    private String bank_code;
    private String bank_name;

}
