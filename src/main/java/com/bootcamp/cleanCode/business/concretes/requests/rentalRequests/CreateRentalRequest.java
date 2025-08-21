package com.bootcamp.cleanCode.business.concretes.requests.rentalRequests;

import java.time.LocalDate;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRentalRequest {
    @NotNull
    @FutureOrPresent
    private LocalDate rentDate;
    @NotNull
    @Future
    private LocalDate returnDate;
    @NotNull
    private int carId;
    @NotNull
    private int customerId;
    
}
