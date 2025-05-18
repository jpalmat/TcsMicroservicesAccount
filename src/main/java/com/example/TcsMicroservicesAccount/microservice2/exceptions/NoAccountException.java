package com.example.TcsMicroservicesAccount.microservice2.exceptions;

public class NoAccountException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public NoAccountException(String message) {
        super(message);
    }

    public NoAccountException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
