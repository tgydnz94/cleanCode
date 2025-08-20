package com.bootcamp.cleanCode.business.concretes.rules;

import org.springframework.stereotype.Service;

import com.bootcamp.cleanCode.core.utilities.exceptions.BusinessException;
import com.bootcamp.cleanCode.core.utilities.exceptions.NotFoundException;
import com.bootcamp.cleanCode.dataAccess.abstracts.CarRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CarBusinessRules {
    private CarRepository carRepository;

    public void checkIfPlateExists(String plate){
        if(this.carRepository.existsByPlate(plate)){
            throw new BusinessException("Plate already exists.");
        }
    }

    public void checkIfCarIdExists(int id){
        if(!this.carRepository.existsById(id)){
            throw new NotFoundException("Car is not foun.");
        }
    }
    public void checkIfCompanyCarLimitExceeded(int companyId) {
        int carCount = carRepository.countByCompanyId(companyId);
        if(carCount>=5) {
            throw new RuntimeException("Bir şirket en fazla 5 tane araba ekleyebilir.");
        }
    }
    
}
