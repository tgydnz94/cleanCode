package com.bootcamp.cleanCode.business.concretes.requests.workerRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateWorkerRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
}
