package org.hngxfreelunch.LiquidApplicationApi.data.dtos.payload;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LunchRequestDto {

    private List<String> receiverId;
    @NotBlank(message = "Please input a quantity")
    private int quantity;
    private String note;
}
