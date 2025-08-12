package com.bootcamp.cleanCode.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootcamp.cleanCode.entities.Model;

public interface ModelRepository extends JpaRepository<Model, Integer> {
    
}
