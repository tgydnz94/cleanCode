package com.bootcamp.cleanCode.business.concretes.requests.rentalRequests;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRentalRequest {
    private int id;
    private LocalDate rentDate;
    private LocalDate returnDate;
    private double totalPrice;
    private int carId;
    private int customerId;
    
}
