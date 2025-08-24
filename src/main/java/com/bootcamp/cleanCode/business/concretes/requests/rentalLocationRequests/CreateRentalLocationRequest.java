package com.bootcamp.cleanCode.business.concretes.requests.rentalLocationRequests;

import com.bootcamp.cleanCode.entities.enums.LocationType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRentalLocationRequest {
    private LocationType locationType;
    private int locationId;
    private int rentalId;

}
