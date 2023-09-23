package org.hngxfreelunch.LiquidApplicationApi.data.dtos.payload;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LunchRequestDto {

    private Long receiverId;
    @NotBlank(message = "Please input a quantity")
    private Integer quantity;
    private String note;
}
