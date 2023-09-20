package org.hngxfreelunch.LiquidApplicationApi.data.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDto {

    ApiResponseDto apiResponseDto;
    private String accessToken;
    private String email;
    private String id;
    private boolean isAdmin;

}
