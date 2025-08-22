package com.bootcamp.cleanCode.business.concretes.requests.transmissionRequests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateTransmissionRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String name;
    
}
