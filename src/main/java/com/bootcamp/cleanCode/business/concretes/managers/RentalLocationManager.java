package com.bootcamp.cleanCode.business.concretes.managers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.bootcamp.cleanCode.business.abstracts.RentalLocationService;
import com.bootcamp.cleanCode.business.concretes.requests.rentalLocationRequests.CreateRentalLocationRequest;
import com.bootcamp.cleanCode.business.concretes.requests.rentalLocationRequests.UpdateRentalLocationRequest;
import com.bootcamp.cleanCode.business.concretes.responses.rentalLocationResponses.GetAllRentalLocationsResponse;
import com.bootcamp.cleanCode.business.concretes.responses.rentalLocationResponses.GetByIdRentalLocationResponse;
import com.bootcamp.cleanCode.business.concretes.rules.RentalLocationBusinessRules;
import com.bootcamp.cleanCode.core.utilities.mappers.ModelMapperService;
import com.bootcamp.cleanCode.dataAccess.abstracts.RentalLocationRepository;
import com.bootcamp.cleanCode.entities.Location;
import com.bootcamp.cleanCode.entities.Rental;
import com.bootcamp.cleanCode.entities.RentalLocation;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RentalLocationManager implements RentalLocationService {
    private RentalLocationRepository rentalLocationRepository;
    private RentalLocationBusinessRules businessRules;
    private ModelMapperService modelMapperService;
    
    @Override
    public List<GetAllRentalLocationsResponse> getAll() {
        List<RentalLocation> locations = rentalLocationRepository.findAll();
        List<GetAllRentalLocationsResponse> locationResponse = locations.stream()
				.map(location-> this.modelMapperService.forResponse()
				.map(location, GetAllRentalLocationsResponse.class))
				.collect(Collectors.toList());
        return locationResponse;
    }

    @Override
    public GetByIdRentalLocationResponse getById(int id) {
        RentalLocation location = this.rentalLocationRepository.findById(id).orElseThrow();
        GetByIdRentalLocationResponse response = this.modelMapperService.forResponse()
				.map(location, GetByIdRentalLocationResponse.class);
        return response;
    }

    @Override
    public void add(CreateRentalLocationRequest createRentalLocationRequest) {
      businessRules.checkIfLocationExistsById(createRentalLocationRequest.getLocaitonId());

        RentalLocation rentalLocation = this.modelMapperService.forRequest()
				.map(createRentalLocationRequest, RentalLocation.class);

        Rental rental = new Rental();
        rentalLocation.setRental(rental);

        Location location = new Location(); 
        rentalLocation.setLocation(location);


        rentalLocation.setTimeStamp(LocalDateTime.now());
        this.rentalLocationRepository.save(rentalLocation);
    }

    @Override
    public void update(UpdateRentalLocationRequest updateRentalLocationRequest) {
      businessRules.checkIfLocationExistsById(updateRentalLocationRequest.getLocaitonId());

        RentalLocation rentalLocation = this.modelMapperService.forRequest()
				.map(updateRentalLocationRequest, RentalLocation.class);

        Rental rental = new Rental();
        rentalLocation.setRental(rental);

        Location location = new Location();
        rentalLocation.setLocation(location);

        rentalLocation.setTimeStamp(LocalDateTime.now());
        

         this.rentalLocationRepository.save(rentalLocation);
    }

    @Override
    public void deleteById(int id) {
        this.rentalLocationRepository.deleteById(id);
    }
    
}
