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

    public PaymentResponseDTO findPaymentByCardNumber(String cardNumber) {
        PaymentModel paymentModel = paymentRepository.findByCardNumber(cardNumber).orElseThrow(() -> new RuntimeException("Payment not found"));
        return modelMapper.map(paymentModel, PaymentResponseDTO.class);
    }

    public PaymentResponseDTO savePayment(PaymentModel paymentModel) {
        PaymentModel savedPayment = paymentRepository.save(paymentModel);
        if(savedPayment == null) {
            throw new RuntimeException("Payment not saved");
        }
        return modelMapper.map(savedPayment, PaymentResponseDTO.class);
    }

    public PaymentResponseDTO updatePayment(UUID id, PaymentModel paymentModel) {
        PaymentModel updatedPayment = paymentRepository.save(paymentModel);
        return modelMapper.map(updatedPayment, PaymentResponseDTO.class);
    }

    public void deletePayment(UUID id) {
        paymentRepository.deleteById(id);
    }

    public PaymentResponseDTO findPaymentByCardHolderName(String cardHolderName) {
        PaymentModel paymentModel = paymentRepository.findByCardHolderName(cardHolderName).orElseThrow(() -> new RuntimeException("Payment not found"));
        return modelMapper.map(paymentModel, PaymentResponseDTO.class);
    }

}
