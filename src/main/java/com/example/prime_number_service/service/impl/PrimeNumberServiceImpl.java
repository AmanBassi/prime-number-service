package com.example.prime_number_service.service.impl;

import com.example.prime_number_service.exception.UnsupportedPrimeNumberAlgorithmException;
import com.example.prime_number_service.model.PrimeNumberRequest;
import com.example.prime_number_service.service.PrimeNumberService;
import com.example.prime_number_service.util.PrimeNumberUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class PrimeNumberServiceImpl implements PrimeNumberService {

    @Override
    public List<Integer> generatePrimeNumbersUsingTrialDivision(int number) {
        List<Integer> primeNumbers = new ArrayList<>();
        for (int i = 2; i <= number; i++) {
            if (PrimeNumberUtil.isPrime(i)) {
                primeNumbers.add(i);
            }
        }
        return primeNumbers;
    }

    @Override
    public List<Integer> generatePrimeNumbersUsingSieveOfEratosthenes(int number) {
        if (number < 2) {
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
    public List<Integer> generatePrimeNumbersUsingOptimisedSieveOfEratosthenes(int number) {
        if (number < 2) {
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
    public List<Integer> generatePrimeNumbers(PrimeNumberRequest request) {
        return switch (request.primeNumberAlgorithm()) {
            case TRIAL_DIVISION -> generatePrimeNumbersUsingTrialDivision(request.number());
            case SIEVE_OF_ERATOSTHENES -> generatePrimeNumbersUsingSieveOfEratosthenes(request.number());
            case OPTIMISED_SIEVE_OF_ERATOSTHENES -> generatePrimeNumbersUsingOptimisedSieveOfEratosthenes(request.number());
            default -> throw new UnsupportedPrimeNumberAlgorithmException(request.primeNumberAlgorithm().toString());
        };
    }

}
