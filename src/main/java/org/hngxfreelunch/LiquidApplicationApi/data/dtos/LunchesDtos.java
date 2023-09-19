package org.hngxfreelunch.LiquidApplicationApi.data.dtos;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LunchesDtos {

    private String senderId;
    private @NotNull String receiverId;
    private @NotNull(message = "Please input a quantity") int quantity;
    private String note;
}
