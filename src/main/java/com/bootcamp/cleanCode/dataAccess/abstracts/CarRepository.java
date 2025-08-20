package com.bootcamp.cleanCode.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.bootcamp.cleanCode.entities.Car;

import jakarta.transaction.Transactional;

public interface CarRepository extends JpaRepository<Car, Integer> {
    @Transactional
    @Modifying
    @Query("UPDATE Car c set c.imageName = :imageName WHERE c.id = :carId")
    void uploadCarImage(int carId, String imageName);
    boolean existsByPlate(String plate);
    boolean existsById(int id);
    int countByCompanyId(int companyId);
    
}
