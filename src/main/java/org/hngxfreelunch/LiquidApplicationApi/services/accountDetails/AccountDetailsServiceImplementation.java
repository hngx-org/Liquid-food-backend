package org.hngxfreelunch.LiquidApplicationApi.services.accountDetails;

import lombok.RequiredArgsConstructor;
import org.hngxfreelunch.LiquidApplicationApi.data.repositories.AccountDetailsRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountDetailsServiceImplementation implements AccountDetailsService {

    private AccountDetailsRepository accountDetailsRepository;

}
