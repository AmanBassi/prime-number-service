package com.example.prime_number_service.service.impl;

import com.example.prime_number_service.exception.UnsupportedPrimeNumberAlgorithmException;
import com.example.prime_number_service.model.PrimeNumberRequest;
import com.example.prime_number_service.service.PrimeNumberService;
import com.example.prime_number_service.util.PrimeNumberUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class PrimeNumberServiceImpl implements PrimeNumberService {

    private static final Logger log = LoggerFactory.getLogger(PrimeNumberServiceImpl.class);

    @Override
    @Cacheable(value = "primeNumbers", key = "#number + '-TRIAL_DIVISION'")
    public List<Integer> generatePrimeNumbersUsingTrialDivision(int number) {
        log.info("Generating prime numbers using Trial Division up to {}", number);
        List<Integer> primeNumbers = new ArrayList<>();
        for (int i = 2; i <= number; i++) {
            if (PrimeNumberUtil.isPrime(i)) {
                primeNumbers.add(i);
            }
        }
        return primeNumbers;
    }

    @Override
    @Cacheable(value = "primeNumbers", key = "#number + '-SIEVE_OF_ERATOSTHENES'")
    public List<Integer> generatePrimeNumbersUsingSieveOfEratosthenes(int number) {
        log.info("Generating prime numbers using Sieve of Eratosthenes up to {}", number);
        if (number < 2) {
            log.info("Number less than 2, returning empty list for Sieve of Eratosthenes");
            return Collections.emptyList();
        }

        boolean[] isPrime = new boolean[number + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;

        for (int i = 2; i * i <= number; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= number; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        List<Integer> primeNumbers = new ArrayList<>();
        for (int i = 2; i <= number; i++) {
            if (isPrime[i]) {
                primeNumbers.add(i);
            }
        }
        return primeNumbers;
    }

    @Override
    @Cacheable(value = "primeNumbers", key = "#number + '-OPTIMISED_SIEVE_OF_ERATOSTHENES'")
    public List<Integer> generatePrimeNumbersUsingOptimisedSieveOfEratosthenes(int number) {
        log.info("Generating prime numbers using Optimised Sieve of Eratosthenes up to {}", number);
        if (number < 2) {
            log.info("Number less than 2, returning empty list for Optimised Sieve of Eratosthenes");
            return Collections.emptyList();
        }

        // Only consider odd numbers
        int size = (number - 1) / 2;
        boolean[] isPrime = new boolean[size];
        Arrays.fill(isPrime, true);

        int limit = (int) Math.sqrt(number);
        for (int i = 0; (2 * i + 3) <= limit; i++) {
            if (isPrime[i]) {
                int p = 2 * i + 3;
                for (int j = ((p * p) - 3) / 2; j < size; j += p) {
                    isPrime[j] = false;
                }
            }
        }

        List<Integer> primes = new ArrayList<>();
        primes.add(2); // Include the only even prime

        for (int i = 0; i < size; i++) {
            if (isPrime[i]) {
                primes.add(2 * i + 3);
            }
        }

        return primes;
    }

    @Override
    @Cacheable(value = "primeNumbers", key = "#request.number + '-' + #request.primeNumberAlgorithm")
    public List<Integer> generatePrimeNumbers(PrimeNumberRequest request) {
        log.info("Generating primes for request: number={}, algorithm={}", request.number(), request.primeNumberAlgorithm());
        return switch (request.primeNumberAlgorithm()) {
            case TRIAL_DIVISION -> generatePrimeNumbersUsingTrialDivision(request.number());
            case SIEVE_OF_ERATOSTHENES -> generatePrimeNumbersUsingSieveOfEratosthenes(request.number());
            case OPTIMISED_SIEVE_OF_ERATOSTHENES -> generatePrimeNumbersUsingOptimisedSieveOfEratosthenes(request.number());
            default -> throw new UnsupportedPrimeNumberAlgorithmException(request.primeNumberAlgorithm().toString());
        };
    }

}
