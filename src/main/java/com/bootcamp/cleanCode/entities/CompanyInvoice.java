package com.bootcamp.cleanCode.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Table(name = "companyInvoieces")
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class CompanyInvoice {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "invoiceDate")
    private LocalDate invoiceDate;

    @Column(name = "totalAmount")
    private double totalAmount;

    @Column(name = "commissionAmount")
    private double commmissionAmount;

    @Column(name = "netAmount")
    private double netAmount;

    @OneToOne
    @JoinColumn(name = "rental_id")
    private Rental rental;
}
