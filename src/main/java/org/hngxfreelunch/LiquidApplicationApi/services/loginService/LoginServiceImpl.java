package org.hngxfreelunch.LiquidApplicationApi.services.loginService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.payload.LoginRequestDto;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.response.ApiResponseDto;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.response.LoginResponseDto;
import org.hngxfreelunch.LiquidApplicationApi.data.entities.User;
import org.hngxfreelunch.LiquidApplicationApi.data.repositories.UserRepository;
import org.hngxfreelunch.LiquidApplicationApi.exceptions.FreeLunchException;
import org.hngxfreelunch.LiquidApplicationApi.security.JwtService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginServiceImpl implements LoginService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtUtils;

    @Override
    public ApiResponseDto<LoginResponseDto> loginUser(LoginRequestDto loginRequest){
        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(()-> new FreeLunchException("User is not registered"));
        if(!passwordEncoder.matches(loginRequest.getPassword(),user.getPasswordHash())){
            throw  new FreeLunchException("Invalid Credentials");
        }
        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(),
                        user.getPasswordHash());
        String accessToken = jwtUtils.generateAccessToken(authentication);
        String refreshToken = jwtUtils.generateRefreshToken(authentication);
        return new ApiResponseDto<>("User Logged in successfully",200, LoginResponseDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .isAdmin(user.getIsAdmin())
                .email(user.getEmail())
                .id(user.getId())
                .build());
    }

    @Override
    public ApiResponseDto<LoginResponseDto> refreshUserToken(String refreshToken){
        String userEmail = jwtUtils.extractUserEmail(refreshToken);
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(()-> new FreeLunchException("User is not registered"));
        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(),
                user.getPasswordHash());
        String accessToken = jwtUtils.generateAccessToken(authentication);
        return new ApiResponseDto<>("User Logged in successfully",200, LoginResponseDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .isAdmin(user.getIsAdmin())
                .email(user.getEmail())
                .id(user.getId())
                .build());
    }
}
