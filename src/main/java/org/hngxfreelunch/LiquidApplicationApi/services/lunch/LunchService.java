package org.hngxfreelunch.LiquidApplicationApi.services.lunch;


import jakarta.servlet.http.HttpServletRequest;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.payload.LunchRequestDto;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.response.LunchResponseDto;
import org.hngxfreelunch.LiquidApplicationApi.data.entities.User;

import java.util.List;

public interface LunchService {
    // TODO: SEND LUNCH TO STAFF ID
    // TODO: GET ALL LUNCH HISTORY FOR STAFF ID
    // TODO: GET LUNCH BY ID
    // TODO: REDEEM A LUNCH

    LunchResponseDto sendLunchToStaff(LunchRequestDto lunchRequestDto);

    List<LunchResponseDto> sendLunch(String note, Integer quantity, User sender);

    List<LunchResponseDto> getAllLunch();

    LunchResponseDto getLunch(Long lunch_id);


}
