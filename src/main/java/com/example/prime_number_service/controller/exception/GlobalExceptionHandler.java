package com.example.prime_number_service.controller.exception;

import com.example.prime_number_service.exception.UnsupportedPrimeNumberAlgorithmException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.validation.ConstraintViolationException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Arrays;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ProblemDetail handleConstraintViolationExceptions(ConstraintViolationException ex){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
        problemDetail.setTitle("Validation error");
        return problemDetail;
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ProblemDetail handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        if (ex.getRequiredType() != null && ex.getRequiredType().isEnum()) {
            problemDetail.setDetail("Invalid value for parameter '" + ex.getName() + "'. Allowed values are: " + Arrays.toString(ex.getRequiredType().getEnumConstants()));
        } else {
            problemDetail.setDetail("Invalid parameter: " + ex.getName());
        }
        problemDetail.setTitle("Validation error");
        return problemDetail;
    }

    @ExceptionHandler(UnsupportedPrimeNumberAlgorithmException.class)
    public ProblemDetail handleUnsupportedPrimeNumberAlgorithmException(UnsupportedPrimeNumberAlgorithmException ex){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
        problemDetail.setTitle("Validation error");
        return problemDetail;
    }
}
