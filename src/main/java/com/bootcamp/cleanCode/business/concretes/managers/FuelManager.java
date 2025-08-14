package com.bootcamp.cleanCode.business.concretes.managers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.bootcamp.cleanCode.business.abstracts.FuelService;
import com.bootcamp.cleanCode.business.concretes.requests.fuelRequests.CreateFuelRequest;
import com.bootcamp.cleanCode.business.concretes.requests.fuelRequests.UpdateFuelRequest;
import com.bootcamp.cleanCode.business.concretes.responses.fuelResponses.GetAllFuelsResponse;
import com.bootcamp.cleanCode.business.concretes.responses.fuelResponses.GetByIdFuelResponse;
import com.bootcamp.cleanCode.core.utilities.mappers.ModelMapperService;
import com.bootcamp.cleanCode.dataAccess.abstracts.FuelRepository;
import com.bootcamp.cleanCode.entities.Fuel;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FuelManager implements FuelService {
    private FuelRepository fuelRepository;
    private ModelMapperService modelMapperService;

    @Override
    public List<GetAllFuelsResponse> getAll() {
        List<Fuel> fuels = fuelRepository.findAll();

        List<GetAllFuelsResponse> fuelsResponse = fuels.stream()
				.map(fuel-> this.modelMapperService.forResponse()
						.map(fuel, GetAllFuelsResponse.class))
				.collect(Collectors.toList());
        return fuelsResponse;
    }

    @Override
    public void add(CreateFuelRequest createFuelRequest) {
        Fuel fuel = this.modelMapperService.forRequest()
				.map(createFuelRequest, Fuel.class);
        this.fuelRepository.save(fuel);
    }

    @Override
    public GetByIdFuelResponse getById(int id) {
        Fuel fuel = this.fuelRepository.findById(id).orElseThrow();
        GetByIdFuelResponse response = this.modelMapperService.forResponse()
				.map(fuel, GetByIdFuelResponse.class);
        return response;
    }

    @Override
    public void update(UpdateFuelRequest updateFuelRequest) {
        Fuel fuel = this.modelMapperService.forRequest()
				.map(updateFuelRequest, Fuel.class);
         this.fuelRepository.save(fuel);
    }

    @Override
    public void deleteById(int id) {
        this.fuelRepository.deleteById(id);
    }
    
}
