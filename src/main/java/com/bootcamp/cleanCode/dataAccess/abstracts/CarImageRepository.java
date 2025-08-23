package com.bootcamp.cleanCode.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootcamp.cleanCode.entities.CarImage;

public interface CarImageRepository extends JpaRepository<CarImage, Integer> {
    
}
