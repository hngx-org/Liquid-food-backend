<<<<<<< HEAD
package org.hngxfreelunch.LiquidApplicationApi.services.lunch;


public interface LunchService {
    // TODO: SEND LUNCH TO STAFF ID
    // TODO: GET ALL LUNCHES RECEIVED BY STAFF ID
    // TODO: GET ALL LUNCHES SENT BY STAFF ID
}
=======
package org.hngxfreelunch.LiquidApplicationApi.services.lunch;


import org.hngxfreelunch.LiquidApplicationApi.data.dtos.payload.LunchRequestDto;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.response.LunchResponseDto;
import org.hngxfreelunch.LiquidApplicationApi.data.entities.User;

import java.util.List;

public interface LunchService {
    // TODO: SEND LUNCH TO STAFF ID
    // TODO: GET ALL LUNCH HISTORY FOR STAFF ID
    // TODO: GET LUNCH BY ID
    // TODO: REDEEM A LUNCH

    public List<LunchResponseDto> sendLunch(LunchRequestDto lunchRequestDto, User sender);

    public List<LunchResponseDto> getAllLunch(Long staff_id);

    public LunchResponseDto getLunch(Long lunch_id);
//    public LunchResponseDto redeemLunch(Long lunch_id);

}
>>>>>>> 37617686d864be3d5717d47a2fb029fe19dd3361
