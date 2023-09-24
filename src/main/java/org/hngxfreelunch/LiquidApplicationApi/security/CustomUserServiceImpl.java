package org.hngxfreelunch.LiquidApplicationApi.security;

import lombok.RequiredArgsConstructor;
import org.hngxfreelunch.LiquidApplicationApi.data.entities.Role;
import org.hngxfreelunch.LiquidApplicationApi.data.entities.User;
import org.hngxfreelunch.LiquidApplicationApi.data.repositories.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
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
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found!"));

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPasswordHash(), getAuthorities(user));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(User user){
        if(user.getIsAdmin()){
            return Collections.singletonList(new SimpleGrantedAuthority(Role.ADMIN.name()));
        }else{
            return Collections.singletonList(new SimpleGrantedAuthority(Role.USER.name()));
        }
    }

}
