package com.bootcamp.cleanCode.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootcamp.cleanCode.entities.Fuel;

public interface FuelRepository extends JpaRepository<Fuel, Integer> {
    
}
