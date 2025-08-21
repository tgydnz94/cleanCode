package com.bootcamp.cleanCode.business.concretes.managers;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.bootcamp.cleanCode.business.abstracts.RentalService;
import com.bootcamp.cleanCode.business.concretes.requests.rentalRequests.CreateRentalRequest;
import com.bootcamp.cleanCode.business.concretes.requests.rentalRequests.UpdateRentalRequest;
import com.bootcamp.cleanCode.business.concretes.responses.rentalResponses.GetAllRentalsResponse;
import com.bootcamp.cleanCode.business.concretes.responses.rentalResponses.GetByIdRentalResponse;
import com.bootcamp.cleanCode.business.concretes.rules.RentalBusinessRules;
import com.bootcamp.cleanCode.core.utilities.exceptions.BusinessException;
import com.bootcamp.cleanCode.core.utilities.mappers.ModelMapperService;
import com.bootcamp.cleanCode.dataAccess.abstracts.CarRepository;
import com.bootcamp.cleanCode.dataAccess.abstracts.CustomerRepository;
import com.bootcamp.cleanCode.dataAccess.abstracts.RentalRepository;
import com.bootcamp.cleanCode.entities.Car;
import com.bootcamp.cleanCode.entities.Rental;
import com.bootcamp.cleanCode.entities.enums.CarState;

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class RentalManager implements RentalService{
    private RentalRepository rentalRepository;
    private RentalBusinessRules businessRules;
    private CarRepository carRepository;
    private CustomerRepository customerRepository;
    private ModelMapperService modelMapperService;

    @Override
    public List<GetAllRentalsResponse> getAll() {
        List<Rental> rentals = rentalRepository.findAll();
        List<GetAllRentalsResponse> rentalsResponse = rentals.stream()
        .filter(rental -> {
          try {
                businessRules.checkIfCarReturned(rental.getCar().getId());
                return true;
            } catch (BusinessException e) {
                return false;
              }
        })
				.map(rental-> this.modelMapperService.forResponse()
				.map(rental, GetAllRentalsResponse.class))
				.collect(Collectors.toList());
        return rentalsResponse;
    }

    @Override
    public GetByIdRentalResponse getById(int id) {
        businessRules.checkIfCarReturned(id);
        Rental rental = this.rentalRepository.findById(id).orElseThrow();
        GetByIdRentalResponse response = this.modelMapperService.forResponse()
				.map(rental, GetByIdRentalResponse.class);
      
        return response;
    }

    @Override
    public void add(CreateRentalRequest createRentalRequest) {
      businessRules.checkIfCarExists(createRentalRequest.getCarId());
      businessRules.checkIfCustomerExists(createRentalRequest.getCustomerId());
      businessRules.checkIfCarIsAvailable(createRentalRequest.getCarId());
      businessRules.checkIfRentalDatesAreValid(createRentalRequest
      .getRentDate(), createRentalRequest.getReturnDate());
      businessRules.checkIfCarReturned(createRentalRequest.getCarId());

        Rental rental = this.modelMapperService.forRequest()
				.map(createRentalRequest, Rental.class);

        double days = ChronoUnit.DAYS.between(createRentalRequest.getRentDate(), createRentalRequest.getRentDate());
        double totalPrice = carRepository.findById(createRentalRequest.getCarId())
                            .get().getDailyPrice() * days;
        
        rental.setTotalPrice(totalPrice);

        this.rentalRepository.save(rental);

        //araç durumu güncelleme
        Car car = carRepository.findById(createRentalRequest.getCarId())
                  .orElseThrow();
        car.setState(CarState.RENTED);
        carRepository.save(car);
    }

    @Override
    public void update(UpdateRentalRequest updateRentalRequest) {
        businessRules.checkIfCarExists(updateRentalRequest.getCarId());
        businessRules.checkIfCustomerExists(updateRentalRequest.getCustomerId());
        businessRules.checkIfCarIsAvailable(updateRentalRequest.getCarId());
        businessRules.checkIfRentalDatesAreValid(updateRentalRequest
        .getRentDate(), updateRentalRequest.getReturnDate());
        businessRules.checkIfRentalExistsById(updateRentalRequest.getId());
        businessRules.checkIfCarReturned(updateRentalRequest.getCarId());


        Rental rental = this.modelMapperService.forRequest()
				.map(updateRentalRequest, Rental.class);
        Car car = carRepository.findById(updateRentalRequest.getCarId()).orElseThrow();

        rental.setCar(car);
        rental.setCustomer(customerRepository.findById(updateRentalRequest.getCustomerId()).orElseThrow());
        rental.setRentDate(updateRentalRequest.getRentDate());
        rental.setReturnDate(updateRentalRequest.getReturnDate());

        long days = ChronoUnit.DAYS.between(updateRentalRequest.getRentDate(), updateRentalRequest.getReturnDate());
        double totalPrice = car.getDailyPrice() * days;

        rental.setTotalPrice(totalPrice);

         this.rentalRepository.save(rental);
    }

    @Override
    public void deleteById(int id) {
        this.rentalRepository.deleteById(id);
    }
    
}
