package com.bootcamp.cleanCode.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootcamp.cleanCode.entities.Brand;

public interface BrandRepository extends JpaRepository<Brand, Integer>{
    boolean existsByName(String name);
    boolean existsById(int id);
    
}
