package org.hngxfreelunch.LiquidApplicationApi.services.withdraw;


import org.hngxfreelunch.LiquidApplicationApi.data.dtos.payload.WithdrawalRequestDto;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.response.WithdrawalResponseDto;


public interface WithdrawalService {

    public WithdrawalResponseDto processWithdrawalRequest(WithdrawalRequestDto withdrawalRequestDto, Long userId);

    // TODO: CREATE NEW WITHDRAWAL REQUEST


}
