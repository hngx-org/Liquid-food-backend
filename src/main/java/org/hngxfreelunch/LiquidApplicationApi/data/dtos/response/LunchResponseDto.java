package org.hngxfreelunch.LiquidApplicationApi.data.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hngxfreelunch.LiquidApplicationApi.data.entities.lunches;

import java.math.BigInteger;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LunchResponseDto {

    private ApiResponseDto apiResponseDto;
    private String profile_picture;
    private String role;
    private BigInteger quantity;
//    private lunches lunches;
}
