package org.hngxfreelunch.LiquidApplicationApi.data.dtos;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class WithdrawalResponse {
    private boolean success;
    private String message;
}
