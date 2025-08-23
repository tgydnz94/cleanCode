package com.bootcamp.cleanCode.business.concretes.rules;

import com.bootcamp.cleanCode.core.utilities.exceptions.NotFoundException;
import com.bootcamp.cleanCode.dataAccess.abstracts.RentalLocationRepository;

public class RentalLocationBusinessRules {
    private RentalLocationRepository rentalLocationRepository;


    public void checkIfLocationExistsById(int id) {
        if (!this.rentalLocationRepository.existsById(id)) {
            throw new NotFoundException("Bu konum bulunamadı.");
        }
    }
}
