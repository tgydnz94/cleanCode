package com.bootcamp.cleanCode.business.concretes.requests.maintenanceRequests;

import java.time.LocalDate;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateMaintenanceRequest {
    @NotBlank
    @FutureOrPresent
    private LocalDate sentDate;
    private LocalDate returnDate;
    @NotBlank
    @Size(min = 5, max = 200)
    private String description;
    @NotNull
    private int carId;
}
