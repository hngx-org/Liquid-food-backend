package org.hngxfreelunch.LiquidApplicationApi.services.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.hngxfreelunch.LiquidApplicationApi.common.security.JwToken;
import org.hngxfreelunch.LiquidApplicationApi.common.security.JwTokenRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogoutServiceImpl implements LogoutHandler {
    private final JwTokenRepository jwtTokenRepository ;
    @Override
    public void logout(HttpServletRequest request,
                       HttpServletResponse response,
                       Authentication authentication) {
        String accessToken;
        String authHeader = request.getHeader("Authorization");

        if(authHeader!=null&&authHeader.startsWith("Bearer ")) {
            accessToken = authHeader.substring(7);
            JwToken jwtToken = jwtTokenRepository.findByAccessToken(accessToken)
                    .orElseThrow(() -> new RuntimeException(""));
            if(jwtToken != null){
                jwtTokenRepository.delete(jwtToken);
            }
        }
    }
}
