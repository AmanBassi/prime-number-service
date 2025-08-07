package com.example.prime_number_service.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * Performance test to compare the speed of different prime number algorithms.
 * This test measures execution time for various input sizes.
 */
class PrimeNumberServicePerformanceTest {

    private PrimeNumberServiceImpl primeNumberService;

    @BeforeEach
    void setUp() {
        primeNumberService = new PrimeNumberServiceImpl();
    }

    @Test
    void detailedPerformanceTest() {
        System.out.println("\nDetailed Performance Test");
        System.out.println("=========================");

        int[] testNumbers = {100, 1000, 10000, 100000, 1000000};

        System.out.printf("%-15s %-15s %-15s %-15s %-15s%n", "Number", "Trial (ns)", "Sieve (ns)", "Half Sieve (ns)", "Primes Found");

        for (int number : testNumbers) {
            // Warm up JVM
            primeNumberService.calculatePrimeNumbersUsingTrialAlgorithmUpTo(number);
            primeNumberService.calculatePrimeNumbersUsingSieveAlgorithmUpTo(number);
            primeNumberService.calculatePrimeNumbersUsingHalfSieveAlgorithmUpTo(number);

            // Test trial division
            long startTime = System.nanoTime();
            List<Integer> result1 = primeNumberService.calculatePrimeNumbersUsingTrialAlgorithmUpTo(number);
            long endTime = System.nanoTime();
            long duration1 = (endTime - startTime);

            // Test sieve
            startTime = System.nanoTime();
            List<Integer> result2 = primeNumberService.calculatePrimeNumbersUsingSieveAlgorithmUpTo(number);
            endTime = System.nanoTime();
            long duration2 = (endTime - startTime);

            // Test half sieve
            startTime = System.nanoTime();
            List<Integer> result3 = primeNumberService.calculatePrimeNumbersUsingHalfSieveAlgorithmUpTo(number);
            endTime = System.nanoTime();
            long duration3 = (endTime - startTime);

            System.out.printf("%-15d %-15d %-15d %-15d %-15d%n", number, duration1, duration2, duration3, result1.size());
        }
    }
}