package com.example.WellnessVision.service;
import at.favre.lib.crypto.bcrypt.BCrypt;

public class PasswordHashingService {

    public static String hashPassword(String password) {
        return BCrypt.withDefaults().hashToString(12, password.toCharArray());
    }

    public static boolean checkPassword(String password, String hashedPassword) {
        return BCrypt.verifyer().verify(password.toCharArray(), hashedPassword).verified;
    }

}
