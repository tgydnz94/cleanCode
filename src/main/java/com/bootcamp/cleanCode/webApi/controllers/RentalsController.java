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

import com.bootcamp.cleanCode.business.abstracts.RentalService;
import com.bootcamp.cleanCode.business.concretes.requests.rentalRequests.CreateRentalRequest;
import com.bootcamp.cleanCode.business.concretes.requests.rentalRequests.UpdateRentalRequest;
import com.bootcamp.cleanCode.business.concretes.responses.rentalResponses.GetAllRentalsResponse;
import com.bootcamp.cleanCode.business.concretes.responses.rentalResponses.GetByIdRentalResponse;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/rentals")
@AllArgsConstructor
public class RentalsController {
    private RentalService rentalService;

     @GetMapping("/getall")
    public List<GetAllRentalsResponse> getAll() {
        return rentalService.getAll();
    }

    @GetMapping("/getbyid/{id}")
    public GetByIdRentalResponse getById(@PathVariable int id) {
        return rentalService.getById(id);
    }

    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void add(@RequestBody() @Valid() CreateRentalRequest createRentalRequest) {
        this.rentalService.add(createRentalRequest);

    }
    @PutMapping("/update/{id}")
    public void updateCar(@RequestBody() UpdateRentalRequest updateRentalRequest){
        this.rentalService.update(updateRentalRequest);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id) {
        this.rentalService.deleteById(id);
    }
    
}
