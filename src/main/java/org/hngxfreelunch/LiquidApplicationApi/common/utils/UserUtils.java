package org.hngxfreelunch.LiquidApplicationApi.common.utils;

import lombok.RequiredArgsConstructor;
import org.hngxfreelunch.LiquidApplicationApi.common.exception.UserNotFoundException;
import org.hngxfreelunch.LiquidApplicationApi.domain.model.Staff;
import org.hngxfreelunch.LiquidApplicationApi.domain.repository.StaffRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserUtils {
    private final StaffRepository staffRepository;
    public Staff getLoggedInUser(){

        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        return staffRepository.findByEmail(email)
                .orElseThrow(()-> new UserNotFoundException("User with email does not exists"));
    }
}
