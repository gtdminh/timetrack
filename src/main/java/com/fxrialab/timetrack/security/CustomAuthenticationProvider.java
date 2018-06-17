package com.fxrialab.timetrack.security;

import com.fxrialab.timetrack.security.model.User;
import com.fxrialab.timetrack.security.persistence.UserService;
import com.fxrialab.timetrack.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Minh T. on 6/7/2018.
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String hash = (String)authentication.getCredentials();
        User user       = userService.findByUsernameOrEmail(username);
        if(user == null) throw new BadCredentialsException("Username " + username + " not found.");

        if(!SecurityUtils.passwordMatches(hash, user.getPassword())) throw new BadCredentialsException("Password is not valid");

        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add((GrantedAuthority) () -> user.getRole().toUpperCase());

        return new UsernamePasswordAuthenticationToken(username, hash, authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class == authentication;
    }
}
