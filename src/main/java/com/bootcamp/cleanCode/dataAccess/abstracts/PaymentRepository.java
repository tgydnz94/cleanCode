package com.bootcamp.cleanCode.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootcamp.cleanCode.entities.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer>{
    
}
