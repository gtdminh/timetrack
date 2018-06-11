package com.fxrialab.timetrack.utils;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Created by Minh T. on 6/11/2018.
 */
public class SecurityUtils {
    public static boolean hasRole(String role){
        if(SecurityContextHolder.getContext().getAuthentication() != null){
            for(GrantedAuthority authority: SecurityContextHolder.getContext().getAuthentication().getAuthorities()){
                String userRole = authority.getAuthority();
                if(userRole == role)
                    return true;
            }
        }

        return false;
    }
}
