package com.bootcamp.cleanCode.entities;

import java.time.LocalDateTime;

import com.bootcamp.cleanCode.entities.enums.LocationType;

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

@Table(name = "rentalLocations")
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class RentalLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Enumerated(EnumType.STRING)
    private LocationType type;

    @ManyToOne
    @JoinColumn(name = "rental_id")
    private Rental rental;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @Column(name = "timeStamp")
    private LocalDateTime timeStamp;
}
