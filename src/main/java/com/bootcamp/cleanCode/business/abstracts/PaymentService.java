package com.bootcamp.cleanCode.business.abstracts;

import java.util.List;

import com.bootcamp.cleanCode.business.concretes.requests.paymentRequests.CreatePaymentRequest;
import com.bootcamp.cleanCode.business.concretes.requests.paymentRequests.UpdatePaymentRequest;
import com.bootcamp.cleanCode.business.concretes.responses.paymentResponses.GetAllPaymentsResponse;
import com.bootcamp.cleanCode.business.concretes.responses.paymentResponses.GetByIdPaymentResponse;

public interface PaymentService {
    List<GetAllPaymentsResponse> getAll();
    GetByIdPaymentResponse getById(int id);
    void add(CreatePaymentRequest createPaymentRequest);
    void update(UpdatePaymentRequest updatePaymentRequest);
    void deleteById(int id);
    
}
