package com.example.prime_number_service.service;

import java.util.List;

public interface PrimeNumberService {

    List<Integer> calculatePrimeNumbersUsingTrialAlgorithmUpTo(int number);
    List<Integer> calculatePrimeNumbersUsingSieveAlgorithmUpTo(int number);
    List<Integer> calculatePrimeNumbersUsingHalfSieveAlgorithmUpTo(int number);
}
