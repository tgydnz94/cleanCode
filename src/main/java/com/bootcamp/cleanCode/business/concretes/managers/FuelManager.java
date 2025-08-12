package com.bootcamp.cleanCode.business.concretes.managers;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bootcamp.cleanCode.business.abstracts.FuelService;
import com.bootcamp.cleanCode.dataAccess.abstracts.FuelRepository;
import com.bootcamp.cleanCode.entities.Fuel;

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class FuelManager implements FuelService {
    private FuelRepository fuelRepository;

    @Override
    public List<Fuel> getAll() {
        List<Fuel> fuels = fuelRepository.findAll();
        return fuels;
    }

    @Override
    public void add(Fuel fuel) {
        this.fuelRepository.save(fuel);
    }

    @Override
    public Fuel getById(int id) {
        Fuel fuel = this.fuelRepository.findById(id).orElseThrow();
        return fuel;
    }

    @Override
    public Fuel update(int id, Fuel updatedFuel) {
        Fuel fuel = fuelRepository.findById(id)
                        .orElseThrow(()-> new RuntimeException("Fuel not found with id:"+id));
        fuel.setName(updatedFuel.getName());
         return fuelRepository.save(fuel);
    }

    @Override
    public void deleteById(int id) {
        this.fuelRepository.deleteById(id);
    }
    
}
