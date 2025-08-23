package com.bootcamp.cleanCode.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootcamp.cleanCode.entities.RentalLocation;

public interface RentalLocationRepository extends JpaRepository<RentalLocation, Integer> {
    boolean existsById(int id);
    
}
