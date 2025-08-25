package com.bootcamp.cleanCode.webApi.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bootcamp.cleanCode.business.abstracts.ModelService;
import com.bootcamp.cleanCode.business.concretes.requests.modelRequests.CreateModelRequest;
import com.bootcamp.cleanCode.business.concretes.requests.modelRequests.UpdateModelRequest;
import com.bootcamp.cleanCode.business.concretes.responses.modelResponses.GetAllModelsResponse;
import com.bootcamp.cleanCode.business.concretes.responses.modelResponses.GetByIdModelResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
@RestController
@RequestMapping("/api/models")
@AllArgsConstructor
public class ModelsController {
    private ModelService modelService;

    @GetMapping("getall")
    public List<GetAllModelsResponse> getAll() {
        return modelService.getAll();
    }

    @GetMapping("/getbyid/{id}")
    public GetByIdModelResponse getById(@PathVariable int id) {
        return modelService.getById(id);
    }

    @PreAuthorize("hasRole('COMPANY')")
    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void add(@RequestBody() @Valid() CreateModelRequest createModelRequest) {
        this.modelService.add(createModelRequest);

    }

    @PreAuthorize("hasRole('COMPANY')")
    @PutMapping("/update")
    public void updateModel(@RequestBody() UpdateModelRequest updateModelRequest){
        this.modelService.update(updateModelRequest);
    }

    @PreAuthorize("hasRole('COMPANY')")
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id) {
        this.modelService.deleteById(id);
    }
    
}
