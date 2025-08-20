package com.bootcamp.cleanCode.dataAccess.abstracts;
import org.springframework.data.jpa.repository.JpaRepository;

import com.bootcamp.cleanCode.entities.Rental;

public interface RentalRepository extends JpaRepository<Rental, Integer>{
    boolean existsById(int id);
    
}
