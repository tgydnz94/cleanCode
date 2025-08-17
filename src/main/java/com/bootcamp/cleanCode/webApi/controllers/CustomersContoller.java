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

import com.bootcamp.cleanCode.business.abstracts.CustomerService;
import com.bootcamp.cleanCode.business.concretes.requests.customerRequests.CreateCustomerRequest;
import com.bootcamp.cleanCode.business.concretes.requests.customerRequests.UpdateCustomerRequest;
import com.bootcamp.cleanCode.business.concretes.responses.customerResponses.GetAllCustomersResponse;
import com.bootcamp.cleanCode.business.concretes.responses.customerResponses.GetByIdCustomerResponse;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/customers")
@AllArgsConstructor
public class CustomersContoller {
    private CustomerService customerService;

     @GetMapping("/getall")
    public List<GetAllCustomersResponse> getAll() {
        return customerService.getAll();
    }

    @GetMapping("/getbyid/{id}")
    public GetByIdCustomerResponse getById(@PathVariable int id) {
        return customerService.getById(id);
    }

    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void add(@RequestBody() @Valid() CreateCustomerRequest createCustomerRequest) {
        this.customerService.add(createCustomerRequest);

    }
    @PutMapping("/update/{id}")
    public void updateCar(@RequestBody() UpdateCustomerRequest updateCustomerRequest){
        this.customerService.update(updateCustomerRequest);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id) {
        this.customerService.deleteById(id);
    }
    
}
