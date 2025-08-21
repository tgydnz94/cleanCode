package com.bootcamp.cleanCode.business.concretes.rules;

import com.bootcamp.cleanCode.core.utilities.exceptions.BusinessException;
import com.bootcamp.cleanCode.dataAccess.abstracts.CustomerRepository;

public class CustomerBusinessRules {
    private CustomerRepository customerRepository;

    public void checkIfEmailExists(String email) {
        if (customerRepository.existsByEmail(email)) {
            throw new BusinessException("Bu email adresi zaten kayıtlı.");
        }
    }

    public void checkIfCustomerExists(int id) {
        if (!customerRepository.existsById(id)) {
            throw new BusinessException("Müşteri bulunamadı.");
        }
    }
}
