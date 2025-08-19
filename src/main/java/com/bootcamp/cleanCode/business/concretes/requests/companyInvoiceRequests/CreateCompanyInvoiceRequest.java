package com.bootcamp.cleanCode.business.concretes.requests.companyInvoiceRequests;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCompanyInvoiceRequest {
    private LocalDate invoiceDate;
    private double totalAmount;
    private double commmissionAmount;
    private int rentalId;
}
