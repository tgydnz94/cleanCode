package com.bootcamp.cleanCode.business.concretes.responses.companyResponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllCompaniesResponses {
    private String name;
    private String email;
    private String phone;
    
}
