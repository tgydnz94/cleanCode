package com.bootcamp.cleanCode.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootcamp.cleanCode.entities.Transmission;

public interface TransmissionRepository extends JpaRepository<Transmission, Integer> {
    
}
