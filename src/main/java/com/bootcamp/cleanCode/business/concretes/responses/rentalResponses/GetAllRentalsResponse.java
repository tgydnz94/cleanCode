package com.bootcamp.cleanCode.business.concretes.responses.rentalResponses;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllRentalsResponse {
    private int id;
    private String customerName;
    private LocalDate rentDate;
    private LocalDate returnDate;
    private double totalPrice;
    private String carPlate;
    private double carDailyPrice;
    private String modelName;
    private int modelYear;
    private String carFuel;
    private String carTransmission;
    private String companyName;

    
    
}
