package org.hngxfreelunch.LiquidApplicationApi.data.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.UserDto;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsersResponseDto {

    private String email;
    private String full_name;
    private String organization_name;
}
