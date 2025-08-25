package com.bootcamp.cleanCode.dataAccess.abstracts;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootcamp.cleanCode.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    boolean existsByEmail(String email);
    boolean existsById(int id);
    Optional<Customer> findByEmail(String email);
      
}
