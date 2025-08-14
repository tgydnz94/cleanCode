package com.bootcamp.cleanCode.business.concretes.responses.fuelResponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllFuelsResponse {
    private int id;
    private String name;
    
}
