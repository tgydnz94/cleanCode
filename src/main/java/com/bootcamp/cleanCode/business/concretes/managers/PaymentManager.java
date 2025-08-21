package com.bootcamp.cleanCode.business.concretes.managers;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.bootcamp.cleanCode.business.abstracts.PaymentService;
import com.bootcamp.cleanCode.business.concretes.requests.paymentRequests.CreatePaymentRequest;
import com.bootcamp.cleanCode.business.concretes.requests.paymentRequests.UpdatePaymentRequest;
import com.bootcamp.cleanCode.business.concretes.responses.paymentResponses.GetAllPaymentsResponse;
import com.bootcamp.cleanCode.business.concretes.responses.paymentResponses.GetByIdPaymentResponse;
import com.bootcamp.cleanCode.business.concretes.rules.PaymentBusinessRules;
import com.bootcamp.cleanCode.core.utilities.mappers.ModelMapperService;
import com.bootcamp.cleanCode.dataAccess.abstracts.PaymentRepository;
import com.bootcamp.cleanCode.dataAccess.abstracts.RentalRepository;
import com.bootcamp.cleanCode.entities.Payment;
import com.bootcamp.cleanCode.entities.Rental;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PaymentManager implements PaymentService {
    private PaymentRepository paymentRepository;
    private RentalRepository rentalRepository;
    private PaymentBusinessRules businessRules;
    private ModelMapperService modelMapperService;
    @Override
    public List<GetAllPaymentsResponse> getAll() {
        List<Payment> payments = paymentRepository.findAll();
        List<GetAllPaymentsResponse> paymentsResponse = payments.stream()
				.map(payment-> this.modelMapperService.forResponse()
				.map(payment, GetAllPaymentsResponse.class))
				.collect(Collectors.toList());
        return paymentsResponse;
    }
    @Override
    public GetByIdPaymentResponse getById(int id) {
        Payment payment = this.paymentRepository.findById(id).orElseThrow();
        GetByIdPaymentResponse response = this.modelMapperService.forResponse()
				.map(payment, GetByIdPaymentResponse.class);
        return response;
    }
    @Override
    public void add(CreatePaymentRequest createPaymentRequest) {
        businessRules.checkIfRentalExists(createPaymentRequest.getRentalId());
        businessRules.checkIfPaymentAlreadyExistsForRental(createPaymentRequest.getRentalId());
        businessRules.validateCardExpiry(createPaymentRequest.getCardExpiryMonth(), createPaymentRequest.getCardExpiryYear());

        Rental rental = rentalRepository.findById(createPaymentRequest.getRentalId()).orElseThrow();

        Payment payment = this.modelMapperService.forRequest()
				.map(createPaymentRequest, Payment.class);

        payment.setRental(rental);
        payment.setAmount(rental.getTotalPrice());
        payment.setPaymentDate(LocalDate.now());

        this.paymentRepository.save(payment);
    }
    @Override
    public void update(UpdatePaymentRequest updatePaymentRequest) {
        Payment payment = this.modelMapperService.forRequest()
				.map(updatePaymentRequest, Payment.class);
         this.paymentRepository.save(payment);
    }
    @Override
    public void deleteById(int id) {
        this.paymentRepository.deleteById(id);
    }

    
}
