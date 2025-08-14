package com.bootcamp.cleanCode.business.abstracts;

import java.util.List;

import com.bootcamp.cleanCode.business.concretes.requests.transmissionRequests.CreateTransmissionRequest;
import com.bootcamp.cleanCode.business.concretes.requests.transmissionRequests.UpdateTransmissionRequest;
import com.bootcamp.cleanCode.business.concretes.responses.transmissionResponses.GetAllTransmissionsResponse;
import com.bootcamp.cleanCode.business.concretes.responses.transmissionResponses.GetByIdTransmissionResponse;

public interface TransmissionService {
    List<GetAllTransmissionsResponse> getAll();
    GetByIdTransmissionResponse getById(int id);
    void add(CreateTransmissionRequest createTransmissionRequest);
    void update(UpdateTransmissionRequest updateTransmissionRequest);
    void deleteById(int id);
}
