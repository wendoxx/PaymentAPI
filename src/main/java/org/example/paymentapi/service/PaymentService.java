package org.example.paymentapi.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.paymentapi.dto.PaymentRequestDTO;
import org.example.paymentapi.dto.PaymentResponseDTO;
import org.example.paymentapi.model.PaymentModel;
import org.example.paymentapi.repository.PaymentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Data
@AllArgsConstructor
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

    public List<PaymentResponseDTO> findAllPayments() {
        return modelMapper.map(paymentRepository.findAll(), List.class);
    }

    public PaymentResponseDTO saveAndUpdatePayment(PaymentRequestDTO paymentRequestDTO) {
        PaymentModel payment;
        if (paymentRequestDTO.getId() != null && paymentRepository.existsById(paymentRequestDTO.getId())){
            payment = paymentRepository.findById(paymentRequestDTO.getId()).orElseThrow(() -> new RuntimeException("Payment not found"));

        }else {
            payment = new PaymentModel();
        }

        payment.setCardNumber(paymentRequestDTO.getCardNumber());
        payment.setCardHolder(paymentRequestDTO.getCardHolder());
        payment.setCardExpirationDate(paymentRequestDTO.getCardExpirationDate());
        payment.setCvv(paymentRequestDTO.getCvv());
        payment.setAmount(paymentRequestDTO.getAmount());

        return modelMapper.map(paymentRepository.save(payment), PaymentResponseDTO.class);
    }

    public PaymentResponseDTO savePayment(PaymentRequestDTO paymentRequestDTO) {
        return saveAndUpdatePayment(paymentRequestDTO);
    }

    public PaymentResponseDTO updatePayment(PaymentRequestDTO paymentRequestDTO) {
        return saveAndUpdatePayment(paymentRequestDTO);
    }

    public void deletePayment(UUID id) {
        paymentRepository.deleteById(id);
    }

    public PaymentResponseDTO findPaymentByCardHolder(String cardHolder) {
        PaymentModel paymentModel = paymentRepository.findByCardHolder(cardHolder).orElseThrow(() -> new RuntimeException("Payment not found"));
        return modelMapper.map(paymentModel, PaymentResponseDTO.class);
    }

}
