package com.example.TcsMicroservicesAccount.microservice2.exceptions;

public class NoBalanceException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public NoBalanceException(String message) {
        super(message);
    }

    public NoBalanceException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
