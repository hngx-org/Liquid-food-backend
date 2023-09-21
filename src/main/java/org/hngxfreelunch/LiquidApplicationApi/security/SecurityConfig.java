package org.hngxfreelunch.LiquidApplicationApi.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
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

    private final String[] WHITE_LIST = {
            "/api/auth/login", "api/auth/staff/**", "/api/organization/create",
            "/swagger-ui.html", "/swagger-ui/**", "v3/api-docs", "v3/api-docs/**", "/"
    };

    private final String[] SWAGGER = {"/swagger-ui.html", "/swagger-ui/**", "v3/api-docs", "v3/api-docs/**"};

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(authEntryPoint)
                .and()
                .authorizeHttpRequests()
                .requestMatchers(WHITE_LIST).permitAll()
                .requestMatchers(SWAGGER).permitAll()
                .anyRequest().authenticated()
                .and()
                .logout(logout -> {
                    logout.logoutUrl("/logout");
                    logout.logoutSuccessHandler((((request, response, authentication) -> SecurityContextHolder.clearContext())));
                })
                .httpBasic();
        http.addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}

