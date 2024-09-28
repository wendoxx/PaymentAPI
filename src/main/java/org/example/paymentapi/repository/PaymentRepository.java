package org.example.paymentapi.repository;

import org.example.paymentapi.model.PaymentModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PaymentRepository extends JpaRepository<PaymentModel, UUID>{
    Optional<PaymentModel> findByCardNumber (String cardNumber);
    Optional<PaymentModel> findByCardHolder (String cardHolder);
}
