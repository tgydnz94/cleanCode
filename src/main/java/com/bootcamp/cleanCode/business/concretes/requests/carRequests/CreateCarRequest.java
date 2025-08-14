package com.bootcamp.cleanCode.business.concretes.requests.carRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarRequest {
    private String plate;
    private double dailyPrice;
    private int modelYear;
    private int modelId;
    private int fuelId;
    private int transmissionId;
    private String state;
}
