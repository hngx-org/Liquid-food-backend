package org.hngxfreelunch.LiquidApplicationApi.data.dtos.response;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.UsersDtos;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.response.ApiResponseDto;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsersResponseDto {

    private ApiResponseDto apiResponseDto;
    private UsersDtos usersDtos;
}
