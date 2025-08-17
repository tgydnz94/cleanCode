package com.bootcamp.cleanCode.business.abstracts;

import java.util.List;

import com.bootcamp.cleanCode.business.concretes.requests.rentalRequests.CreateRentalRequest;
import com.bootcamp.cleanCode.business.concretes.requests.rentalRequests.UpdateRentalRequest;
import com.bootcamp.cleanCode.business.concretes.responses.rentalResponses.GetAllRentalsResponse;
import com.bootcamp.cleanCode.business.concretes.responses.rentalResponses.GetByIdRentalResponse;

public interface RentalService {
    List<GetAllRentalsResponse> getAll();
    GetByIdRentalResponse getById(int id);
    void add(CreateRentalRequest createRentalRequest);
    void update(UpdateRentalRequest updateRentalRequest);
    void deleteById(int id);
}
