package com.bootcamp.cleanCode.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootcamp.cleanCode.entities.Location;

public interface LocationRepository extends JpaRepository<Location, Integer> {
    boolean existsById(int id);
    
}
