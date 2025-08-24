package com.bootcamp.cleanCode.business.concretes.rules;

import org.springframework.stereotype.Service;

import com.bootcamp.cleanCode.core.utilities.exceptions.BusinessException;
import com.bootcamp.cleanCode.core.utilities.exceptions.NotFoundException;
import com.bootcamp.cleanCode.dataAccess.abstracts.RentalLocationRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RentalLocationBusinessRules {
    private RentalLocationRepository rentalLocationRepository;


    public void checkIfLocationExistsById(int id) {
        if (!this.rentalLocationRepository.existsById(id)) {
            throw new NotFoundException("Bu konum bulunamadı.");
        }
    }

    public void checkIfRentalExistsById(int rentalId) {
        if (!rentalLocationRepository.existsByRentalId(rentalId)) {
            throw new BusinessException("Bu kiralama için kayıt bulunamadı.");
        }
    }
}
