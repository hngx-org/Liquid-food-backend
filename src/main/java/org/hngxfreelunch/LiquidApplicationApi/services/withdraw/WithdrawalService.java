package org.hngxfreelunch.LiquidApplicationApi.services.withdraw;


import org.hngxfreelunch.LiquidApplicationApi.data.dtos.payload.WithdrawalRequestDto;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.response.ApiResponseDto;


public interface WithdrawalService {
    ApiResponseDto<?> processWithdrawalRequest(WithdrawalRequestDto withdrawalRequestDto, Long userId);


    // TODO: CREATE NEW WITHDRAWAL REQUEST


}
