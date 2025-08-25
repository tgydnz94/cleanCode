package com.bootcamp.cleanCode.business.concretes.managers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.bootcamp.cleanCode.business.abstracts.WorkerService;
import com.bootcamp.cleanCode.business.concretes.requests.workerRequests.CreateWorkerRequest;
import com.bootcamp.cleanCode.business.concretes.requests.workerRequests.UpdateWorkerRequest;
import com.bootcamp.cleanCode.business.concretes.responses.workerResponses.GetAllWorkersResponse;
import com.bootcamp.cleanCode.business.concretes.responses.workerResponses.GetByIdWorkerResponse;
import com.bootcamp.cleanCode.business.concretes.rules.WorkerBusinessRules;
import com.bootcamp.cleanCode.core.utilities.mappers.ModelMapperService;
import com.bootcamp.cleanCode.dataAccess.abstracts.WorkerRepository;
import com.bootcamp.cleanCode.entities.Worker;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class WorkerManager implements WorkerService {

    private WorkerRepository workerRepository;
    private WorkerBusinessRules businessRules;
    private ModelMapperService modelMapperService;

    @Override
    public List<GetAllWorkersResponse> getAll() {
        List<Worker> workers = workerRepository.findAll();

        List<GetAllWorkersResponse> workersResponses = workers.stream()
				.map(worker-> this.modelMapperService.forResponse()
				.map(worker, GetAllWorkersResponse.class))
				.collect(Collectors.toList());
        return workersResponses;
    }

    @Override
    public GetByIdWorkerResponse getById(int id) {
        businessRules.checkIfWorkerExistsById(id);
        Worker worker = workerRepository.findById(id).orElseThrow();
        return modelMapperService.forResponse().map(worker, GetByIdWorkerResponse.class);
    }

    @Override
    public void add(CreateWorkerRequest request) {
        businessRules.checkIfEmailExists(request.getEmail());
        businessRules.checkIfPhoneNumberExists(request.getPhoneNumber());

        Worker worker = modelMapperService.forRequest().map(request, Worker.class);
        workerRepository.save(worker);
    }

    @Override
    public void update(UpdateWorkerRequest request) {
        businessRules.checkIfWorkerExistsById(request.getId());
        
        Worker worker = modelMapperService.forRequest().map(request, Worker.class);
        workerRepository.save(worker);
    }

    @Override
    public void deleteById(int id) {
        businessRules.checkIfWorkerExistsById(id);
        workerRepository.deleteById(id);
    }
}
