package com.bootcamp.cleanCode.business.concretes.requests.companyInvoiceRequests;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCompanyInvoiceRequest {
    @NotNull
    private int rentalId;
}
