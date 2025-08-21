package com.bootcamp.cleanCode.business.concretes.rules;

import org.springframework.stereotype.Service;

import com.bootcamp.cleanCode.core.utilities.exceptions.BusinessException;
import com.bootcamp.cleanCode.dataAccess.abstracts.LocationRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LocationBusinessRules {
    private LocationRepository locationRepository;

    public void checkIfLocationExists(int id) {
        if (!locationRepository.existsById(id)) {
            throw new BusinessException("Konum bulunamadı.");
        }
    }
}
