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

import com.bootcamp.cleanCode.business.abstracts.TransmissionService;
import com.bootcamp.cleanCode.entities.Transmission;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/transmissions")
@AllArgsConstructor
public class TransmissionsController {
    private TransmissionService transmissionService;

    @GetMapping("/getall")
    public List<Transmission> getAll() {
        return transmissionService.getAll();
    }

    @GetMapping("/getbyid/{id}")
    public Transmission getById(@PathVariable int id) {
        return transmissionService.getById(id);
    }

    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void add(@RequestBody() Transmission transmission) {
        this.transmissionService.add(transmission);

    }
    @PutMapping("/update/{id}")
    public Transmission updateTransmission(@PathVariable int id, @RequestBody() Transmission updatedTransmission){
        return transmissionService.update(id, updatedTransmission);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id) {
        this.transmissionService.deleteById(id);
    }
    
}
