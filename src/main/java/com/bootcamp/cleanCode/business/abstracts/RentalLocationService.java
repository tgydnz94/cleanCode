package com.bootcamp.cleanCode.business.abstracts;

import java.util.List;

import com.bootcamp.cleanCode.business.concretes.requests.rentalLocationRequests.CreateRentalLocationRequest;
import com.bootcamp.cleanCode.business.concretes.requests.rentalLocationRequests.UpdateRentalLocationRequest;
import com.bootcamp.cleanCode.business.concretes.responses.rentalLocationResponses.GetAllRentalLocationsResponse;
import com.bootcamp.cleanCode.business.concretes.responses.rentalLocationResponses.GetByIdRentalLocationResponse;

public interface RentalLocationService {
    List<GetAllRentalLocationsResponse> getAll();
    GetByIdRentalLocationResponse getById(int id);
    void add(CreateRentalLocationRequest createRentalLocationRequest);
    void update(UpdateRentalLocationRequest updateRentalLocationRequest);
    void deleteById(int id);
}
