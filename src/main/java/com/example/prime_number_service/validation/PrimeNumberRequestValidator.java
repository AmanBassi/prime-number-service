package com.example.prime_number_service.validation;

import com.example.prime_number_service.constant.PrimeNumberAlgorithm;
import com.example.prime_number_service.model.PrimeNumberRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import static com.example.prime_number_service.constant.PrimeNumberAlgorithm.*;

public class PrimeNumberRequestValidator implements ConstraintValidator<ValidPrimeNumberRequest, PrimeNumberRequest> {

    private static final int MAX_TRIAL = 100_000;
    private static final int MAX_SIEVE = 1_000_000;

    @Override
    public boolean isValid(PrimeNumberRequest value, ConstraintValidatorContext context) {
        if (value == null) {
            return true; // Let @NotNull handle this if needed
        }

        int number = value.number();
        PrimeNumberAlgorithm algorithm = value.primeNumberAlgorithm();

        // Disable default "Invalid prime number request" message
        context.disableDefaultConstraintViolation();

        if (algorithm == TRIAL_DIVISION && number > MAX_TRIAL) {
            context.buildConstraintViolationWithTemplate("Number must be less than or equal to " + MAX_TRIAL + " for TRIAL_DIVISION"
            ).addPropertyNode("number").addConstraintViolation();
            return false;
        }

        if (algorithm == SIEVE_OF_ERATOSTHENES && number > MAX_SIEVE) {
            context.buildConstraintViolationWithTemplate("Number must be less than or equal to " + MAX_SIEVE + " for SIEVE_OF_ERATOSTHENES"
            ).addPropertyNode("number").addConstraintViolation();
            return false;
        }

        if (algorithm == OPTIMISED_SIEVE_OF_ERATOSTHENES && number > MAX_SIEVE) {
            context.buildConstraintViolationWithTemplate("Number must be less than or equal to " + MAX_SIEVE + " for OPTIMISED_SIEVE_OF_ERATOSTHENES"
            ).addPropertyNode("number").addConstraintViolation();
            return false;
        }

        return true;
    }
}


