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

    private UsersResponseDto sender;
    private UsersResponseDto receiver;
    private LocalDateTime createdAt;
    private Integer quantity;
    private String note;
}
