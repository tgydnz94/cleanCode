package com.bootcamp.cleanCode.business.abstracts;

import java.util.List;

import com.bootcamp.cleanCode.business.concretes.requests.customerInvoiceRequests.CreateCustomerInvoiceRequest;
import com.bootcamp.cleanCode.business.concretes.requests.customerInvoiceRequests.UpdateCUstomerInvoiceRequest;
import com.bootcamp.cleanCode.business.concretes.responses.customerInvoiceResponses.GetAllCustomerInvoicesResponse;
import com.bootcamp.cleanCode.business.concretes.responses.customerInvoiceResponses.GetByIdCustomerInvoiceResponse;

public interface CustomerInvoiceService {
    List<GetAllCustomerInvoicesResponse> getAll();
    GetByIdCustomerInvoiceResponse getById(int id);
    void add(CreateCustomerInvoiceRequest createCustomerInvoiceRequest);
    void update(UpdateCUstomerInvoiceRequest updateCUstomerInvoiceRequest);
    void deleteById(int id);
}
