package org.example.paymentapi.exception;

public class PaymentListEmptyException extends RuntimeException {
    public PaymentListEmptyException(String message) {
        super("Payment list is empty. " + message);
    }
}
