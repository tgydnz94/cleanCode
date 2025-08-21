package com.bootcamp.cleanCode.business.concretes.requests.fuelRequests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateFuelRequest {
    @NotBlank
    @Size(min = 2, max = 20)
    private String name;
    
}
