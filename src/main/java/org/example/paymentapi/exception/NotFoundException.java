package org.example.paymentapi.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super("Payment not found. " + message);
    }
}