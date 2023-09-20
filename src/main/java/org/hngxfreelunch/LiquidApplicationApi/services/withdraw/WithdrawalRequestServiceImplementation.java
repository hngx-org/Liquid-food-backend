package org.hngxfreelunch.LiquidApplicationApi.services.withdraw;

import lombok.RequiredArgsConstructor;
import org.hngxfreelunch.LiquidApplicationApi.data.repositories.WithdrawalRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class WithdrawalRequestServiceImplementation implements WithdrawalRequestService {

    private WithdrawalRepository withdrawalRepository;
}
