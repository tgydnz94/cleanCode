package com.bootcamp.cleanCode.business.concretes.responses.workerResponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetByIdWorkerResponse {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
}
