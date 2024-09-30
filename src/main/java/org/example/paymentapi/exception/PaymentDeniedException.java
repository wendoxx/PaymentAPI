package org.example.paymentapi.exception;

public class PaymentDeniedException extends RuntimeException {
    public PaymentDeniedException(String message) {
      super("Payment Denied. " + message);
    }
}