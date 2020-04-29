package com.ecom.springboot.poc.exception;

public class InvalidInputException extends RuntimeException {
    public InvalidInputException() {
        super();
    }

    public InvalidInputException(String message) {
        super(message);
    }

    public InvalidInputException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public InvalidInputException(Throwable throwable) {
        super(throwable);
    }
}

