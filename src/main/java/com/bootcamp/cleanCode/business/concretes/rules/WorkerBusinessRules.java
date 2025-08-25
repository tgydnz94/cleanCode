package com.bootcamp.cleanCode.business.concretes.rules;

import org.springframework.stereotype.Service;

import com.bootcamp.cleanCode.core.utilities.exceptions.BusinessException;
import com.bootcamp.cleanCode.dataAccess.abstracts.WorkerRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class WorkerBusinessRules {
    private WorkerRepository workerRepository;

    public void checkIfWorkerExistsById(int id) {
        if (!workerRepository.existsById(id)) {
            throw new BusinessException("Çalışan bulunamadı.");
        }
    }

    public void checkIfEmailExists(String email) {
        if (workerRepository.existsByEmailIgnoreCase(email)) {
            throw new BusinessException("Bu e-posta adresi zaten kullanılıyor.");
        }
    }

    public void checkIfPhoneNumberExists(String phoneNumber) {
        if (workerRepository.existsByPhoneNumber(phoneNumber)) {
            throw new BusinessException("Bu telefon numarası zaten kayıtlı.");
        }
    }
}
