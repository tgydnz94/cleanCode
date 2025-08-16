package com.bootcamp.cleanCode.entities;

import com.bootcamp.cleanCode.entities.enums.CarState;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "cars")
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "plate", unique = true)
    private String plate;

    @Column(name = "dailyPrice")
    private double dailyPrice;

    @Column(name = "modelYear")
    private int modelYear;

    /*@Column(name = "state")
    private int state;*/

    /*@ManyToOne
    @JoinColumn(name = "model_id")
    private Model model; */
    
    @ManyToOne
    @JoinColumn(name= "model_id")
    private Model model;

    @ManyToOne
    @JoinColumn(name = "fuel_id")
    private Fuel fuel;

    @ManyToOne
    @JoinColumn(name = "transmission_id")
    private Transmission transmission;

    @Enumerated(EnumType.STRING)//string olarak açık şekilde veriye ulaşmak için
    private CarState state;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
}

