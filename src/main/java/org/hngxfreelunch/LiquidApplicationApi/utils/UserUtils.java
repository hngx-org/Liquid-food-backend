package org.hngxfreelunch.LiquidApplicationApi.utils;

import lombok.RequiredArgsConstructor;
import org.hngxfreelunch.LiquidApplicationApi.data.entities.Staff;
import org.hngxfreelunch.LiquidApplicationApi.data.repositories.StaffRepository;
import org.hngxfreelunch.LiquidApplicationApi.exceptions.UserNotFoundException;
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
