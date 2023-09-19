package org.hngxfreelunch.LiquidApplicationApi.data.dtos.response;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.response.ApiResponseDto;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDtos {

    ApiResponseDto apiResponseDto;
    private String access_token;
    private String email;
    private Long id;
    private boolean isAdmin;
}
