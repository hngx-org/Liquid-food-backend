package org.hngxfreelunch.LiquidApplicationApi.services.impl;

import lombok.RequiredArgsConstructor;
import org.hngxfreelunch.LiquidApplicationApi.domain.model.Staff;
import org.hngxfreelunch.LiquidApplicationApi.domain.repository.StaffRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserServiceImpl implements UserDetailsService {
    private final StaffRepository staffRepository;
    @Override
    public Staff loadUserByUsername(String email) throws UsernameNotFoundException {
        return staffRepository.findByEmail(email).orElseThrow(()->
                new UsernameNotFoundException("Wrong user credentials"));
    }
}
