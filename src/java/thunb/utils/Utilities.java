/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thunb.utils;

import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 *
 * @author Banh Bao
 */
public class Utilities {

    public static String encryptedPasswordByUsingSHA256(String pass)
            throws NoSuchAlgorithmException {
        String encoded;

        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(pass.getBytes(StandardCharsets.UTF_8));
        encoded = Base64.getEncoder().encodeToString(hash);

        return encoded;
    }
}
