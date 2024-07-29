package com.example.WellnessVision.service;
import org.apache.commons.lang3.RandomStringUtils;

public class RandomStringGeneratorService {
    public static String generateRandomString(int length) {
        return RandomStringUtils.randomAlphanumeric(length);
    }
}

