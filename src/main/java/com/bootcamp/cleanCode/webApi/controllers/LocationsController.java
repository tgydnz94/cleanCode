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

import com.bootcamp.cleanCode.business.abstracts.LocationService;
import com.bootcamp.cleanCode.business.concretes.requests.locationRequests.CreateLocationRequest;
import com.bootcamp.cleanCode.business.concretes.requests.locationRequests.UpdateLocationRequest;
import com.bootcamp.cleanCode.business.concretes.responses.locationResponses.GetAllLocationsResponse;
import com.bootcamp.cleanCode.business.concretes.responses.locationResponses.GetByIdLocationResponse;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;


@RestController
@RequestMapping("/api/locations")
@AllArgsConstructor
public class LocationsController {
    private LocationService locationService;

     @GetMapping("/getall")
    public List<GetAllLocationsResponse> getAll() {
        return locationService.getAll();
    }

    @GetMapping("/getbyid/{id}")
    public GetByIdLocationResponse getById(@PathVariable int id) {
        return locationService.getById(id);
    }

    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void add(@RequestBody() @Valid() CreateLocationRequest createLocationRequest) {
        this.locationService.add(createLocationRequest);

    }
    @PutMapping("/update/{id}")
    public void updateCar(@RequestBody() UpdateLocationRequest updateLocationRequest){
        this.locationService.update(updateLocationRequest);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id) {
        this.locationService.deleteById(id);
    }
}
