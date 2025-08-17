package com.bootcamp.cleanCode.business.concretes.responses.customerResponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetByIdCustomerResponse {
    private int id;
    private String firstName;
    private String lastName;
    private String userName;
    private String phoneNumber;
    
}
