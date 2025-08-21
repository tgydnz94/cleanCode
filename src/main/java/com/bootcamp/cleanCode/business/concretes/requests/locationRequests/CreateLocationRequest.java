package com.bootcamp.cleanCode.business.concretes.requests.locationRequests;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateLocationRequest {
    @NotBlank
    private String city;
    @NotBlank
    private String district;
    @NotBlank
    private String street;
    @NotBlank
    private String buildingNo;
    private String details;
}
    

