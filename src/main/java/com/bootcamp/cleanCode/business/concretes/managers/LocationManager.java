package com.bootcamp.cleanCode.business.concretes.managers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.bootcamp.cleanCode.business.abstracts.LocationService;
import com.bootcamp.cleanCode.business.concretes.requests.locationRequests.CreateLocationRequest;
import com.bootcamp.cleanCode.business.concretes.requests.locationRequests.UpdateLocationRequest;
import com.bootcamp.cleanCode.business.concretes.responses.locationResponses.GetAllLocationsResponse;
import com.bootcamp.cleanCode.business.concretes.responses.locationResponses.GetByIdLocationResponse;
import com.bootcamp.cleanCode.core.utilities.mappers.ModelMapperService;
import com.bootcamp.cleanCode.dataAccess.abstracts.LocationRepository;
import com.bootcamp.cleanCode.entities.Location;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LocationManager implements LocationService {
    private LocationRepository locationRepository;
    private ModelMapperService modelMapperService;

    @Override
    public List<GetAllLocationsResponse> getAll() {
        List<Location> locations = locationRepository.findAll();
        List<GetAllLocationsResponse> locationResponse = locations.stream()
				.map(location-> this.modelMapperService.forResponse()
				.map(location, GetAllLocationsResponse.class))
				.collect(Collectors.toList());
        return locationResponse;
    }

    @Override
    public GetByIdLocationResponse getById(int id) {
        Location location = this.locationRepository.findById(id).orElseThrow();
        GetByIdLocationResponse response = this.modelMapperService.forResponse()
				.map(location, GetByIdLocationResponse.class);
        return response;
    }

    @Override
    public void add(CreateLocationRequest createLocationRequest) {
        Location location = this.modelMapperService.forRequest()
				.map(createLocationRequest, Location.class);
        this.locationRepository.save(location);
    }

    @Override
    public void update(UpdateLocationRequest updateLocationRequest) {
        Location location = this.modelMapperService.forRequest()
				.map(updateLocationRequest, Location.class);
         this.locationRepository.save(location);
    }

    @Override
    public void deleteById(int id) {
        this.locationRepository.deleteById(id);
    }
    
}
