package org.hngxfreelunch.LiquidApplicationApi.data.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hngxfreelunch.LiquidApplicationApi.data.entities.User;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LunchResponseDto {

    private String profilePicture;
    private UsersResponseDto sender;
    private UsersResponseDto receiver;
    private Boolean redeemed;
    private LocalDateTime createdAt;
    private String role;
    private Integer quantity;

}
