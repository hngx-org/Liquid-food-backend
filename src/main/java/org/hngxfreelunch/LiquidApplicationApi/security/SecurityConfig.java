package org.hngxfreelunch.LiquidApplicationApi.security;

import lombok.RequiredArgsConstructor;
import org.hngxfreelunch.LiquidApplicationApi.security.JwtAuthenticationFilter;
import org.hngxfreelunch.LiquidApplicationApi.services.logout.LogoutServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtAuthenticationFilter authFilter;
    private final AuthenticationEntryPoint authEntryPoint;
    private final LogoutServiceImpl logoutService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.cors(corsConfigurer -> corsConfigurer.configure(http)).csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize-> authorize.requestMatchers("/auth/**",
                                "/v3/api-docs",
                                "/v3/api-docs/**",
                                "/swagger-resources",
                                "/swagger-resources/**",
                                "/configuration/ui",
                                "/configuration/security",
                                "/swagger-ui/**",
                                "/webjars/**",
                                "/swagger-ui.html")
                        .permitAll()
                        .anyRequest()
                        .authenticated())
                .exceptionHandling(exceptionHandling -> exceptionHandling.authenticationEntryPoint(authEntryPoint))
                .sessionManagement(session ->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(logout -> {
                    logout.logoutUrl("/auth/logout");
                    logout.addLogoutHandler(logoutService);
                    logout.logoutSuccessHandler((((request, response, authentication) -> SecurityContextHolder.clearContext())));
                });
        return http.build();
    }
}

