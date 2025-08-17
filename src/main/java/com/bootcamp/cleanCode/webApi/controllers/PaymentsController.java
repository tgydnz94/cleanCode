package com.bootcamp.cleanCode.webApi.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bootcamp.cleanCode.business.abstracts.PaymentService;
import com.bootcamp.cleanCode.business.concretes.requests.paymentRequests.CreatePaymentRequest;
import com.bootcamp.cleanCode.business.concretes.requests.paymentRequests.UpdatePaymentRequest;
import com.bootcamp.cleanCode.business.concretes.responses.paymentResponses.GetAllPaymentsResponse;
import com.bootcamp.cleanCode.business.concretes.responses.paymentResponses.GetByIdPaymentResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/payments")
@AllArgsConstructor
public class PaymentsController {
    private PaymentService paymentService;

    @GetMapping("/getall")
    public List<GetAllPaymentsResponse> getAll() {
        return paymentService.getAll();
    }

    @GetMapping("/getbyid/{id}")
    public GetByIdPaymentResponse getById(@PathVariable int id) {
        return paymentService.getById(id);
    }

    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void add(@RequestBody() @Valid() CreatePaymentRequest createPaymentRequest) {
        this.paymentService.add(createPaymentRequest);

    }
    @PutMapping("/update/{id}")
    public void updateCar(@RequestBody() UpdatePaymentRequest updatePaymentRequest){
        this.paymentService.update(updatePaymentRequest);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id) {
        this.paymentService.deleteById(id);
    }
    
}
