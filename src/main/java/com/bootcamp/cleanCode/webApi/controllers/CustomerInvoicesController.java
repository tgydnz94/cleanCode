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

import com.bootcamp.cleanCode.business.abstracts.CustomerInvoiceService;
import com.bootcamp.cleanCode.business.concretes.requests.customerInvoiceRequests.CreateCustomerInvoiceRequest;
import com.bootcamp.cleanCode.business.concretes.requests.customerInvoiceRequests.UpdateCUstomerInvoiceRequest;
import com.bootcamp.cleanCode.business.concretes.responses.customerInvoiceResponses.GetAllCustomerInvoicesResponse;
import com.bootcamp.cleanCode.business.concretes.responses.customerInvoiceResponses.GetByIdCustomerInvoiceResponse;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/customerinvoices")
@AllArgsConstructor
public class CustomerInvoicesController {
    private CustomerInvoiceService invoiceService;

    @GetMapping("getall")
    public List<GetAllCustomerInvoicesResponse> getAll() {
        return invoiceService.getAll();
    }

    @GetMapping("/getbyid/{id}")
    public GetByIdCustomerInvoiceResponse getById(@PathVariable int id) {
        return invoiceService.getById(id);
    }

    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void add(@RequestBody() @Valid() CreateCustomerInvoiceRequest createRequest) {
        this.invoiceService.add(createRequest);

    }
    @PutMapping("/update")
    public void updateBrand(@RequestBody() UpdateCUstomerInvoiceRequest updateRequest){
        this.invoiceService.update(updateRequest);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id) {
        this.invoiceService.deleteById(id);
    }
}
