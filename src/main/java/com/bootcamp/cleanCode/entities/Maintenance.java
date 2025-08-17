package com.bootcamp.cleanCode.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "maintenances")
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Maintenance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "sentDate")
    private LocalDate sentDate;

    @Column(name = "returnDate")
    private LocalDate returnDate;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;
    
}
