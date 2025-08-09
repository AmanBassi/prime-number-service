package com.example.prime_number_service.service;

import com.example.prime_number_service.model.PrimeNumberRequest;

import java.util.List;

public interface PrimeNumberService {

    List<Integer> generatePrimeNumbersUsingTrialDivision(int number);
    List<Integer> generatePrimeNumbersUsingSieveOfEratosthenes(int number);
    List<Integer> generatePrimeNumbersUsingOptimisedSieveOfEratosthenes(int number);
    List<Integer> generatePrimeNumbers(PrimeNumberRequest request);
}
