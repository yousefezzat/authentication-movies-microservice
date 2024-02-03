package org.microservice1.movie.exception;

public class TokenValidationException extends RuntimeException {
    public TokenValidationException(String message) {
        super(message);
    }
}
