package org.example.paymentapi.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class PaymentResponseDTO {

    private UUID transactionId;
    private String cardNumber;
    private String cardHolder;
    private LocalDate cardExpirationDate;
    private String cvv;
    private Double amount;
}
