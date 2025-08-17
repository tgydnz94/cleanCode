package com.bootcamp.cleanCode.business.concretes.responses.maintenanceResponses;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetByIdMaintenanceResponse {
    private int id;
    private LocalDate sentDate;
    private LocalDate returnDate;
    private String description;
    private String carPlate;
    
}
