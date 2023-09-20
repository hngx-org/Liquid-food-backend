package org.hngxfreelunch.LiquidApplicationApi.services.withdraw;


import org.hngxfreelunch.LiquidApplicationApi.data.dtos.WithdrawalResponse;

public interface WithdrawalRequestService {

    WithdrawalResponse addWithdrawalRequest(Long staffId, Integer creditsToWithdraw);
    // TODO: ADD NEW WITHDRAWAL REQUEST

}
