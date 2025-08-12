package com.bootcamp.cleanCode.business.abstracts;

import java.util.List;

import com.bootcamp.cleanCode.entities.Fuel;

public interface FuelService {
    List<Fuel> getAll();
    Fuel getById(int id);
    void add(Fuel fuel);
    Fuel update(int id, Fuel updatedFuel);
    void deleteById(int id);
}
