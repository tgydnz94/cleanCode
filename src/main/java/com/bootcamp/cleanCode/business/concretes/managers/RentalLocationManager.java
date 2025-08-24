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
import com.bootcamp.cleanCode.core.utilities.exceptions.BusinessException;
import com.bootcamp.cleanCode.core.utilities.mappers.ModelMapperService;
import com.bootcamp.cleanCode.dataAccess.abstracts.LocationRepository;
import com.bootcamp.cleanCode.dataAccess.abstracts.RentalLocationRepository;
import com.bootcamp.cleanCode.dataAccess.abstracts.RentalRepository;
import com.bootcamp.cleanCode.entities.Location;
import com.bootcamp.cleanCode.entities.Rental;
import com.bootcamp.cleanCode.entities.RentalLocation;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RentalLocationManager implements RentalLocationService {
    private RentalLocationRepository rentalLocationRepository;
    private RentalLocationBusinessRules businessRules;
    private RentalRepository rentalRepository;
    private LocationRepository locationRepository;
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
      businessRules.checkIfLocationExistsById(createRentalLocationRequest.getLocationId());
      businessRules.checkIfRentalExistsById(createRentalLocationRequest.getRentalId());

      Rental rental = rentalRepository.findById(createRentalLocationRequest.getRentalId())
      .orElseThrow(() -> new BusinessException("Kiralama bulunamadı."));

      Location location = locationRepository.findById(createRentalLocationRequest.getLocationId())
      .orElseThrow(() -> new BusinessException("Lokasyon bulunamadı."));

        RentalLocation rentalLocation = this.modelMapperService.forRequest()
				.map(createRentalLocationRequest, RentalLocation.class);

        rentalLocation.setRental(rental);
        rentalLocation.setLocation(location);



        rentalLocation.setTimeStamp(LocalDateTime.now());
        this.rentalLocationRepository.save(rentalLocation);
    }

    @Override
    public void update(UpdateRentalLocationRequest updateRentalLocationRequest) {
      businessRules.checkIfLocationExistsById(updateRentalLocationRequest.getLocationId());
      businessRules.checkIfRentalExistsById(updateRentalLocationRequest.getRentalId());

      Rental rental = rentalRepository.findById(updateRentalLocationRequest.getRentalId())
      .orElseThrow(() -> new BusinessException("Kiralama bulunamadı."));

      Location location = locationRepository.findById(updateRentalLocationRequest.getLocationId())
      .orElseThrow(() -> new BusinessException("Lokasyon bulunamadı."));

        RentalLocation rentalLocation = this.modelMapperService.forRequest()
				.map(updateRentalLocationRequest, RentalLocation.class);

        rentalLocation.setRental(rental);
        rentalLocation.setLocation(location);


        rentalLocation.setTimeStamp(LocalDateTime.now());
        

         this.rentalLocationRepository.save(rentalLocation);
    }

    @Override
    public void deleteById(int id) {
        this.rentalLocationRepository.deleteById(id);
    }
    
}
