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
import com.bootcamp.cleanCode.entities.Car;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/cars")
@AllArgsConstructor
public class CarsController {
    private CarService carService;

    @GetMapping("getall")
    public List<Car> getAll() {
        return carService.getAll();
    }

    @GetMapping("/getbyid/{id}")
    public Car getById(@PathVariable int id) {
        return carService.getById(id);
    }

    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void add(@RequestBody() Car car) {
        this.carService.add(car);

    }
    @PutMapping("/update/{id}")
    public Car updateCar(@PathVariable int id, @RequestBody() Car updatedCar){
        return carService.update(id, updatedCar);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id) {
        this.carService.deleteById(id);
    }
    
}
