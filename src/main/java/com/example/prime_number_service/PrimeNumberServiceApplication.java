package com.example.prime_number_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class PrimeNumberServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PrimeNumberServiceApplication.class, args);
    }

}
