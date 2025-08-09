package com.example.prime_number_service.model;

import com.example.prime_number_service.constant.PrimeNumberAlgorithm;

public record PrimeNumberRequest(int number, PrimeNumberAlgorithm primeNumberAlgorithm) {}


