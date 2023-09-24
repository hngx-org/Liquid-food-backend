package org.hngxfreelunch.LiquidApplicationApi.data.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hngxfreelunch.LiquidApplicationApi.data.entities.Organizations;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private String refreshToken;

    private LocalDateTime updatedAt;

    private LocalDateTime createdAt;

    private String bankNumber;

    private String bankCode;

    private String bankName;

    private String bankRegion;

    private String currencyCode;

    private String currency;

    private Boolean isAdmin;

    private BigInteger lunchCreditBalance;

    private Organizations organizations;

    private String profilePic;

}
