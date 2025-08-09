package com.example.prime_number_service.model;

import com.example.prime_number_service.annotation.ValidPrimeNumberRequest;
import com.example.prime_number_service.constant.PrimeNumberAlgorithm;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

@ValidPrimeNumberRequest
public record PrimeNumberRequest(
        @PositiveOrZero int number,
        @NotNull PrimeNumberAlgorithm primeNumberAlgorithm
) {
}