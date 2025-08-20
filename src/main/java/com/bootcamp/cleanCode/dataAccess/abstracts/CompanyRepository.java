package com.bootcamp.cleanCode.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootcamp.cleanCode.entities.Company;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);
    boolean existsById(int id);
    
}
