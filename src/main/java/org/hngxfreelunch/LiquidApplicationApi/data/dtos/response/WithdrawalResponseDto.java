package org.hngxfreelunch.LiquidApplicationApi.data.dtos.response;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WithdrawalResponseDto {

    private ApiResponseDto apiResponseDto;
    private String id;
    private String userId;
    private String status;
    private int amount;
    private String createdAt;
}
