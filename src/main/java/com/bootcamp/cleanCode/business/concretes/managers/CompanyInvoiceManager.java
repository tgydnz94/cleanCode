package com.bootcamp.cleanCode.business.concretes.managers;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.bootcamp.cleanCode.business.abstracts.CompanyInvoiceService;
import com.bootcamp.cleanCode.business.concretes.requests.companyInvoiceRequests.CreateCompanyInvoiceRequest;
import com.bootcamp.cleanCode.business.concretes.requests.companyInvoiceRequests.UpdateCompanyInvoiceRequest;
import com.bootcamp.cleanCode.business.concretes.responses.companyInvoiceResponses.GetAllCompanyInvoicesResponse;
import com.bootcamp.cleanCode.business.concretes.responses.companyInvoiceResponses.GetByIdCompanyInvoiceResponse;
import com.bootcamp.cleanCode.business.concretes.rules.CompanyInvoiceBusinessRules;
import com.bootcamp.cleanCode.core.utilities.mappers.ModelMapperService;
import com.bootcamp.cleanCode.dataAccess.abstracts.CompanyInvoiceRepository;
import com.bootcamp.cleanCode.dataAccess.abstracts.RentalRepository;
import com.bootcamp.cleanCode.entities.CompanyInvoice;
import com.bootcamp.cleanCode.entities.Rental;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CompanyInvoiceManager implements CompanyInvoiceService {
    private CompanyInvoiceRepository companyInvoiceRepository;
    private RentalRepository rentalRepository;
    private ModelMapperService modelMapperService;
    private CompanyInvoiceBusinessRules businessRules;

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
      businessRules.checkIfRentalExists(createCompanyInvoiceRequest.getRentalId());
      businessRules.checkIfInvoiceAlreadyExistsForRental(createCompanyInvoiceRequest.getRentalId());

      Rental rental = rentalRepository.findById(createCompanyInvoiceRequest.getRentalId()).orElseThrow();

      double total = rental.getTotalPrice();
      double commission = total * 0.03;
      double net = total - commission;

        CompanyInvoice invoice = new CompanyInvoice();
        invoice.setRental(rental);
        invoice.setInvoiceDate(LocalDate.now());
        invoice.setTotalAmount(total);
        invoice.setCommmissionAmount(commission);
        invoice.setNetAmount(net);

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
