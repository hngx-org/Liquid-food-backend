package org.hngxfreelunch.LiquidApplicationApi.utils;

import lombok.RequiredArgsConstructor;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.UserDto;
import org.hngxfreelunch.LiquidApplicationApi.data.entities.User;
import org.hngxfreelunch.LiquidApplicationApi.data.repositories.UserRepository;
import org.hngxfreelunch.LiquidApplicationApi.exceptions.UserNotFoundException;
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

    public UserDto mapUserToDto(User user){
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phone(user.getPhone())
                .organizations(user.getOrganizations())
                .profilePic(user.getProfilePic())
                .bankName(user.getBankName())
                .bankNumber(user.getBankNumber())
                .bankCode(user.getBankCode())
                .bankRegion(user.getBankRegion())
                .currency(user.getCurrency())
                .currencyCode(user.getCurrencyCode())
                .lunchCreditBalance(user.getLunchCreditBalance())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .isAdmin(user.getIsAdmin())
                .refreshToken(user.getRefreshToken())
                .build();
    }
}
