package com.bootcamp.cleanCode.business.concretes.requests.maintenanceRequests;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateMaintenanceRequest {
    private int id;
    private LocalDate sentDate;
    private LocalDate returnDate;
    private String description;
    private int carId;
}
