package org.georg.web.impl.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by felix on 3/12/14.
 */
@Component("authenticationManagerImpl")
public class AuthenticationManagerImpl implements AuthenticationManager {
    @Resource(name = "authService")
    private UserDetailsService userDetailsService;

    private ShaPasswordEncoder passwordEncoder = new ShaPasswordEncoder();

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UserDetails user = userDetailsService.loadUserByUsername(authentication.getName());
        if (!passwordEncoder.isPasswordValid(user.getPassword(), (String) authentication.getCredentials(), null)) {
            throw new BadCredentialsException("Wrong password!");
        }
        return new UsernamePasswordAuthenticationToken(
                authentication.getName(),
                authentication.getCredentials(),
                user.getAuthorities());
    }
}
