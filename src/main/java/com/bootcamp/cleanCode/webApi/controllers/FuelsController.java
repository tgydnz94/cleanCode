package com.bootcamp.cleanCode.webApi.controllers;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bootcamp.cleanCode.business.abstracts.FuelService;
import com.bootcamp.cleanCode.business.concretes.requests.fuelRequests.CreateFuelRequest;
import com.bootcamp.cleanCode.business.concretes.requests.fuelRequests.UpdateFuelRequest;
import com.bootcamp.cleanCode.business.concretes.responses.fuelResponses.GetAllFuelsResponse;
import com.bootcamp.cleanCode.business.concretes.responses.fuelResponses.GetByIdFuelResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/fuels")
@AllArgsConstructor
public class FuelsController {
    private FuelService fuelService;

    @GetMapping("/getall")
    public List<GetAllFuelsResponse> getAll() {
        return fuelService.getAll();
    }

    @GetMapping("/getbyid/{id}")
    public GetByIdFuelResponse getById(@PathVariable int id) {
        return fuelService.getById(id);
    }

    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void add(@RequestBody() @Valid() CreateFuelRequest createFuelRequest) {
        this.fuelService.add(createFuelRequest);

    }
    @PutMapping("/update/{id}")
    public void updateFuel( @RequestBody() UpdateFuelRequest updateFuelRequest){
        this.fuelService.update(updateFuelRequest);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id) {
        this.fuelService.deleteById(id);
    }

    
}