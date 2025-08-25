package com.bootcamp.cleanCode.business.abstracts;

import java.util.List;

import com.bootcamp.cleanCode.business.concretes.requests.workerRequests.CreateWorkerRequest;
import com.bootcamp.cleanCode.business.concretes.requests.workerRequests.UpdateWorkerRequest;
import com.bootcamp.cleanCode.business.concretes.responses.workerResponses.GetAllWorkersResponse;
import com.bootcamp.cleanCode.business.concretes.responses.workerResponses.GetByIdWorkerResponse;

public interface WorkerService {
    List<GetAllWorkersResponse> getAll();
    GetByIdWorkerResponse getById(int id);
    void add(CreateWorkerRequest request);
    void update(UpdateWorkerRequest request);
    void deleteById(int id);
}
