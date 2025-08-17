package com.bootcamp.cleanCode.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootcamp.cleanCode.entities.Maintenance;

public interface MaintenanceRepository extends JpaRepository<Maintenance, Integer> {
    
}
