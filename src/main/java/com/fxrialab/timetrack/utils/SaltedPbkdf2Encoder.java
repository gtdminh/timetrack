package com.fxrialab.timetrack.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.util.EncodingUtils;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

/**
 * Created by Minh T. on 6/14/2018.
 */
public class SaltedPbkdf2Encoder implements PasswordEncoder {
    private static Logger logger = LoggerFactory.getLogger(SaltedPbkdf2Encoder.class);

    private String HASH_ALGORITHM = "PBKDF2WithHmacSHA1";
    private final int ITERATION = 1000;
    private final int HASH_LENGTH =  256;
    private final int SALT_LENGTH = 24;

    private byte[] pbkdf2(char[] chars, byte[] salt, int iteration, int length) throws NoSuchAlgorithmException, InvalidKeySpecException {
        PBEKeySpec spec = new PBEKeySpec(chars, salt, iteration, length);
        SecretKeyFactory factory = SecretKeyFactory.getInstance(HASH_ALGORITHM);
        return factory.generateSecret(spec).getEncoded();
    }

    private boolean byteArratEqual(byte[] a, byte[] b){
        int diff = a.length ^ b.length;
        for(short i=0; i < a.length && i < b.length; i++){
            diff |= a[i] ^ b[i];
        }

        return diff == 0;
    }

    public String createHash(String password, String salt) throws InvalidKeySpecException, NoSuchAlgorithmException {
        byte[] bytes = salt.getBytes();

        byte[] hash  = pbkdf2(password.toCharArray(), bytes, ITERATION, HASH_LENGTH);

        return String.format("%d:%s:%s",ITERATION,String.valueOf(Hex.encode(bytes)), String.valueOf(Hex.encode(hash)) );
    }

    public String generateSalt()
    {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[SALT_LENGTH];
        random.nextBytes(bytes);

        return new String(bytes, StandardCharsets.UTF_8);
    }

    @Override
    public String encode(CharSequence rawPassword) {
        String salt = generateSalt();

        try {
            return createHash(rawPassword.toString(), salt);
        } catch (InvalidKeySpecException e) {
            logger.error(Marker.ANY_MARKER, e);
        } catch (NoSuchAlgorithmException e) {
            logger.error(Marker.ANY_MARKER, e);
        }

        return null;
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        String[] parts = encodedPassword.split(":");
        int iteration = Integer.valueOf(parts[0]);
        byte[] salt   = Hex.decode(parts[1]);
        byte[] hash   = Hex.decode(parts[2]);
        try {
            byte[] testHash = pbkdf2(rawPassword.toString().toCharArray(), salt, iteration, hash.length);
            return byteArratEqual(testHash, hash);
        } catch (NoSuchAlgorithmException e) {
            logger.error(Marker.ANY_MARKER, e);
        } catch (InvalidKeySpecException e) {
            logger.error(Marker.ANY_MARKER, e);
        }
        return  false;
    }

    public boolean matches(String hashedPassword, String encodedPassword)
    {
        String[] parts = encodedPassword.split(":");
        //int iteration = Integer.valueOf(parts[0]);
        //byte[] salt   = Hex.decode(parts[1]);
        byte[] hash   = Hex.decode(parts[2]);
        byte[] testHash = Hex.decode(hashedPassword);

        return byteArratEqual(testHash, hash);
    }
}
