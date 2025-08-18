package com.bootcamp.cleanCode.business.abstracts;

import java.util.List;

import com.bootcamp.cleanCode.business.concretes.requests.locationRequests.CreateLocationRequest;
import com.bootcamp.cleanCode.business.concretes.requests.locationRequests.UpdateLocationRequest;
import com.bootcamp.cleanCode.business.concretes.responses.locationResponses.GetAllLocationsResponse;
import com.bootcamp.cleanCode.business.concretes.responses.locationResponses.GetByIdLocationResponse;

public interface LocationService {
    List<GetAllLocationsResponse> getAll();
    GetByIdLocationResponse getById(int id);
    void add(CreateLocationRequest createLocationRequest);
    void update(UpdateLocationRequest updateLocationRequest);
    void deleteById(int id);
}
