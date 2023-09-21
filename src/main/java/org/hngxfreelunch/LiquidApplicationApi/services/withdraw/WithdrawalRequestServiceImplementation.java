<<<<<<< HEAD
package org.hngxfreelunch.LiquidApplicationApi.services.withdraw;

import lombok.RequiredArgsConstructor;
import org.hngxfreelunch.LiquidApplicationApi.data.repositories.WithdrawalRequestRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class WithdrawalRequestServiceImplementation implements WithdrawalRequestService {

    private WithdrawalRequestRepository withdrawalRequestRepository;
}
=======
package org.hngxfreelunch.LiquidApplicationApi.services.withdraw;

import lombok.RequiredArgsConstructor;
import org.hngxfreelunch.LiquidApplicationApi.data.repositories.WithdrawalRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class WithdrawalRequestServiceImplementation implements WithdrawalRequestService {

    private WithdrawalRepository withdrawalRepository;
}
>>>>>>> 37617686d864be3d5717d47a2fb029fe19dd3361
