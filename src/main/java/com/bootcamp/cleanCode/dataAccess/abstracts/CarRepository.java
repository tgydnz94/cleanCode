package com.bootcamp.cleanCode.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bootcamp.cleanCode.entities.Car;

public interface CarRepository extends JpaRepository<Car, Integer> {
    boolean existsByPlate(String plate);
    boolean existsById(int id);
    int countByCompanyId(int companyId);
    
}
