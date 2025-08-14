package com.bootcamp.cleanCode.business.concretes.requests.fuelRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateFuelRequest {
    private int id;
    private String name;
    
}
