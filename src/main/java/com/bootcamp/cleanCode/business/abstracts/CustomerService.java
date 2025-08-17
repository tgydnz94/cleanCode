package com.bootcamp.cleanCode.business.abstracts;

import java.util.List;

import com.bootcamp.cleanCode.business.concretes.requests.customerRequests.CreateCustomerRequest;
import com.bootcamp.cleanCode.business.concretes.requests.customerRequests.UpdateCustomerRequest;
import com.bootcamp.cleanCode.business.concretes.responses.customerResponses.GetAllCustomersResponse;
import com.bootcamp.cleanCode.business.concretes.responses.customerResponses.GetByIdCustomerResponse;

public interface CustomerService {
    List<GetAllCustomersResponse> getAll();
    GetByIdCustomerResponse getById(int id);
    void add(CreateCustomerRequest createCustomerRequest);
    void update(UpdateCustomerRequest updateCustomerRequest);
    void deleteById(int id);
}
