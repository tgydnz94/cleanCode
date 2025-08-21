package com.bootcamp.cleanCode.business.concretes.rules;

import org.springframework.stereotype.Service;

import com.bootcamp.cleanCode.core.utilities.exceptions.BusinessException;
import com.bootcamp.cleanCode.dataAccess.abstracts.BrandRepository;
import com.bootcamp.cleanCode.dataAccess.abstracts.ModelRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ModelBusinessRules {
    private ModelRepository modelRepository;
    private BrandRepository brandRepository;

    public void checkIfModelNameExists(String name) {
        if (modelRepository.existsByNameIgnoreCase(name)) {
            throw new BusinessException("Bu model adı zaten mevcut.");
        }
    }

    public void checkIfModelExistsById(int id) {
        if (!modelRepository.existsById(id)) {
            throw new BusinessException("Model bulunamadı.");
        }
    }

    public void checkIfBrandExists(int brandId) {
        if (!brandRepository.existsById(brandId)) {
            throw new BusinessException("Marka bulunamadı.");
        }
    }
}
