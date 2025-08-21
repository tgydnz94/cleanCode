package com.bootcamp.cleanCode.business.concretes.requests.customerInvoiceRequests;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCUstomerInvoiceRequest {
    @NotNull
    private int id;
    @NotNull
    private LocalDate invoiceDate;
    @NotNull
    private int rentalId;
    @NotNull
    private String invoiceNumber;
}
