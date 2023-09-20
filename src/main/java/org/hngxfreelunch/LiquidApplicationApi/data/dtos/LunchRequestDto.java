package org.hngxfreelunch.LiquidApplicationApi.data.dtos;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LunchRequestDto {

    private String receiverId;
    @NotBlank(message = "Please input a quantity")
    private int quantity;
    private String note;
}
