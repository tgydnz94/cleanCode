package com.bootcamp.cleanCode.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootcamp.cleanCode.entities.CustomerInvoice;

public interface CustomerInvoiceRepository extends JpaRepository<CustomerInvoice, Integer> {
    boolean existsByRentalId(int rentalId);

    
}
