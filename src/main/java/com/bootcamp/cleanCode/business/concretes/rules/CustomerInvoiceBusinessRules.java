package com.bootcamp.cleanCode.business.concretes.rules;

import com.bootcamp.cleanCode.core.utilities.exceptions.BusinessException;
import com.bootcamp.cleanCode.dataAccess.abstracts.CustomerInvoiceRepository;
import com.bootcamp.cleanCode.dataAccess.abstracts.RentalRepository;

public class CustomerInvoiceBusinessRules {
    private RentalRepository rentalRepository;
    private CustomerInvoiceRepository customerInvoiceRepository;

    public void checkIfRentalExists(int rentalId) {
        if (!rentalRepository.existsById(rentalId)) {
            throw new BusinessException("Kiralama bilgisi bulunamadı.");
        }
    }

    public void checkIfInvoiceAlreadyCreatedForRental(int rentalId) {
        if (customerInvoiceRepository.existsByRentalId(rentalId)) {
            throw new BusinessException("Bu kiralama için fatura zaten oluşturulmuş.");
        }
    }
    
}
