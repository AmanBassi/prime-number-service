package com.example.prime_number_service.service.impl;

import com.example.prime_number_service.constant.PrimeNumberAlgorithm;
import com.example.prime_number_service.exception.UnsupportedPrimeNumberAlgorithmException;
import com.example.prime_number_service.model.PrimeNumberRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class PrimeNumberServiceImplTest {

    private PrimeNumberServiceImpl primeNumberService;

    @BeforeEach
    void setUp() {
        primeNumberService = new PrimeNumberServiceImpl();
    }

    static Stream<Arguments> inputsWithExpectedPrimes() {
        return Stream.of(
                Arguments.of(-5, List.of()),
                Arguments.of(0, List.of()),
                Arguments.of(1, List.of()),
                Arguments.of(2, List.of(2)),
                Arguments.of(50, List.of(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47))
        );
    }

    @ParameterizedTest(name = "trialDivision up to {0} -> {1}")
    @MethodSource("inputsWithExpectedPrimes")
    void trialDivisionParameterized(int input, List<Integer> expected) {
        List<Integer> result = primeNumberService.generatePrimeNumbersUsingTrialDivision(input);
        assertEquals(result.size(), result.stream().distinct().count());
        assertEquals(expected, result);
    }

    @ParameterizedTest(name = "sieve up to {0} -> {1}")
    @MethodSource("inputsWithExpectedPrimes")
    void sieveParameterized(int input, List<Integer> expected) {
        List<Integer> result = primeNumberService.generatePrimeNumbersUsingSieveOfEratosthenes(input);
        assertEquals(result.size(), result.stream().distinct().count());
        assertEquals(expected, result);
    }

    @ParameterizedTest(name = "optimised sieve up to {0} -> {1}")
    @MethodSource("inputsWithExpectedPrimes")
    void optimisedSieveParameterized(int input, List<Integer> expected) {
        List<Integer> result = primeNumberService.generatePrimeNumbersUsingOptimisedSieveOfEratosthenes(input);
        assertEquals(result.size(), result.stream().distinct().count());
        assertEquals(expected, result);
    }

    static Stream<Arguments> requestAndExpectedPrimes() {
        return Stream.of(
                Arguments.of(new PrimeNumberRequest(10, PrimeNumberAlgorithm.TRIAL_DIVISION), List.of(2, 3, 5, 7)),
                Arguments.of(new PrimeNumberRequest(10, PrimeNumberAlgorithm.SIEVE_OF_ERATOSTHENES), List.of(2, 3, 5, 7)),
                Arguments.of(new PrimeNumberRequest(10, PrimeNumberAlgorithm.OPTIMISED_SIEVE_OF_ERATOSTHENES), List.of(2, 3, 5, 7))
        );
    }

    @ParameterizedTest(name = "generatePrimeNumbers {0} -> {1}")
    @MethodSource("requestAndExpectedPrimes")
    void generatePrimeNumbersParameterized(PrimeNumberRequest request, List<Integer> expected) {
        List<Integer> result = primeNumberService.generatePrimeNumbers(request);
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("generatePrimeNumbers with unsupported enum throws exception")
    void generatePrimeNumbersUnsupported() {
        PrimeNumberRequest request = new PrimeNumberRequest(10, PrimeNumberAlgorithm.HASH_SET);
        assertThrows(UnsupportedPrimeNumberAlgorithmException.class, () -> primeNumberService.generatePrimeNumbers(request));
    }

} 