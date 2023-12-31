package org.hngxfreelunch.LiquidApplicationApi.data.dtos.payload;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankRequestDto {

    @NotBlank(message = "bank number is required")
    private String bankNumber;
    @NotBlank(message = "bank code is required")
    private String bankCode;
    @NotBlank(message = "bank name is required")
    private String bankName;
}
