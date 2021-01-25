/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thunb.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Random;

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

    public static Timestamp getCurrentTime() {
        Calendar cd = Calendar.getInstance();
        Timestamp time = new Timestamp(cd.getTimeInMillis());
        return time;
    }

    public static ArrayList<Integer> randomQuestion(int numOfQues, int totalQues) {
        ArrayList<Integer> numbers = new ArrayList<>();
        Random randomGenerator = new Random();
        while (numbers.size() < numOfQues) {
            int random = randomGenerator.nextInt(totalQues);
            if (!numbers.contains(random)) {
                numbers.add(random);
            }
        }
        return numbers;
    }
}
