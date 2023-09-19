package org.hngxfreelunch.LiquidApplicationApi.services.impl;

import lombok.RequiredArgsConstructor;
import org.hngxfreelunch.LiquidApplicationApi.domain.model.User;
import org.hngxfreelunch.LiquidApplicationApi.domain.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(()->
                new UsernameNotFoundException("Wrong user credentials"));
    }
}
