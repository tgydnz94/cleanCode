package com.bootcamp.cleanCode.business.concretes.responses.companyResponses;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetByIdCompanyResponse {
    @NotNull
    private int id;
    private String name;
    private String email;
    private String phone;
    
}
