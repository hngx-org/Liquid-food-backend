package org.hngxfreelunch.LiquidApplicationApi.services.impl;

import lombok.RequiredArgsConstructor;
import org.hngxfreelunch.LiquidApplicationApi.application.dto.request.LoginRequest;
import org.hngxfreelunch.LiquidApplicationApi.application.dto.response.LoginResponse;
import org.hngxfreelunch.LiquidApplicationApi.application.dto.response.StaffResponse;
import org.hngxfreelunch.LiquidApplicationApi.common.exception.InvalidCredentials;
import org.hngxfreelunch.LiquidApplicationApi.common.exception.UserNotFoundException;
import org.hngxfreelunch.LiquidApplicationApi.common.exception.UserDisabledException;
import org.hngxfreelunch.LiquidApplicationApi.common.security.JwToken;
import org.hngxfreelunch.LiquidApplicationApi.common.security.JwTokenRepository;
import org.hngxfreelunch.LiquidApplicationApi.common.security.JwtService;
import org.hngxfreelunch.LiquidApplicationApi.domain.model.Staff;
import org.hngxfreelunch.LiquidApplicationApi.domain.repository.StaffRepository;
import org.hngxfreelunch.LiquidApplicationApi.services.LoginService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {
    private final StaffRepository staffRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final JwTokenRepository tokenRepository;

    @Override
    public LoginResponse loginUser(LoginRequest loginRequest){
        Staff staff = staffRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(()-> new UserNotFoundException("Invalid Credentials"));
        if(!staff.isEnabled()){
            throw new UserDisabledException("Account is not activated");
        }
        if(passwordEncoder.matches(loginRequest.getPassword(), staff.getPassword())){
            Authentication authentication = new UsernamePasswordAuthenticationToken(staff.getUsername(), staff.getPassword());
            String accessToken = jwtService.generateToken(authentication);
            jwtService.revokeTokens(staff);
            tokenRepository.save(new JwToken(accessToken, staff));
            return LoginResponse.builder()
                    .accessToken(accessToken)
                    .role(staff.getRole().name())
                    .staff(new StaffResponse(staff))
                    .build();
        }else{
            throw new InvalidCredentials("Invalid Credentials");
        }
    }
}
