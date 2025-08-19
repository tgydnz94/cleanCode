package com.bootcamp.cleanCode.business.concretes.managers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.bootcamp.cleanCode.business.abstracts.CompanyInvoiceService;
import com.bootcamp.cleanCode.business.concretes.requests.companyInvoiceRequests.CreateCompanyInvoiceRequest;
import com.bootcamp.cleanCode.business.concretes.requests.companyInvoiceRequests.UpdateCompanyInvoiceRequest;
import com.bootcamp.cleanCode.business.concretes.responses.companyInvoiceResponses.GetAllCompanyInvoicesResponse;
import com.bootcamp.cleanCode.business.concretes.responses.companyInvoiceResponses.GetByIdCompanyInvoiceResponse;
import com.bootcamp.cleanCode.core.utilities.mappers.ModelMapperService;
import com.bootcamp.cleanCode.dataAccess.abstracts.CompanyInvoiceRepository;
import com.bootcamp.cleanCode.entities.CompanyInvoice;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CompanyInvoiceManager implements CompanyInvoiceService {
    private CompanyInvoiceRepository companyInvoiceRepository;
    private ModelMapperService modelMapperService;

    @Override
    public List<GetAllCompanyInvoicesResponse> getAll() {
        List<CompanyInvoice> invoices = companyInvoiceRepository.findAll();
        List<GetAllCompanyInvoicesResponse> invoiceResponse = invoices.stream()
				.map(invoice-> this.modelMapperService.forResponse()
				.map(invoice, GetAllCompanyInvoicesResponse.class))
				.collect(Collectors.toList());
        return invoiceResponse;
    }

    @Override
    public GetByIdCompanyInvoiceResponse getById(int id) {
        CompanyInvoice invoice = this.companyInvoiceRepository.findById(id).orElseThrow();
        GetByIdCompanyInvoiceResponse response = this.modelMapperService.forResponse()
				.map(invoice, GetByIdCompanyInvoiceResponse.class);
        return response;
    }

    @Override
    public void add(CreateCompanyInvoiceRequest createCompanyInvoiceRequest) {
        CompanyInvoice invoice = this.modelMapperService.forRequest()
				.map(createCompanyInvoiceRequest, CompanyInvoice.class);
        this.companyInvoiceRepository.save(invoice);
    }

    @Override
    public void update(UpdateCompanyInvoiceRequest updateCompanyInvoiceRequest) {
        CompanyInvoice invoice = this.modelMapperService.forRequest()
				.map(updateCompanyInvoiceRequest, CompanyInvoice.class);
         this.companyInvoiceRepository.save(invoice);
    }

    @Override
    public void deleteById(int id) {
        this.companyInvoiceRepository.deleteById(id);
    }
    
}
