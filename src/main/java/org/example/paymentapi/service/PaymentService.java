package org.example.paymentapi.service;

import lombok.Data;
import org.example.paymentapi.dto.PaymentResponseDTO;
import org.example.paymentapi.model.PaymentModel;
import org.example.paymentapi.repository.PaymentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Data
public class PaymentService {

    private final ModelMapper modelMapper;
    private final PaymentRepository paymentRepository;


    public PaymentResponseDTO findPaymentById(UUID id) {
       PaymentModel paymentModel = paymentRepository.findById(id).orElseThrow(() -> new RuntimeException("Payment not found"));
         return modelMapper.map(paymentModel, PaymentResponseDTO.class);
    }
}
