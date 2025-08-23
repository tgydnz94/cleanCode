package com.bootcamp.cleanCode.business.concretes.rules;

import org.springframework.stereotype.Service;

import com.bootcamp.cleanCode.dataAccess.abstracts.TransmissionRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TransmissionBusinessRules {
    private TransmissionRepository transmissionRepository;

    public void checkIfTransmissionNameExist(String name) {
        if(transmissionRepository.existsByName(name)){
            throw new RuntimeException("Vites türü mevcut.");
        }

    }

    public void checkIfLimitExceeded() {
        long count = transmissionRepository.count();
        if(count>=3) {
            throw new RuntimeException("En fazla iki vites türü girilebilir.");
        }
    }
}
