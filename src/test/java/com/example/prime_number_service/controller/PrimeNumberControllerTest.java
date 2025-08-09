package com.example.prime_number_service.controller;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.Arrays;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PrimeNumberControllerTest {

    @LocalServerPort
    int port;

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }

    @Nested
    class HappyPath {

        @Test
        @DisplayName("GET /prime-numbers-trial returns primes up to N")
        void trialDivisionEndpoint() {
            given()
                    .queryParam("number", 10)
                    .when()
                    .get("/prime-numbers-trial")
                    .then()
                    .statusCode(200)
                    .body("$", equalTo(Arrays.asList(2, 3, 5, 7)));
        }

        @Test
        @DisplayName("GET /prime-numbers-sieve returns primes up to N")
        void sieveEndpoint() {
            given()
                    .queryParam("number", 10)
                    .when()
                    .get("/prime-numbers-sieve")
                    .then()
                    .statusCode(200)
                    .body("$", equalTo(Arrays.asList(2, 3, 5, 7)));
        }

        @Test
        @DisplayName("GET /prime-numbers-optimised-sieve returns primes up to N")
        void halfSieveEndpoint() {
            given()
                    .queryParam("number", 10)
                    .when()
                    .get("/prime-numbers-optimised-sieve")
                    .then()
                    .statusCode(200)
                    .body("$", equalTo(Arrays.asList(2, 3, 5, 7)));
        }

        @Test
        @DisplayName("GET /prime-numbers with TRIAL_DIVISION returns primes up to N")
        void genericEndpointTrialDivision() {
            given()
                    .queryParam("number", 10)
                    .queryParam("primeNumberAlgorithm", "TRIAL_DIVISION")
                    .when()
                    .get("/prime-numbers")
                    .then()
                    .statusCode(200)
                    .body("$", equalTo(Arrays.asList(2, 3, 5, 7)));
        }

        @Test
        @DisplayName("GET /prime-numbers with SIEVE_OF_ERATOSTHENES returns primes up to N")
        void genericEndpointSieve() {
            given()
                    .queryParam("number", 10)
                    .queryParam("primeNumberAlgorithm", "SIEVE_OF_ERATOSTHENES")
                    .when()
                    .get("/prime-numbers")
                    .then()
                    .statusCode(200)
                    .body("$", equalTo(Arrays.asList(2, 3, 5, 7)));
        }

        @Test
        @DisplayName("GET /prime-numbers with OPTIMISED_SIEVE_OF_ERATOSTHENES returns primes up to N")
        void genericEndpointOptimisedSieve() {
            given()
                    .queryParam("number", 10)
                    .queryParam("primeNumberAlgorithm", "OPTIMISED_SIEVE_OF_ERATOSTHENES")
                    .when()
                    .get("/prime-numbers")
                    .then()
                    .statusCode(200)
                    .body("$", equalTo(Arrays.asList(2, 3, 5, 7)));
        }
    }

    @Nested
    class ValidationAndErrorCases {

        @Nested
        class TrialDivision {

            @Test
            @DisplayName("number empty yields 400 ProblemDetail with title 'Missing parameter'")
            void numberEmpty() {
                given()
                        .when()
                        .get("/prime-numbers-trial")
                        .then()
                        .statusCode(400)
                        .body("title", equalTo("Missing parameter"));
            }

            @Test
            @DisplayName("number invalid yields 400 ProblemDetail with title 'Validation error'")
            void numberInvalid() {
                given()
                        .queryParam("number", "invalid")
                        .when()
                        .get("/prime-numbers-trial")
                        .then()
                        .statusCode(400)
                        .body("title", equalTo("Validation error"));
            }

            @Test
            @DisplayName("number below minimum yields 400 ProblemDetail with title 'Validation error'")
            void numberTooSmall() {
                given()
                        .queryParam("number", -1)
                        .when()
                        .get("/prime-numbers-trial")
                        .then()
                        .statusCode(400)
                        .body("title", equalTo("Validation error"));
            }

            @Test
            @DisplayName("number above maximum yields 400 ProblemDetail with title 'Validation error'")
            void numberTooLarge() {
                given()
                        .queryParam("number", 100_001)
                        .when()
                        .get("/prime-numbers-trial")
                        .then()
                        .statusCode(400)
                        .body("title", equalTo("Validation error"))
                        .body("detail", containsString("Number must be less than or equal to 100,000"));
            }
        }

        @Nested
        class SieveOfEratosthenes {

            @Test
            @DisplayName("number empty yields 400 ProblemDetail with title 'Missing parameter'")
            void numberEmpty() {
                given()
                        .when()
                        .get("/prime-numbers-sieve")
                        .then()
                        .statusCode(400)
                        .body("title", equalTo("Missing parameter"));
            }

            @Test
            @DisplayName("number invalid yields 400 ProblemDetail with title 'Validation error'")
            void numberInvalid() {
                given()
                        .queryParam("number", "invalid")
                        .when()
                        .get("/prime-numbers-sieve")
                        .then()
                        .statusCode(400)
                        .body("title", equalTo("Validation error"));
            }

            @Test
            @DisplayName("number below minimum yields 400 ProblemDetail with title 'Validation error'")
            void numberTooSmall() {
                given()
                        .queryParam("number", -1)
                        .when()
                        .get("/prime-numbers-sieve")
                        .then()
                        .statusCode(400)
                        .body("title", equalTo("Validation error"));
            }

            @Test
            @DisplayName("number above maximum yields 400 ProblemDetail with title 'Validation error'")
            void numberTooLarge() {
                given()
                        .queryParam("number", 1_000_001)
                        .when()
                        .get("/prime-numbers-sieve")
                        .then()
                        .statusCode(400)
                        .body("title", equalTo("Validation error"))
                        .body("detail", containsString("Number must be less than or equal to 1,000,000"));
            }
        }

        @Nested
        class OptimisedSieveOfEratosthenes {

            @Test
            @DisplayName("number empty yields 400 ProblemDetail with title 'Missing parameter'")
            void numberEmpty() {
                given()
                        .when()
                        .get("/prime-numbers-optimised-sieve")
                        .then()
                        .statusCode(400)
                        .body("title", equalTo("Missing parameter"));
            }

            @Test
            @DisplayName("number invalid yields 400 ProblemDetail with title 'Validation error'")
            void numberInvalid() {
                given()
                        .queryParam("number", "invalid")
                        .when()
                        .get("/prime-numbers-optimised-sieve")
                        .then()
                        .statusCode(400)
                        .body("title", equalTo("Validation error"));
            }

            @Test
            @DisplayName("number below minimum yields 400 ProblemDetail with title 'Validation error'")
            void numberTooSmall() {
                given()
                        .queryParam("number", -1)
                        .when()
                        .get("/prime-numbers-optimised-sieve")
                        .then()
                        .statusCode(400)
                        .body("title", equalTo("Validation error"));
            }

            @Test
            @DisplayName("number above maximum yields 400 ProblemDetail with title 'Validation error'")
            void numberTooLarge() {
                given()
                        .queryParam("number", 1_000_001)
                        .when()
                        .get("/prime-numbers-optimised-sieve")
                        .then()
                        .statusCode(400)
                        .body("title", equalTo("Validation error"))
                        .body("detail", containsString("Number must be less than or equal to 1,000,000"));
            }
        }

        @Nested
        class PrimeNumbers {

            @Test
            @DisplayName("number empty yields 400 ProblemDetail with title 'Validation error'")
            void numberEmpty() {
                given()
                        .queryParam("primeNumberAlgorithm", "TRIAL_DIVISION")
                        .when()
                        .get("/prime-numbers")
                        .then()
                        .statusCode(400)
                        .body("title", equalTo("Validation error"));
            }

            @Test
            @DisplayName("prime number algorithm empty yields 400 ProblemDetail with title 'Validation error'")
            void primeNumberAlgorithmEmpty() {
                given()
                        .queryParam("number", 10)
                        .when()
                        .get("/prime-numbers")
                        .then()
                        .statusCode(400)
                        .body("title", equalTo("Validation error"));
            }

            @Test
            @DisplayName("number below minimum yields 400 ProblemDetail with title 'Validation error'")
            void numberTooSmall() {
                given()
                        .queryParam("number", -1)
                        .queryParam("primeNumberAlgorithm", "TRIAL_DIVISION")
                        .when()
                        .get("/prime-numbers")
                        .then()
                        .statusCode(400)
                        .body("title", equalTo("Validation error"));
            }

            @Test
            @DisplayName("number above maximum yields 400 ProblemDetail with title 'Validation error'")
            void numberTooLarge() {
                given()
                        .queryParam("number", 1_000_001)
                        .queryParam("primeNumberAlgorithm", "TRIAL_DIVISION")
                        .when()
                        .get("/prime-numbers")
                        .then()
                        .statusCode(400)
                        .body("title", equalTo("Validation error"));
            }

            @Test
            @DisplayName("invalid enum value yields 400 with allowed values in detail")
            void invalidEnumValue() {
                given()
                        .queryParam("number", 10)
                        .queryParam("primeNumberAlgorithm", "MAGIC")
                        .when()
                        .get("/prime-numbers")
                        .then()
                        .statusCode(400)
                        .body("title", equalTo("Validation error"))
                        .body("detail", containsString("PrimeNumberAlgorithm"));
            }

            @Test
            @DisplayName("unsupported but valid enum (HASH_SET) yields 400 from service handler")
            void unsupportedEnumHandledByService() {
                given()
                        .queryParam("number", 10)
                        .queryParam("primeNumberAlgorithm", "HASH_SET")
                        .when()
                        .get("/prime-numbers")
                        .then()
                        .statusCode(400)
                        .body("title", equalTo("Validation error"))
                        .body("detail", equalTo("Unsupported prime number algorithm: HASH_SET"));
            }
        }
    }
}