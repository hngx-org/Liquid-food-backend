package org.hngxfreelunch.LiquidApplicationApi.data.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.UserDto;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileResponseDto {

    private ApiResponseDto apiResponseDto;
    private UserDto users;
}
