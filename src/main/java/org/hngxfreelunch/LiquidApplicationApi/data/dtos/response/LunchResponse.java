package org.hngxfreelunch.LiquidApplicationApi.data.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.UserDto;
import org.hngxfreelunch.LiquidApplicationApi.data.entities.Lunches;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class LunchResponse {
    private Long id;

    private UserDto sender;

    private UserDto receiver;

    private String note;

    private Integer quantity;

    private Boolean redeemed;

    private String createdAt;

    private String updatedAt;

}
