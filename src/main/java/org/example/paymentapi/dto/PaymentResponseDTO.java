package org.example.paymentapi.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class PaymentResponseDTO {

    private UUID id;
    private String cardNumber;
    private String cardHolder;
    private LocalDate cardExpirationDate;
    private String cvv;
    private Double amount;
    private LocalDateTime payedAt = LocalDateTime.now();
}
