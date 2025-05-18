package com.example.TcsMicroservicesAccount.microservice2.exceptions;

public class NoClienteException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public NoClienteException(String message) {
        super(message);
    }

    public NoClienteException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
