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

import com.bootcamp.cleanCode.business.abstracts.CarService;
import com.bootcamp.cleanCode.business.concretes.requests.carRequests.CreateCarRequest;
import com.bootcamp.cleanCode.business.concretes.requests.carRequests.UpdateCarRequest;
import com.bootcamp.cleanCode.business.concretes.responses.carResponses.GetAllCarsResponse;
import com.bootcamp.cleanCode.business.concretes.responses.carResponses.GetByIdCarResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/cars")
@AllArgsConstructor
public class CarsController {
    private CarService carService;

    @GetMapping("/getall")
    public List<GetAllCarsResponse> getAll() {
        return carService.getAll();
    }

    @GetMapping("/getbyid/{id}")
    public GetByIdCarResponse getById(@PathVariable int id) {
        return carService.getById(id);
    }

    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void add(@RequestBody() @Valid() CreateCarRequest createCarRequest) {
        this.carService.add(createCarRequest);

    }
    @PutMapping("/update/{id}")
    public void updateCar(@RequestBody() UpdateCarRequest updateCarRequest){
        this.carService.update(updateCarRequest);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id) {
        this.carService.deleteById(id);
    }
    
}

