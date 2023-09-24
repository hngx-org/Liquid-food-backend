package org.hngxfreelunch.LiquidApplicationApi.utils;

import lombok.RequiredArgsConstructor;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.response.LunchResponse;
import org.hngxfreelunch.LiquidApplicationApi.data.entities.Lunches;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LunchUtils {
    private final UserUtils userUtils;

    public LunchResponse mapLunchesToDto(Lunches lunches){
        return LunchResponse.builder()
                .id(lunches.getId())
                .sender(userUtils.mapUserToDto(lunches.getSender()))
                .receiver(userUtils.mapUserToDto(lunches.getReceiver()))
                .quantity(lunches.getQuantity())
                .redeemed(lunches.getRedeemed())
                .note(lunches.getNote())
                .createdAt(DateUtils.saveDate(lunches.getCreatedAt()))
                .updatedAt(DateUtils.saveDate(lunches.getUpdatedAt()))
                .build();
    }
}
