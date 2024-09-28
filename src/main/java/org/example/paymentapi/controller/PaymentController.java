package org.example.paymentapi.controller;

import lombok.Data;
import org.example.paymentapi.dto.PaymentRequestDTO;
import org.example.paymentapi.dto.PaymentResponseDTO;
import org.example.paymentapi.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/payment")
@Data
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping("/{id}")
    public ResponseEntity<PaymentResponseDTO> getPaymentById(UUID id){
        return ResponseEntity.ok(paymentService.findPaymentById(id));
    }

    @GetMapping("/{cardNumber}")
    public ResponseEntity<PaymentResponseDTO> getPaymentByCardNumber(String cardNumber){
        return ResponseEntity.ok(paymentService.findPaymentByCardNumber(cardNumber));
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<PaymentResponseDTO>> getAllPayments(){
        return ResponseEntity.ok(paymentService.findAllPayments());
    }

    @GetMapping("/{cardHolderName}")
    public ResponseEntity<PaymentResponseDTO> getPaymentByCardHolder(String cardHolder){
        return ResponseEntity.ok(paymentService.findPaymentByCardHolder(cardHolder));
    }

    @PostMapping
    public ResponseEntity<PaymentResponseDTO> savePayment(@RequestBody PaymentRequestDTO paymentRequestDTO){
        return ResponseEntity.status(201).body(paymentService.savePayment(paymentRequestDTO));
    }
}
