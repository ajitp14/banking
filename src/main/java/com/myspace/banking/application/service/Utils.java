package com.myspace.banking.application.service;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class Utils {

    private Utils() {
    }

    public static String generateCustomerNumber() {
        Random random = new Random();
        long randomNumber = Math.abs(random.nextLong()) % 1000000000000L; // Ensure it's 12 digits
        return String.format("%012d", randomNumber);
    }

}
