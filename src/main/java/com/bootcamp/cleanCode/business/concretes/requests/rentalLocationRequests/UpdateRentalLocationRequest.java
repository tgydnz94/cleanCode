package com.bootcamp.cleanCode.business.concretes.requests.rentalLocationRequests;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRentalLocationRequest {
    private int id;
    private String locationType;
    private int rentalId;
    private int locaitonId;
    private LocalDateTime timeStamp;
}
