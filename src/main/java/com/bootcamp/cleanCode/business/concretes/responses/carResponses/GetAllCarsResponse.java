package com.bootcamp.cleanCode.business.concretes.responses.carResponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllCarsResponse {
    private int id;
    private String plate;
    private double dailyPrice;
    private int modelYear;
    private String modelName;
    private String fuelName;
    private String transmissionName;
    private String state;
    
}
