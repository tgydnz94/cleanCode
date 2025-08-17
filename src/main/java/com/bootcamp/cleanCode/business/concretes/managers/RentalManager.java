package com.bootcamp.cleanCode.business.concretes.managers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.bootcamp.cleanCode.business.abstracts.RentalService;
import com.bootcamp.cleanCode.business.concretes.requests.rentalRequests.CreateRentalRequest;
import com.bootcamp.cleanCode.business.concretes.requests.rentalRequests.UpdateRentalRequest;
import com.bootcamp.cleanCode.business.concretes.responses.rentalResponses.GetAllRentalsResponse;
import com.bootcamp.cleanCode.business.concretes.responses.rentalResponses.GetByIdRentalResponse;
import com.bootcamp.cleanCode.core.utilities.mappers.ModelMapperService;
import com.bootcamp.cleanCode.dataAccess.abstracts.RentalRepository;
import com.bootcamp.cleanCode.entities.Rental;

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class RentalManager implements RentalService{
    private RentalRepository rentalRepository;
    private ModelMapperService modelMapperService;

    @Override
    public List<GetAllRentalsResponse> getAll() {
        List<Rental> rentals = rentalRepository.findAll();
        List<GetAllRentalsResponse> rentalsResponse = rentals.stream()
				.map(rental-> this.modelMapperService.forResponse()
				.map(rental, GetAllRentalsResponse.class))
				.collect(Collectors.toList());
        return rentalsResponse;
    }

    @Override
    public GetByIdRentalResponse getById(int id) {
        Rental rental = this.rentalRepository.findById(id).orElseThrow();
        GetByIdRentalResponse response = this.modelMapperService.forResponse()
				.map(rental, GetByIdRentalResponse.class);
        return response;
    }

    @Override
    public void add(CreateRentalRequest createRentalRequest) {
        Rental rental = this.modelMapperService.forRequest()
				.map(createRentalRequest, Rental.class);
        this.rentalRepository.save(rental);
    }

    @Override
    public void update(UpdateRentalRequest updateRentalRequest) {
        Rental rental = this.modelMapperService.forRequest()
				.map(updateRentalRequest, Rental.class);
         this.rentalRepository.save(rental);
    }

    @Override
    public void deleteById(int id) {
        this.rentalRepository.deleteById(id);
    }
    
}
