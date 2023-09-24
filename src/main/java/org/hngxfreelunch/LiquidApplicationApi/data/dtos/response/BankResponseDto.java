package org.hngxfreelunch.LiquidApplicationApi.data.dtos.response;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BankResponseDto {
    private String bankNumber;
    private String bankCode;
    private String bankName;
    private String email;
    private String user_id;
    private String org_id;
}
