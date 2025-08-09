package com.example.prime_number_service.annotation;

import com.example.prime_number_service.validation.PrimeNumberRequestValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = PrimeNumberRequestValidator.class)
@Target({TYPE})
@Retention(RUNTIME)
public @interface ValidPrimeNumberRequest {
    String message() default "Invalid prime number request";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}


