package com.bootcamp.cleanCode.business.concretes.responses.customerInvoiceResponses;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllCustomerInvoicesResponse {
    private int id;
    private LocalDate invoiceDate;
    private double amount;

}
