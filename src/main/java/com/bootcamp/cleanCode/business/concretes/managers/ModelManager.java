package com.bootcamp.cleanCode.business.concretes.managers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.bootcamp.cleanCode.business.abstracts.ModelService;
import com.bootcamp.cleanCode.business.concretes.requests.modelRequests.CreateModelRequest;
import com.bootcamp.cleanCode.business.concretes.requests.modelRequests.UpdateModelRequest;
import com.bootcamp.cleanCode.business.concretes.responses.modelResponses.GetAllModelsResponse;
import com.bootcamp.cleanCode.business.concretes.responses.modelResponses.GetByIdModelResponse;
import com.bootcamp.cleanCode.core.utilities.mappers.ModelMapperService;
import com.bootcamp.cleanCode.dataAccess.abstracts.ModelRepository;
import com.bootcamp.cleanCode.entities.Model;

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class ModelManager implements ModelService {
    private ModelRepository modelRepository;
    private ModelMapperService modelMapperService;

        @Override
    public List<GetAllModelsResponse> getAll() {
        List<Model> models = modelRepository.findAll();

        List<GetAllModelsResponse> modelsResponse = models.stream()
				.map(model-> this.modelMapperService.forResponse()
				.map(model, GetAllModelsResponse.class))
				.collect(Collectors.toList());
        return modelsResponse;
    }

    @Override
    public void add(CreateModelRequest createModelRequest) {
        Model model = this.modelMapperService.forRequest()
				.map(createModelRequest, Model.class);

        this.modelRepository.save(model);
    }

    @Override
    public GetByIdModelResponse getById(int id) {
        Model model = this.modelRepository.findById(id).orElseThrow();
        GetByIdModelResponse response = this.modelMapperService.forResponse()
				.map(model, GetByIdModelResponse.class);
        return response;
    }

    @Override
    public void update(UpdateModelRequest updateModelRequest) {
       Model model = this.modelMapperService.forRequest()
				.map(updateModelRequest, Model.class);
		
		this.modelRepository.save(model);
    }

    @Override
    public void deleteById(int id) {
        this.modelRepository.deleteById(id);
    }
    
}
