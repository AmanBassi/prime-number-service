package com.example.prime_number_service.controller;

import com.example.prime_number_service.service.PrimeNumberService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PrimeNumberController {

    private final PrimeNumberService primeNumberService;

    public PrimeNumberController(PrimeNumberService primeNumberService) {
        this.primeNumberService = primeNumberService;
    }

    @GetMapping("/prime-numbers")
    public List<Integer> getPrimeNumbersUpTo(@RequestParam int number) {
        return primeNumberService.calculatePrimeNumbersUpTo(number);
    }
}
