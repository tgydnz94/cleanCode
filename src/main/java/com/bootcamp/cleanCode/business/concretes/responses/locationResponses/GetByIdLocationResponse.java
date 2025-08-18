package com.bootcamp.cleanCode.business.concretes.responses.locationResponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetByIdLocationResponse {
    private int id;
    private String city;
    private String district;
    private String street;
    private String buildingNo;
    private String details;
}
