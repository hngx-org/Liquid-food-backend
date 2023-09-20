package org.hngxfreelunch.LiquidApplicationApi.data.dtos.response;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.LunchRequestDto;
import org.hngxfreelunch.LiquidApplicationApi.data.entities.Lunches;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LunchResponseDto {

    private ApiResponseDto apiResponseDto;
    private Lunches lunches;
}
