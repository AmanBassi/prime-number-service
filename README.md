# Prime Number Service

A simple Spring Boot application that provides REST APIs to generate and retrieve prime numbers using different algorithms.

## Features
- Generate prime numbers up to a given number using:
    - Trial Division
    - Sieve of Eratosthenes
    - Half Sieve of Eratosthenes
- Handles invalid requests with a global exception handler.

## Technologies
- Java 17+ (or your chosen version)
- Spring Boot (Web)
- Maven (for build and dependencies)

## API Endpoints

### 1. Generate Prime Numbers
**GET** `/prime-numbers-trial?number={n}`  
Generates primes up to `n` using Trial Division.

**GET** `/prime-numbers-sieve?number={n}`  
Generates primes up to `n` using Sieve of Eratosthenes.

**GET** `/prime-numbers-optimised-sieve?number={n}`  
Generates primes up to `n` using Half Sieve of Eratosthenes.

**GET** `http://localhost:8080/prime-numbers?number={n}&primeNumberAlgorithm={algorithm}`
Generates primes up to `n` using Half Sieve of Eratosthenes.

**Example Request:**
```bash
curl -H "Accept: application/json" "http://localhost:8080/prime-numbers-trial?number=50"
```
**Example Response:**
```json
[2,3,5,7,11,13,17,19,23,29,31,37,41,43,47]
```

## Error Handling
If an invalid number is provided (e.g., negative), the API returns a descriptive error in problem detail.  
Example:
```json
{
  "type":"about:blank",
  "title":"Validation error",
  "status":400,
  "detail":"getPrimeNumbersUsingTrailDivisionAlgorithm.number: Number must be at least 0",
  "instance":"/prime-numbers-trial"
}
```

## Running the Application
### 1. Clone the Repository
```bash
git clone https://github.com/AmanBassi/prime-number-service
cd prime-number-service
```

### 2. Build and Run
```bash
mvn spring-boot:run
```

### 3. Access the API
Open your browser or API tool (Postman, curl) at:
```
http://localhost:8080/swagger-ui/index.html
```
