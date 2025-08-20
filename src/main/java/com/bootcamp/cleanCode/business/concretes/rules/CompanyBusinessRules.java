package com.bootcamp.cleanCode.business.concretes.rules;

import org.springframework.stereotype.Service;

import com.bootcamp.cleanCode.dataAccess.abstracts.CompanyRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CompanyBusinessRules {
    private CompanyRepository companyRepository;

    public void checkIfEmailExists(String email){
        if(companyRepository.existsByEmail(email)){
            throw new RuntimeException("Bu e-mail adresi zaten mevuct.");
        }
    }

    public void checkIfPhoneExists(String phone) {
        if(companyRepository.existsByPhone(phone)){
            throw new RuntimeException("Bu telefon numarası zaten kayıtlı.");
        }

    }
    public void checkIfCompanyExistsById(int id){
        if(!companyRepository.existsById(id)){
            throw new RuntimeException("Aranan sonuç bulunamadı.");
        }

    }
}
