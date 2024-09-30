package org.example.paymentapi.infra;

import org.example.paymentapi.exception.NotFoundException;
import org.example.paymentapi.exception.PaymentDeniedException;
import org.example.paymentapi.exception.PaymentListEmptyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public String handleNotFoundException(NotFoundException e) {
        return e.getMessage();
    }

    @ExceptionHandler(PaymentDeniedException.class)
    public String handlePaymentDeniedException(PaymentDeniedException e) {
        return e.getMessage();
    }

    @ExceptionHandler(PaymentListEmptyException.class)
    public String handlePaymentListEmptyException(PaymentListEmptyException e) {
        return e.getMessage();
    }
}
