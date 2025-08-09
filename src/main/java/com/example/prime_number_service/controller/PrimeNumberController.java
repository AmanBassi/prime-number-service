package com.example.prime_number_service.controller;

import com.example.prime_number_service.constant.PrimeNumberAlgorithm;
import com.example.prime_number_service.model.PrimeNumberRequest;
import com.example.prime_number_service.service.PrimeNumberService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Validated
public class PrimeNumberController {

    private final PrimeNumberService primeNumberService;

    public PrimeNumberController(PrimeNumberService primeNumberService) {
        this.primeNumberService = primeNumberService;
    }

    @GetMapping("/prime-numbers-trial")
    public List<Integer> getPrimeNumbersUpTo(
            @RequestParam
            @Min(value = 0, message = "Number must be at least 0")
            @Max(value = 1000000, message = "Number must be less than or equal to 1,000,000") int number) {
        return primeNumberService.generatePrimeNumbersUsingTrialDivision(number);
    }

    @GetMapping("/prime-numbers-sieve")
    public List<Integer> getPrimeNumbersUsingSieveAlgorithmUpTo(
            @RequestParam
            @Min(value = 0, message = "Number must be at least 0")
            @Max(value = 1000000, message = "Number must be less than or equal to 1,000,000") int number) {
        return primeNumberService.generatePrimeNumbersUsingSieveOfEratosthenes(number);
    }

    @GetMapping("/prime-numbers-optimised-sieve")
    public List<Integer> getPrimeNumbersUsingHalfSieveAlgorithmUpTo(
            @RequestParam
            @Min(value = 0, message = "Number must be at least 0")
            @Max(value = 1000000, message = "Number must be less than or equal to 1,000,000") int number) {
        return primeNumberService.generatePrimeNumbersUsingOptimisedSieveOfEratosthenes(number);
    }

    @GetMapping("/prime-numbers")
    public List<Integer> getPrimeNumbersUpTo(
            @RequestParam int number,
            @RequestParam PrimeNumberAlgorithm primeNumberAlgorithm) {
        return primeNumberService.generatePrimeNumbers(new PrimeNumberRequest(number, primeNumberAlgorithm));
    }
}
