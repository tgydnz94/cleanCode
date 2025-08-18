package com.bootcamp.cleanCode.business.concretes.requests.locationRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateLocationRequest {
    private int id;
    private String city;
    private String district;
    private String street;
    private String buildingNo;
    private String details;
}
