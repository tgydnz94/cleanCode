package com.bootcamp.cleanCode.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootcamp.cleanCode.entities.CompanyInvoice;

public interface CompanyInvoiceRepository extends JpaRepository<CompanyInvoice, Integer> {
    
}
