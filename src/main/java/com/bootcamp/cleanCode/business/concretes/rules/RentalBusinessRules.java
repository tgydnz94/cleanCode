package com.bootcamp.cleanCode.business.concretes.rules;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.bootcamp.cleanCode.core.utilities.exceptions.BusinessException;
import com.bootcamp.cleanCode.dataAccess.abstracts.CarRepository;
import com.bootcamp.cleanCode.dataAccess.abstracts.CustomerRepository;
import com.bootcamp.cleanCode.dataAccess.abstracts.RentalRepository;
import com.bootcamp.cleanCode.entities.Car;
import com.bootcamp.cleanCode.entities.enums.CarState;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RentalBusinessRules {
    private CarRepository carRepository;
    private CustomerRepository customerRepository;
    private RentalRepository rentalRepository;

    public void checkIfCarExists(int carId) {
        if (!carRepository.existsById(carId)) {
            throw new BusinessException("Araç bulunamadı.");
        }
    }

    public void checkIfCustomerExists(int customerId) {
        if (!customerRepository.existsById(customerId)) {
            throw new BusinessException("Müşteri bulunamadı.");
        }
    }

    public void checkIfCarIsAvailable(int carId) {
        Car car = carRepository.findById(carId).orElseThrow();
        if (car.getState() != CarState.AVAILABLE) {
            throw new BusinessException("Araç şu anda kiralanamaz durumda.");
        }
    }

    public void checkIfRentalDatesAreValid(LocalDate rentDate, LocalDate returnDate) {
        if (returnDate.isBefore(rentDate)) {
            throw new BusinessException("Teslim tarihi, kiralama tarihinden önce olamaz.");
        }
    }

    public void checkIfRentalExistsById(int id) {
        if (!rentalRepository.existsById(id)) {
            throw new BusinessException("Kiralama kaydı bulunamadı.");
        }
    }

    public void checkIfCarReturned(int carId) {
    LocalDate today = LocalDate.now();
    boolean isStillRented = rentalRepository.checkCarIfReturned(carId, today);

        if (isStillRented) {
            throw new BusinessException("Araç hâlâ kullanımda, yeniden kiralanamaz.");
        }
    }

}