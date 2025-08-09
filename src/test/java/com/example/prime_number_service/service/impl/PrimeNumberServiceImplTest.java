package com.example.prime_number_service.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PrimeNumberServiceImplTest {

    private PrimeNumberServiceImpl primeNumberService;

    @BeforeEach
    void setUp() {
        primeNumberService = new PrimeNumberServiceImpl();
    }

    @Test
    void generatePrimeNumbersUsingTrialDivision_WithZero_ReturnsEmptyList() {
        List<Integer> result = primeNumberService.generatePrimeNumbersUsingTrialDivision(0);
        assertTrue(result.isEmpty());
    }

    @Test
    void generatePrimeNumbersUsingTrialDivision_WithOne_ReturnsEmptyList() {
        List<Integer> result = primeNumberService.generatePrimeNumbersUsingTrialDivision(1);
        assertTrue(result.isEmpty());
    }

    @Test
    void generatePrimeNumbersUsingTrialDivision_WithTwo_ReturnsListWithTwo() {
        List<Integer> result = primeNumberService.generatePrimeNumbersUsingTrialDivision(2);
        assertEquals(List.of(2), result);
    }

    @Test
    void generatePrimesUsingTrialDivision_WithFifty_ReturnsCorrectPrimeNumbers() {
        List<Integer> result = primeNumberService.generatePrimeNumbersUsingTrialDivision(50);
        assertEquals(Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47), result);
    }

    @Test
    void generatePrimeNumbersUsingTrialDivision_WithNegativeNumber_ReturnsEmptyList() {
        List<Integer> result = primeNumberService.generatePrimeNumbersUsingTrialDivision(-5);
        assertTrue(result.isEmpty());
    }

    @Test
    void generatePrimeNumbersUsingTrialDivision_ResultContainsNoDuplicates() {
        List<Integer> result = primeNumberService.generatePrimeNumbersUsingTrialDivision(50);
        assertEquals(result.size(), result.stream().distinct().count());
    }

} 