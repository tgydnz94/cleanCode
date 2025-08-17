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

import com.bootcamp.cleanCode.business.abstracts.MaintenanceService;
import com.bootcamp.cleanCode.business.concretes.requests.maintenanceRequests.CreateMaintenanceRequest;
import com.bootcamp.cleanCode.business.concretes.requests.maintenanceRequests.UpdateMaintenanceRequest;
import com.bootcamp.cleanCode.business.concretes.responses.maintenanceResponses.GetAllMaintenancesResponse;
import com.bootcamp.cleanCode.business.concretes.responses.maintenanceResponses.GetByIdMaintenanceResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/maintenances")
@AllArgsConstructor
public class MaintenancesController {
    private MaintenanceService maintenanceService;

    @GetMapping("/getall")
    public List<GetAllMaintenancesResponse> getAll() {
        return maintenanceService.getAll();
    }

    @GetMapping("/getbyid/{id}")
    public GetByIdMaintenanceResponse getById(@PathVariable int id) {
        return maintenanceService.getById(id);
    }

    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void add(@RequestBody() @Valid() CreateMaintenanceRequest createMaintenanceRequest) {
        this.maintenanceService.add(createMaintenanceRequest);

    }
    @PutMapping("/update/{id}")
    public void updateCar(@RequestBody() UpdateMaintenanceRequest updateMaintenanceRequest){
        this.maintenanceService.update(updateMaintenanceRequest);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id) {
        this.maintenanceService.deleteById(id);
    }
    
}
