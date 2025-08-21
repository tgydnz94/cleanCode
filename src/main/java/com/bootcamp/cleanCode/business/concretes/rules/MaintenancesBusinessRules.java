package com.bootcamp.cleanCode.business.concretes.rules;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.bootcamp.cleanCode.core.utilities.exceptions.BusinessException;
import com.bootcamp.cleanCode.dataAccess.abstracts.CarRepository;
import com.bootcamp.cleanCode.dataAccess.abstracts.MaintenanceRepository;
import com.bootcamp.cleanCode.entities.Car;
import com.bootcamp.cleanCode.entities.enums.CarState;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MaintenancesBusinessRules {
    private CarRepository carRepository;
    private MaintenanceRepository maintenanceRepository;

    public void checkIfCarExists(int carId) {
        if (!carRepository.existsById(carId)) {
            throw new BusinessException("Araç bulunamadı.");
        }
    }

    public void checkIfCarAlreadyInMaintenance(int carId) {
        boolean exists = maintenanceRepository.existsByCarIdAndReturnDateIsNull(carId);
        if (exists) {
            throw new BusinessException("Araç şu anda bakımda.");
        }
    }

    public void checkIfCarIsRented(int carId) {
        Car car = carRepository.findById(carId).orElseThrow();
        if (car.getState() == CarState.RENTED) {
            throw new BusinessException("Kirada olan araç bakıma alınamaz.");
        }
    }

    public void checkIfMaintenanceExists(int id) {
        if (!maintenanceRepository.existsById(id)) {
            throw new BusinessException("Bakım kaydı bulunamadı.");
        }
    }

    public void validateDates(LocalDate sentDate, LocalDate returnDate) {
        if (returnDate != null && returnDate.isBefore(sentDate)) {
            throw new BusinessException("Dönüş tarihi gönderim tarihinden önce olamaz.");
        }
    }
}
