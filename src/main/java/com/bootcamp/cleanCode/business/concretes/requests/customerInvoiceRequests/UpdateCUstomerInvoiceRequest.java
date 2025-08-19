package com.bootcamp.cleanCode.business.concretes.requests.customerInvoiceRequests;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCUstomerInvoiceRequest {
    private int id;
    private LocalDate invoiceDate;
    private double amount;
    private int rentalId;
}
