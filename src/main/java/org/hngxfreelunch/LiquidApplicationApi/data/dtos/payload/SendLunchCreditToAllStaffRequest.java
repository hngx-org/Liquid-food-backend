package org.hngxfreelunch.LiquidApplicationApi.data.dtos.payload;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SendLunchCreditToAllStaffRequest {
    @NotBlank(message = "amount is required")
    private Integer quantity;
    private String note;
}
