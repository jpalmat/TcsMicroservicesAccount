package com.example.TcsMicroservicesAccount.microservice2.exceptions;

public class NoMovementException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public NoMovementException(String message) {
        super(message);
    }

    public NoMovementException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
