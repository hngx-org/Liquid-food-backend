package org.hngxfreelunch.LiquidApplicationApi.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class LoginRequest {
    private String email;
    private String password;
}
