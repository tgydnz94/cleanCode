package com.bootcamp.cleanCode.dataAccess.abstracts;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootcamp.cleanCode.entities.Worker;

public interface WorkerRepository extends JpaRepository<Worker, Integer>{
    boolean existsByEmailIgnoreCase(String email);
    boolean existsByEmail(String email);
    boolean existsByPhoneNumber(String phoneNumber);
    Optional<Worker> findByEmail(String email);
    
}
