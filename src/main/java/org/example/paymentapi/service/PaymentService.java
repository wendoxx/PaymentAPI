package org.example.paymentapi.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.paymentapi.dto.PaymentRequestDTO;
import org.example.paymentapi.dto.PaymentResponseDTO;
import org.example.paymentapi.exception.NotFoundException;
import org.example.paymentapi.exception.PaymentDeniedException;
import org.example.paymentapi.exception.PaymentListEmptyException;
import org.example.paymentapi.model.PaymentModel;
import org.example.paymentapi.model.PaymentStatus;
import org.example.paymentapi.repository.PaymentRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@Data
@AllArgsConstructor
public class PaymentService {

    private final ModelMapper modelMapper;
    private final PaymentRepository paymentRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentService.class);

    public PaymentResponseDTO findPaymentById(UUID id) {
        LOGGER.info("Finding payment...");
       PaymentModel paymentModel = paymentRepository.findById(id).orElseThrow(() -> new NotFoundException("This id does not exist"));
        LOGGER.info("Payment found");
       return modelMapper.map(paymentModel, PaymentResponseDTO.class);
    }

    public PaymentResponseDTO findPaymentByCardNumber(String cardNumber) {
        PaymentModel paymentModel = paymentRepository.findByCardNumber(cardNumber).get();
        if (paymentRepository.findByCardNumber(cardNumber).isEmpty()) {
            LOGGER.error("Payment not found. Please, check the card number");
            throw new NotFoundException("This card number does not exist");
        }
        return modelMapper.map(paymentModel, PaymentResponseDTO.class);
    }

    public List<PaymentResponseDTO> findAllPayments() {
        LOGGER.info("Finding all payments...");

        if (paymentRepository.findAll().isEmpty()) {
            LOGGER.error("No payments found");
            throw new PaymentListEmptyException("No payments found");
        }

        return modelMapper.map(paymentRepository.findAll(), List.class);
    }

    public PaymentResponseDTO saveAndUpdatePayment(PaymentRequestDTO paymentRequestDTO) {
        LOGGER.info("Saving payment...");
        PaymentModel payment;
        if (paymentRequestDTO.getId() != null && paymentRepository.existsById(paymentRequestDTO.getId())){
            LOGGER.info("Updating payment");
            payment = paymentRepository.findById(paymentRequestDTO.getId()).orElseThrow(() -> new NotFoundException("Payment not found"));
        }else {
            payment = new PaymentModel();
        }

        if (paymentRequestDTO.getCardExpirationDate().isBefore(LocalDate.now())) {
            payment.setStatus(PaymentStatus.DENIED);
        } else {
            payment.setStatus(PaymentStatus.APPROVED);
        }

        // The Payment just will be approved if the card expiration date is after the current date
        if (payment.getStatus() == PaymentStatus.DENIED) {
                LOGGER.error("Payment denied. Please, check the card expiration date");
                throw new PaymentDeniedException("Payment denied. Please, check the card" + "expiration date and try again.");
        }
        payment.setCardNumber(paymentRequestDTO.getCardNumber());
        payment.setCardHolder(paymentRequestDTO.getCardHolder());
        payment.setCardExpirationDate(paymentRequestDTO.getCardExpirationDate());
        payment.setCvv(paymentRequestDTO.getCvv());
        payment.setAmount(paymentRequestDTO.getAmount());
        LOGGER.info("Payment saved");
        return modelMapper.map(paymentRepository.save(payment), PaymentResponseDTO.class);
    }

    public PaymentResponseDTO savePayment(PaymentRequestDTO paymentRequestDTO) {
        return saveAndUpdatePayment(paymentRequestDTO);
    }

    public PaymentResponseDTO updatePayment(PaymentRequestDTO paymentRequestDTO) {
        return saveAndUpdatePayment(paymentRequestDTO);
    }

    public void deletePayment(UUID id) {
        LOGGER.info("Deleting payment...");

        if (paymentRepository.findById(id).isEmpty()) {
            LOGGER.error("Payment not found");
            throw new NotFoundException("Payment not found");
        }

        LOGGER.info("Payment deleted");
        paymentRepository.deleteById(id);
    }

    public PaymentResponseDTO findPaymentByCardHolder(String cardHolder) {
        LOGGER.info("Finding payment...");
        PaymentModel paymentModel = paymentRepository.findByCardHolder(cardHolder).orElseThrow(() -> new NotFoundException("This card holder does not exist"));
        LOGGER.info("Payment found");
        return modelMapper.map(paymentModel, PaymentResponseDTO.class);
    }

}
