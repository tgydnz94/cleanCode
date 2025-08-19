package com.bootcamp.cleanCode.business.concretes.responses.companyInvoiceResponses;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllCompanyInvoicesResponse {
    private int id;
    private LocalDate invoiceDate;
    private double totalAmount;
    private double commmissionAmount;
}
