package com.bootcamp.cleanCode.business.abstracts;

import java.util.List;

import com.bootcamp.cleanCode.business.concretes.requests.companyInvoiceRequests.CreateCompanyInvoiceRequest;
import com.bootcamp.cleanCode.business.concretes.requests.companyInvoiceRequests.UpdateCompanyInvoiceRequest;
import com.bootcamp.cleanCode.business.concretes.responses.companyInvoiceResponses.GetAllCompanyInvoicesResponse;
import com.bootcamp.cleanCode.business.concretes.responses.companyInvoiceResponses.GetByIdCompanyInvoiceResponse;

public interface CompanyInvoiceService {
    List<GetAllCompanyInvoicesResponse> getAll();
    GetByIdCompanyInvoiceResponse getById(int id);
    void add(CreateCompanyInvoiceRequest createCompanyInvoiceRequest);
    void update(UpdateCompanyInvoiceRequest updateCompanyInvoiceRequest);
    void deleteById(int id);
}
