package org.fhtech.yamaServer;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA512PasswordEncoder implements PasswordEncoder {

    private MessageDigest md;

    public SHA512PasswordEncoder() throws NoSuchAlgorithmException {
        md = MessageDigest.getInstance("SHA-512");
    }

    @Override
    public String encode(CharSequence charSequence) {
        byte[] messageDigest = md.digest(charSequence.toString().getBytes(StandardCharsets.UTF_8));
        // Convert byte array into signum representation
        BigInteger no = new BigInteger(1, messageDigest);
        // Convert message digest into hex value
        String hashtext = no.toString(16);
        // Add preceding 0s to make it 32 bit
        while (hashtext.length() < 32) {
            hashtext = "0" + hashtext;
        }
        // return the HashText
        return hashtext;
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return false;
    }
}
