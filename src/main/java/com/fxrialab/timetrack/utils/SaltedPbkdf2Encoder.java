package com.fxrialab.timetrack.utils;

import org.apache.log4j.Logger;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * Created by Minh T. on 6/14/2018.
 */
public class SaltedPbkdf2Encoder implements PasswordEncoder {
    private static Logger logger = Logger.getLogger(SaltedPbkdf2Encoder.class);
    private static String saltChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789@#$%^&-=!";
    //private String HASH_ALGORITHM = "PBKDF2WithHmacSHA1";
    private static final int ITERATION = 1000;
    private static final int HASH_LENGTH = 256;
    private static final int SALT_LENGTH = 16;

    private byte[] pbkdf2(byte[] chars, byte[] salt, int iteration, int length) {
        PBKDF2 provider = new PBKDF2();
        return provider.generateDerivedKey(length, chars, salt, iteration);
    }

    private boolean byteArrayEqual(byte[] a, byte[] b) {
        int diff = a.length ^ b.length;
        for (short i = 0; i < a.length && i < b.length; i++) {
            diff |= a[i] ^ b[i];
        }

        return diff == 0;
    }

    public String createHash(String password, String salt) {
        byte[] bytes = salt.getBytes(StandardCharsets.UTF_8);

        byte[] hash = pbkdf2(password.getBytes(StandardCharsets.UTF_8), bytes, ITERATION, HASH_LENGTH);

        return String.format("%d:%s:%s", ITERATION, salt, String.valueOf(Hex.encode(hash)).toUpperCase());
    }

    public String generateSalt() throws NoSuchAlgorithmException {
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        char[] chars = new char[SALT_LENGTH];
        int seed = random.nextInt(saltChars.length());
        for(int i=0; i < chars.length; i++){
            chars[i] = saltChars.charAt(seed);
            seed = random.nextInt(saltChars.length());
        }

        return new String(chars);
    }

    @Override
    public String encode(CharSequence rawPassword) {
        String salt = null;
        try {
            salt = generateSalt();
        } catch (NoSuchAlgorithmException e) {
            logger.error(e);
        }

        try {
            return createHash(rawPassword.toString(), salt);
        } catch (Exception e) {
            logger.error(e);
        }

        return null;
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        String[] parts = encodedPassword.split(":");
        int iteration = Integer.valueOf(parts[0]);
        byte[] salt = Hex.decode(parts[1]);
        byte[] hash = Hex.decode(parts[2]);
        try {
            byte[] testHash = pbkdf2(rawPassword.toString().getBytes(), salt, iteration, hash.length);
            return byteArrayEqual(testHash, hash);
        } catch (Exception e) {
            logger.error(e);
        }
        return false;
    }

    public boolean matches(String hashedPassword, String encodedPassword) {
        String[] parts = encodedPassword.split(":");
        //int iteration = Integer.valueOf(parts[0]);
        //byte[] salt   = Hex.decode(parts[1]);
        byte[] hash = Hex.decode(parts[2]);
        byte[] testHash = Hex.decode(hashedPassword);

        return byteArrayEqual(testHash, hash);
    }
}
