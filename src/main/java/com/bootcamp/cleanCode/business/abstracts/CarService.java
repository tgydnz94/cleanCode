package com.bootcamp.cleanCode.business.abstracts;

import java.util.List;

import com.bootcamp.cleanCode.entities.Car;

public interface CarService {
    List<Car> getAll();
    Car getById(int id);
    void add(Car car);
    Car update(int id, Car updatedCar);
    void deleteById(int id);
}
