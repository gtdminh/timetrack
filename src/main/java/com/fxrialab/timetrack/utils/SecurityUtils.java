package com.fxrialab.timetrack.utils;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * Created by Minh T. on 6/11/2018.
 */
public class SecurityUtils {
    private final static SaltedPbkdf2Encoder passwordEncoder = new SaltedPbkdf2Encoder();
    private final static int encryptIteration = 50;

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

    public static String hashPassword(String password, String salt) throws InvalidKeySpecException, NoSuchAlgorithmException {
        return passwordEncoder.createHash(password, salt);
    }

    public static String generateSalt(){
        return passwordEncoder.generateSalt();
    }

    public static boolean passwordMatches(String incomingHash, String originalHash)
    {
        return passwordEncoder.matches(incomingHash, originalHash);
    }
}
