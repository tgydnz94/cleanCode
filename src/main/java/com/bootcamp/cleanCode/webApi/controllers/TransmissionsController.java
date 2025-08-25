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

import com.bootcamp.cleanCode.business.abstracts.TransmissionService;
import com.bootcamp.cleanCode.business.concretes.requests.transmissionRequests.CreateTransmissionRequest;
import com.bootcamp.cleanCode.business.concretes.requests.transmissionRequests.UpdateTransmissionRequest;
import com.bootcamp.cleanCode.business.concretes.responses.transmissionResponses.GetAllTransmissionsResponse;
import com.bootcamp.cleanCode.business.concretes.responses.transmissionResponses.GetByIdTransmissionResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/transmissions")
@AllArgsConstructor
public class TransmissionsController {
    private TransmissionService transmissionService;

    @GetMapping("/getall")
    public List<GetAllTransmissionsResponse> getAll() {
        return transmissionService.getAll();
    }

    @GetMapping("/getbyid/{id}")
    public GetByIdTransmissionResponse getById(@PathVariable int id) {
        return transmissionService.getById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void add(@RequestBody() @Valid() CreateTransmissionRequest createTransmissionRequest) {
        this.transmissionService.add(createTransmissionRequest);

    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update")
    public void updateTransmission(@RequestBody() UpdateTransmissionRequest updateTransmissionRequest){
        this.transmissionService.update(updateTransmissionRequest);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id) {
        this.transmissionService.deleteById(id);
    }
    
}
