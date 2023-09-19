package org.hngxfreelunch.LiquidApplicationApi.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class LoginResponse {
    private String accessToken;
    private String role;
    private StaffResponse staff;
}
