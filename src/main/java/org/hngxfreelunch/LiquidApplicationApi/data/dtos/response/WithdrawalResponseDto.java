package org.hngxfreelunch.LiquidApplicationApi.data.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hngxfreelunch.LiquidApplicationApi.data.entities.Status;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WithdrawalResponseDto {

    private Long id;
    private Long userId;
    private Status status;
    private BigInteger amount;
    private LocalDateTime createdAt;

}
