package org.hngxfreelunch.LiquidApplicationApi.data.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WithdrawalResponseDto {

    private ApiResponseDto apiResponseDto;
    private String id;
    private String userId;
    private String status;
    private BigInteger amount;
    private String createdAt;
}
