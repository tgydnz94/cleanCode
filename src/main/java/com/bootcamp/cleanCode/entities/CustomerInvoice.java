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

@Table(name = "customerInvoieces")
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class CustomerInvoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "invoiceNumber")
    private String invoiceNumber;

    @Column(name = "invoiceDate")
    private LocalDate invoiceDate;

    @Column(name = "amount")
    private double amount;

    @OneToOne
    @JoinColumn(name = "rental_id")
    private Rental rental;
    
}
