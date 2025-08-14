package com.bootcamp.cleanCode.business.abstracts;

import java.util.List;

import com.bootcamp.cleanCode.business.concretes.requests.fuelRequests.CreateFuelRequest;
import com.bootcamp.cleanCode.business.concretes.requests.fuelRequests.UpdateFuelRequest;
import com.bootcamp.cleanCode.business.concretes.responses.fuelResponses.GetAllFuelsResponse;
import com.bootcamp.cleanCode.business.concretes.responses.fuelResponses.GetByIdFuelResponse;

public interface FuelService {
    List<GetAllFuelsResponse> getAll();
    GetByIdFuelResponse getById(int id);
    void add(CreateFuelRequest createFuelRequest);
    void update(UpdateFuelRequest updateFuelRequest);
    void deleteById(int id);
}
