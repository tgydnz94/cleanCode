package com.bootcamp.cleanCode.business.concretes.requests.brandRequests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBrandRequest {
    @NotBlank
    @Size(min=2, max = 30)
    private String name;
    
}
