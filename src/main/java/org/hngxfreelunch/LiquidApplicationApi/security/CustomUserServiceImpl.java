package org.hngxfreelunch.LiquidApplicationApi.security;

import lombok.RequiredArgsConstructor;
import org.hngxfreelunch.LiquidApplicationApi.data.repositories.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomUserServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        org.hngxfreelunch.LiquidApplicationApi.data.entities.User user = userRepository.findByEmail(email).orElseThrow(()->
                new UsernameNotFoundException("Wrong user credentials"));
        return new User(user.getEmail(), user.getPasswordHash(), getAuthorities(user));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(org.hngxfreelunch.LiquidApplicationApi.data.entities.User user){
        if(user.getIsAdmin()){
            return Collections.singletonList(new SimpleGrantedAuthority("ADMIN"));
        }else{
            return Collections.singletonList(new SimpleGrantedAuthority("USER"));
        }
    }
}
