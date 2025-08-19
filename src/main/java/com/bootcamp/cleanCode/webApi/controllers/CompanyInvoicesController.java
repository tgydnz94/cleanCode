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

import com.bootcamp.cleanCode.business.abstracts.CompanyInvoiceService;
import com.bootcamp.cleanCode.business.concretes.requests.companyInvoiceRequests.CreateCompanyInvoiceRequest;
import com.bootcamp.cleanCode.business.concretes.requests.companyInvoiceRequests.UpdateCompanyInvoiceRequest;
import com.bootcamp.cleanCode.business.concretes.responses.companyInvoiceResponses.GetAllCompanyInvoicesResponse;
import com.bootcamp.cleanCode.business.concretes.responses.companyInvoiceResponses.GetByIdCompanyInvoiceResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/companyinvoices")
@AllArgsConstructor
public class CompanyInvoicesController {
    private CompanyInvoiceService invoiceService;

    @GetMapping("getall")
    public List<GetAllCompanyInvoicesResponse> getAll() {
        return invoiceService.getAll();
    }

    @GetMapping("/getbyid/{id}")
    public GetByIdCompanyInvoiceResponse getById(@PathVariable int id) {
        return invoiceService.getById(id);
    }

    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void add(@RequestBody() @Valid() CreateCompanyInvoiceRequest createRequest) {
        this.invoiceService.add(createRequest);

    }
    @PutMapping("/update")
    public void updateBrand(@RequestBody() UpdateCompanyInvoiceRequest updateRequest){
        this.invoiceService.update(updateRequest);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id) {
        this.invoiceService.deleteById(id);
    }
}
