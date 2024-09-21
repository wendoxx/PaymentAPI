package org.example.paymentapi.repository;

import org.example.paymentapi.model.PaymentModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PaymentRepository extends JpaRepository<PaymentModel, UUID>{
    PaymentModel findByCardNumber (String cardNumber);

}
