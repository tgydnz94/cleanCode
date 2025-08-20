package com.bootcamp.cleanCode.business.concretes.requests.carRequests;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarRequest {
    @NotBlank
    @Size(min = 2, max = 20)
    private String plate;
    @NotNull
    @DecimalMin("0.0")
    private double dailyPrice;
    @NotNull
    private int modelYear;
    @NotNull
    private int modelId;
    @NotNull
    private int fuelId;
    @NotNull
    private int transmissionId;
    @NotNull
    private String state;
     @NotNull
    private int companyId;
}
