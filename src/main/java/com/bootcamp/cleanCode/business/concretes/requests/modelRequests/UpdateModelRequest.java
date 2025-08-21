package com.bootcamp.cleanCode.business.concretes.requests.modelRequests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateModelRequest {
    @NotNull
    private int id;
    @NotBlank
    @Size(min = 2, max = 50)
    private String name;
    @NotNull
    private int brandId;
    
}
