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

@Table(name = "payments")
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "paymentDate")
    private LocalDate paymentDate;

    @Column(name = "amount")
    private double amount;

    @Column(name = "cardHolder")
    private String cardHolder;

    @Column(name = "cardNumber")
    private String cardNumber;

    @Column(name = "cardCvv")
    private String cardCvv;

    @Column(name = "cardExpiryMonth")
    private String cardExpiryMonth;

    @Column(name = "cardExpiryYear")
    private String cardExpiryYear;

    @ManyToOne
    @JoinColumn(name = "rental_id")
    private Rental rental;
    
}
