package com.tharishaperera.toothcare.utils;

import java.util.Random;

public class Utils {
    public static long generateId() {
        // define the minimum and maximum id
        long minId = 1000L;
        long maxId = 9999L;

        Random random = new Random();

        // generate a random ID number within the specified range and return
        return minId + (long) (random.nextDouble() * (maxId - minId + 1));
    }
}