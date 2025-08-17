package com.bootcamp.cleanCode.business.concretes.requests.customerRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCustomerRequest {
    private int id;
    private String firstName;
    private String lastName;
    private String userName;
    private String phoneNumber;
    
}
