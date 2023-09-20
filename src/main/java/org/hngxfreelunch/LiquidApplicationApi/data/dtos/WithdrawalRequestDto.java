package org.hngxfreelunch.LiquidApplicationApi.data.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WithdrawalRequestDto {

    @NotBlank(message = "bank number is required")
    private String bankNumber;
    @NotBlank(message = "bank code is required")
    private String bankCode;
    @NotBlank(message = "bank name is required")
    private String bankName;
    @NotBlank(message = "amount is required")
    private BigInteger amount;
}
