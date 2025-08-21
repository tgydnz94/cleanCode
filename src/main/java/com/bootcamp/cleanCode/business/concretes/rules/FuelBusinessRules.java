package com.bootcamp.cleanCode.business.concretes.rules;

import org.springframework.stereotype.Service;

import com.bootcamp.cleanCode.core.utilities.exceptions.BusinessException;
import com.bootcamp.cleanCode.dataAccess.abstracts.FuelRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FuelBusinessRules {
    private FuelRepository fuelRepository;

    public void checkIfFuelNameExists(String name) {
        if (fuelRepository.existsByNameIgnoreCase(name)) {
            throw new BusinessException("Bu yakıt türü zaten mevcut.");
        }
    }

    public void checkIfFuelIdExists(int id) {
        if (!fuelRepository.existsById(id)) {
            throw new BusinessException("Yakıt türü bulunamadı.");
        }
    }

    public void checkIfFuelLimitExceeded() {
        long count = fuelRepository.count();
        if (count >= 4) {
            throw new BusinessException("En fazla 4 yakıt türü eklenebilir.");
        }
    }
}
