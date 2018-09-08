package com.fxrialab.timetrack.utils;

import org.junit.Test;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import static org.junit.Assert.*;

public class SecurityUtilsTest {

    @Test
    public void hashPassword() throws InvalidKeySpecException, NoSuchAlgorithmException {
        String password = "password";
        String salt = "D7bc@AjYle7h9Zhy";
        String expected = "1000:D7bc@AjYle7h9Zhy:920D722F7A5FEA107A69ED9A61FAFD0F37717CF1773DCDB242CD7B34CB5024BEB0E87B697696CFB1319746E676F2FEE607FB8417E85C970449E05208E98A012B7A3E4A8A32997B90B264FA4E92331E0FD6FED839B89CB927D56B0A1F59B3F2B4E891268CB1DEDA20461FAC832057E9C5C27D559FDA1CFA25F2E963292BC93135DAF1F8E3EA9DFF0A24D52D85D37C6C4A6840DB604697AD71A8222F4C12827C9EC19DA2DDEC932729D0442BE6EC2444AD50678C6899AB51F77E10FB64E6C56DB688553096277C87F4047646216BC5A4770EF50797E6B8C03D5A6620FBDCBFDCD03209C96745B4116B50C330E29AB72D83632A35B4BBD10CD9455F1713E11F1265";
        String actual = SecurityUtils.hashPassword(password, salt);
        assertTrue(SecurityUtils.passwordMatches(actual,expected ));

    }
}