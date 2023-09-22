package org.hngxfreelunch.LiquidApplicationApi.services.loginService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.payload.LoginRequestDto;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.response.LoginResponseDto;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.response.TokenResponse;
import org.hngxfreelunch.LiquidApplicationApi.data.repositories.UserRepository;
import org.hngxfreelunch.LiquidApplicationApi.exceptions.InvalidCredentials;
import org.hngxfreelunch.LiquidApplicationApi.exceptions.UserNotFoundException;
import org.hngxfreelunch.LiquidApplicationApi.security.AuthenticatedUser;
import org.hngxfreelunch.LiquidApplicationApi.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginServiceImpl implements LoginService {

    private final UserRepository userRepository;
    private final JwtService jwtUtils;
    private final AuthenticationManager authenticationManager;

    @Override
    public LoginResponseDto loginUser(LoginRequestDto loginRequest){
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),
                            loginRequest.getPassword())
            );

            Map<String, Object> claims = authentication.getAuthorities().stream()
                    .collect(Collectors.toMap(authority -> "claim", Function.identity()));
            AuthenticatedUser user = (AuthenticatedUser) authentication.getPrincipal();
            String email = user.getUser().getEmail();
            TokenResponse response = this.generateTokens(claims, email);
            return LoginResponseDto.builder()
                    .accessToken(response.getAccessToken())
                    .refreshToken(response.getRefreshToken())
                    .isAdmin(user.getUser().getIsAdmin())
                    .email(user.getUsername())
                    .id(user.getUser().getId())
                    .build();

        } catch (Exception e) {
            log.info(Arrays.toString(e.getStackTrace()));
            throw new InvalidCredentials("Invalid login details");
        }
    }

    @Override
    public LoginResponseDto refreshUserToken(String refreshToken){
        String userEmail = jwtUtils.extractUsername(refreshToken);
        try {
            return LoginResponseDto.builder()
                    .accessToken("")
                    .refreshToken(jwtUtils.generateRefreshToken(userEmail))
                    .isAdmin(userRepository.findByEmail(userEmail).orElseThrow(() -> new UserNotFoundException("User not found!")).getIsAdmin())
                    .email(userEmail)
                    .id(userRepository.findByEmail(userEmail).orElseThrow(() -> new UserNotFoundException("User not found")).getId())
                    .build();
        } catch (Exception e) {
            log.info(Arrays.toString(e.getStackTrace()));
            throw new InvalidCredentials("Invalid login details");
        }
    }

    private TokenResponse generateTokens(Map<String, Object> claims, String email) {
        String accessToken = jwtUtils.generateAccessToken(claims, email);
        String refreshToken = jwtUtils.generateRefreshToken(email);

        return TokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
