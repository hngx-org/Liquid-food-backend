package org.hngxfreelunch.LiquidApplicationApi.services.loginService;

import lombok.RequiredArgsConstructor;

import org.hngxfreelunch.LiquidApplicationApi.data.dtos.payload.LoginRequestDto;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.response.LoginResponseDto;
import org.hngxfreelunch.LiquidApplicationApi.data.entities.User;
import org.hngxfreelunch.LiquidApplicationApi.data.repositories.UserRepository;
import org.hngxfreelunch.LiquidApplicationApi.exceptions.FreeLunchException;
import org.hngxfreelunch.LiquidApplicationApi.exceptions.InvalidCredentials;
import org.hngxfreelunch.LiquidApplicationApi.exceptions.UserNotFoundException;
import org.hngxfreelunch.LiquidApplicationApi.security.CustomUserServiceImpl;
import org.hngxfreelunch.LiquidApplicationApi.security.JwtService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final CustomUserServiceImpl userService;

    @Override
    public LoginResponseDto loginUser(LoginRequestDto loginRequest){
        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(()-> new UserNotFoundException("Invalid Credentials"));
        if(passwordEncoder.matches(loginRequest.getPassword(), user.getPasswordHash())){
            Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPasswordHash());
            String accessToken = jwtService.generateToken(authentication);
            String refreshToken = jwtService.generateRefreshToken(authentication);
            user.setRefreshToken(refreshToken);
            userRepository.save(user);
            return LoginResponseDto.builder()
                    .accessToken(accessToken)
                    .isAdmin((user.getIsAdmin()))
                    .email(user.getEmail())
                    .id(user.getId())
                    .build();
        }else{
            throw new InvalidCredentials("Invalid Credentials");
        }
    }

    @Override
    public LoginResponseDto refreshUserToken(String refreshToken){
        String userEmail = jwtService.extractUsername(refreshToken);
        if(userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null){
            org.springframework.security.core.userdetails.User user = userService.loadUserByUsername(userEmail);
            if(jwtService.isTokenValid(refreshToken, user)){
                User theUser = userRepository.findByEmail(userEmail)
                        .orElseThrow(()-> new UserNotFoundException("Invalid Credentials"));
                Authentication authentication = new UsernamePasswordAuthenticationToken(theUser.getEmail(), theUser.getPasswordHash());
                String accessToken = jwtService.generateToken(authentication);
                return LoginResponseDto.builder()
                        .accessToken(accessToken)
                        .isAdmin((theUser.getIsAdmin()))
                        .email(theUser.getEmail())
                        .id(theUser.getId())
                        .build();
            }
        }

        throw new FreeLunchException();
    }
}
