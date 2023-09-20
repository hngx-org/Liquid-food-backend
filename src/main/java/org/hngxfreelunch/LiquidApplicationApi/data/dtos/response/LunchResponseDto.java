package org.hngxfreelunch.LiquidApplicationApi.data.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LunchResponseDto {

    private ApiResponseDto apiResponseDto;
    private String profilePicture;
    private String role;
    private Integer quantity;
//    private lunches lunches;
}
