package com.bootcamp.cleanCode.business.concretes.responses.transmissionResponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetByIdTransmissionResponse {
    private int id;
    private String name;

    
}