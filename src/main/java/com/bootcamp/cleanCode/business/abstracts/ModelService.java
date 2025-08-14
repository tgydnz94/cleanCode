package com.bootcamp.cleanCode.business.abstracts;

import java.util.List;

import com.bootcamp.cleanCode.business.concretes.requests.modelRequests.CreateModelRequest;
import com.bootcamp.cleanCode.business.concretes.requests.modelRequests.UpdateModelRequest;
import com.bootcamp.cleanCode.business.concretes.responses.modelResponses.GetAllModelsResponse;
import com.bootcamp.cleanCode.business.concretes.responses.modelResponses.GetByIdModelResponse;

public interface ModelService {
    List<GetAllModelsResponse> getAll();
    GetByIdModelResponse getById(int id);
    void add(CreateModelRequest createModelRequest);
    void update(UpdateModelRequest updateModelRequest);
    void deleteById(int id);
    
}
