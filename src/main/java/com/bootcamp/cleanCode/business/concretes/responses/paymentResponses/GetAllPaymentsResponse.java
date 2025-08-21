package com.bootcamp.cleanCode.business.concretes.responses.paymentResponses;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllPaymentsResponse {
    private int id;
    private LocalDate paymenDate;
    private double amount;
    private int rentalId;
    
}
