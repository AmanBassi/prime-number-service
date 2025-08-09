package com.example.prime_number_service.service;

import com.example.prime_number_service.model.PrimeNumberRequest;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface PrimeNumberService {

    List<Integer> generatePrimeNumbersUsingTrialDivision(int number);

    List<Integer> generatePrimeNumbersUsingSieveOfEratosthenes(int number);

    List<Integer> generatePrimeNumbersUsingOptimisedSieveOfEratosthenes(int number);

    List<Integer> generatePrimeNumbers(@Valid PrimeNumberRequest request);
}
