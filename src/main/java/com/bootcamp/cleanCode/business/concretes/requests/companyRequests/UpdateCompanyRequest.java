package com.bootcamp.cleanCode.business.concretes.requests.companyRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCompanyRequest {
    private int id;
    private String name;
    private String email;
    private String phone;

    
}
