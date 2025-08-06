package com.example.prime_number_service.service.impl;

import com.example.prime_number_service.service.PrimeNumberService;
import com.example.prime_number_service.util.PrimeNumberUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PrimeNumberServiceImpl implements PrimeNumberService {

    @Override
    public List<Integer> calculatePrimeNumbersUpTo(int number) {
        List<Integer> primeNumbers = new ArrayList<>();
        for (int i = 2; i <= number; i++) {
            if (PrimeNumberUtil.isPrime(i)) {
                primeNumbers.add(i);
            }
        }
        return primeNumbers;
    }
}
