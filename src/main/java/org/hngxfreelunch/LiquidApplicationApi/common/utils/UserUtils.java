package org.hngxfreelunch.LiquidApplicationApi.common.utils;

import lombok.RequiredArgsConstructor;
import org.hngxfreelunch.LiquidApplicationApi.common.exception.UserNotFoundException;
import org.hngxfreelunch.LiquidApplicationApi.domain.model.User;
import org.hngxfreelunch.LiquidApplicationApi.domain.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserUtils {
    private final UserRepository userRepository;
    public User getLoggedInUser(){

        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        return userRepository.findByEmail(email)
                .orElseThrow(()-> new UserNotFoundException("User with email does not exists"));
    }
}
