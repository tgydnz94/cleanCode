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

import com.bootcamp.cleanCode.business.abstracts.RentalLocationService;
import com.bootcamp.cleanCode.business.concretes.requests.rentalLocationRequests.CreateRentalLocationRequest;
import com.bootcamp.cleanCode.business.concretes.requests.rentalLocationRequests.UpdateRentalLocationRequest;
import com.bootcamp.cleanCode.business.concretes.responses.rentalLocationResponses.GetAllRentalLocationsResponse;
import com.bootcamp.cleanCode.business.concretes.responses.rentalLocationResponses.GetByIdRentalLocationResponse;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/rentallocations")
@AllArgsConstructor
public class RentalLocationsController {
    private RentalLocationService rentalLocationService;

     @GetMapping("/getall")
    public List<GetAllRentalLocationsResponse> getAll() {
        return rentalLocationService.getAll();
    }

    @GetMapping("/getbyid/{id}")
    public GetByIdRentalLocationResponse getById(@PathVariable int id) {
        return rentalLocationService.getById(id);
    }

    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void add(@RequestBody() @Valid() CreateRentalLocationRequest createRentalLocationRequest) {
        this.rentalLocationService.add(createRentalLocationRequest);

    }
    @PutMapping("/update/{id}")
    public void updateCar(@RequestBody() UpdateRentalLocationRequest updateRentalLocationRequest){
        this.rentalLocationService.update(updateRentalLocationRequest);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id) {
        this.rentalLocationService.deleteById(id);
    }
}
