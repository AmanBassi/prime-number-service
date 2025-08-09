package com.example.prime_number_service.exception;

public class UnsupportedPrimeNumberAlgorithmException extends RuntimeException {
        public UnsupportedPrimeNumberAlgorithmException(String algorithm){
            super("Unsupported prime number algorithm: " + algorithm);
        }
}
