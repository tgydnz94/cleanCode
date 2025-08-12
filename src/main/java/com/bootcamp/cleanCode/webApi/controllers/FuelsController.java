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
import com.bootcamp.cleanCode.entities.Fuel;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/fuels")
@AllArgsConstructor
public class FuelsController {
    private FuelService fuelService;

    @GetMapping("getall")
    public List<Fuel> getAll() {
        return fuelService.getAll();
    }

    @GetMapping("/getbyid/{id}")
    public Fuel getById(@PathVariable int id) {
        return fuelService.getById(id);
    }

    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void add(@RequestBody() Fuel fuel) {
        this.fuelService.add(fuel);

    }
    @PutMapping("/update/{id}")
    public Fuel updateFuel(@PathVariable int id, @RequestBody() Fuel updatedFuel){
        return fuelService.update(id, updatedFuel);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id) {
        this.fuelService.deleteById(id);
    }

    
}