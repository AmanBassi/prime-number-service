package com.example.prime_number_service.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PrimeNumberUtilTest {

    @Test
    void isPrime_WithNegativeNumber_ReturnsFalse() {
        assertFalse(PrimeNumberUtil.isPrime(-1));
        assertFalse(PrimeNumberUtil.isPrime(-10));
        assertFalse(PrimeNumberUtil.isPrime(-100));
        assertFalse(PrimeNumberUtil.isPrime(Integer.MIN_VALUE));
    }

    @Test
    void isPrime_WithZero_ReturnsFalse() {
        assertFalse(PrimeNumberUtil.isPrime(0));
    }

    @Test
    void isPrime_WithOne_ReturnsFalse() {
        assertFalse(PrimeNumberUtil.isPrime(1));
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, Integer.MAX_VALUE})
    void isPrime_WithPrimeNumbers_ReturnsTrue(int primeNumber) {
        assertTrue(PrimeNumberUtil.isPrime(primeNumber));
    }

    @ParameterizedTest
    @ValueSource(ints = {4, 6, 8, 9, 10, 12, 14, 15, 16, 18, 20, 21, 22, 24, 25, 26, 27, 28, 30, Integer.MAX_VALUE - 1})
    void isPrime_WithNonPrimeNumbers_ReturnsFalse(int nonPrimeNumber) {
        assertFalse(PrimeNumberUtil.isPrime(nonPrimeNumber));
    }
}