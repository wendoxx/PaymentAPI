package org.example.paymentapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "payment")
public class PaymentModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Column
    private String cardNumber;

    @Column
    private String cardHolderName;

    @Column
    private LocalDate cardExpirationDate;

    @Column
    private String cvv;

    @Column
    private Double amount;

    @Column
    private LocalDateTime payedAt;
}
