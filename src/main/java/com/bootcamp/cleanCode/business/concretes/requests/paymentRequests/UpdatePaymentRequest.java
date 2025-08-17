package com.bootcamp.cleanCode.business.concretes.requests.paymentRequests;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePaymentRequest {
    private int id;
    private LocalDate paymenDate;
    private double amount;
    private int rentalId;
    
}
