package com.bootcamp.cleanCode.business.concretes.requests.customerRequests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCustomerRequest {
    @NotBlank
    @Size(min = 2, max = 30)
    private String firstName;
    @NotBlank
    @Size(min = 2, max = 30)
    private String lastName;
    @NotBlank
    @Size(min = 2, max = 30)
    private String userName;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String phoneNumber;
    
}
