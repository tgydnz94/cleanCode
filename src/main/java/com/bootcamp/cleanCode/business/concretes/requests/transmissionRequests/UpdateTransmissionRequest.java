package com.bootcamp.cleanCode.business.concretes.requests.transmissionRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTransmissionRequest {
    private int id;
    private String name;
    
}
