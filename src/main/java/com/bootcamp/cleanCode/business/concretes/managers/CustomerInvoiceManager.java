package com.bootcamp.cleanCode.business.concretes.managers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.bootcamp.cleanCode.business.abstracts.CustomerInvoiceService;
import com.bootcamp.cleanCode.business.concretes.requests.customerInvoiceRequests.CreateCustomerInvoiceRequest;
import com.bootcamp.cleanCode.business.concretes.requests.customerInvoiceRequests.UpdateCUstomerInvoiceRequest;
import com.bootcamp.cleanCode.business.concretes.responses.customerInvoiceResponses.GetAllCustomerInvoicesResponse;
import com.bootcamp.cleanCode.business.concretes.responses.customerInvoiceResponses.GetByIdCustomerInvoiceResponse;
import com.bootcamp.cleanCode.business.concretes.rules.CustomerInvoiceBusinessRules;
import com.bootcamp.cleanCode.core.utilities.mappers.ModelMapperService;
import com.bootcamp.cleanCode.dataAccess.abstracts.CustomerInvoiceRepository;
import com.bootcamp.cleanCode.dataAccess.abstracts.RentalRepository;
import com.bootcamp.cleanCode.entities.CustomerInvoice;
import com.bootcamp.cleanCode.entities.Rental;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomerInvoiceManager implements CustomerInvoiceService {
    private CustomerInvoiceRepository customerInvoiceRepository;
    private CustomerInvoiceBusinessRules businessRules;
    private RentalRepository rentalRepository;
    private ModelMapperService modelMapperService;

    @Override
    public List<GetAllCustomerInvoicesResponse> getAll() {
        List<CustomerInvoice> invoices = customerInvoiceRepository.findAll();
        List<GetAllCustomerInvoicesResponse> invoiceResponse = invoices.stream()
				.map(invoice-> this.modelMapperService.forResponse()
				.map(invoice, GetAllCustomerInvoicesResponse.class))
				.collect(Collectors.toList());
        return invoiceResponse;
    }

    @Override
    public GetByIdCustomerInvoiceResponse getById(int id) {
        CustomerInvoice invoice = this.customerInvoiceRepository.findById(id).orElseThrow();
        GetByIdCustomerInvoiceResponse response = this.modelMapperService.forResponse()
				.map(invoice, GetByIdCustomerInvoiceResponse.class);
        return response;
    }

    @Override
    public void add(CreateCustomerInvoiceRequest createCustomerInvoiceRequest) {
      businessRules.checkIfRentalExists(createCustomerInvoiceRequest.getRentalId());
      businessRules.checkIfInvoiceAlreadyCreatedForRental(createCustomerInvoiceRequest.getRentalId());

        Rental rental = rentalRepository.findById(createCustomerInvoiceRequest.getRentalId()).orElseThrow();

        CustomerInvoice invoice = this.modelMapperService.forRequest()
				.map(createCustomerInvoiceRequest, CustomerInvoice.class);
        invoice.setAmount(rental.getTotalPrice());
        this.customerInvoiceRepository.save(invoice);
    }

    @Override
    public void update(UpdateCUstomerInvoiceRequest updateCUstomerInvoiceRequest) {
        CustomerInvoice invoice = this.modelMapperService.forRequest()
				.map(updateCUstomerInvoiceRequest, CustomerInvoice.class);
         this.customerInvoiceRepository.save(invoice);
    }

    @Override
    public void deleteById(int id) {
        this.customerInvoiceRepository.deleteById(id);
    }
    
}
