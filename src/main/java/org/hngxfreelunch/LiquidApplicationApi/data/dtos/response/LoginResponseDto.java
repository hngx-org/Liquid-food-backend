package org.hngxfreelunch.LiquidApplicationApi.data.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDto {

    ApiResponseDto apiResponseDto;
    private String access_token;
    private String email;
    private String id;
    private boolean isAdmin;
}
