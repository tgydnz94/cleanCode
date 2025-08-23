package com.bootcamp.cleanCode.business.concretes.rules;

import org.springframework.stereotype.Service;

import com.bootcamp.cleanCode.dataAccess.abstracts.CompanyInvoiceRepository;
import com.bootcamp.cleanCode.dataAccess.abstracts.RentalRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CompanyInvoiceBusinessRules {
    private CompanyInvoiceRepository invoiceRepository;
    private RentalRepository rentalRepository;

    public void checkIfRentalExists(int rentalId) {
        if(!rentalRepository.existsById(rentalId)){
            throw new RuntimeException("Kiralama kaydı bulunamadı, fatura oluşturulamaz.");
        }

    }

    public void checkIfInvoiceAlreadyExistsForRental(int rentalId){
        if(invoiceRepository.existsById(rentalId)){
            throw new RuntimeException("Bu kiralama işlemi için oluşturulmuş bir fatura var.");
        }
    }
}
