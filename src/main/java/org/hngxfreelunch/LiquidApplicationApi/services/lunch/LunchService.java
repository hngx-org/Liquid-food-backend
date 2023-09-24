package org.hngxfreelunch.LiquidApplicationApi.services.lunch;


import org.hngxfreelunch.LiquidApplicationApi.data.dtos.payload.LunchRequestDto;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.response.ApiResponseDto;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.response.LunchResponse;

import java.util.List;

public interface LunchService {
    // TODO: SEND LUNCH TO STAFF ID
    // TODO: GET ALL LUNCH HISTORY FOR STAFF ID
    // TODO: GET LUNCH BY ID
    // TODO: REDEEM A LUNCH

    ApiResponseDto<LunchResponse> sendLunch(Long receiverId, LunchRequestDto lunchRequestDto);

    ApiResponseDto<LunchResponse> getLunch(Long lunchId);

    ApiResponseDto<List<LunchResponse>> getAllUserLunches();

    ApiResponseDto<List<LunchResponse>> getAllPendingLunches();

    ApiResponseDto<List<LunchResponse>> getAllRedeemedLunches();

    ApiResponseDto<List<LunchResponse>> getAllSentLunchesByUser();

    ApiResponseDto<List<LunchResponse>> getAllReceivedLunchesByUser();

    ApiResponseDto<List<LunchResponse>> getAllOrganizationLunches();

    ApiResponseDto<LunchResponse> redeemLunch(Long lunchId);

    ApiResponseDto<LunchResponse> cancelLunch(Long lunchId);
}
