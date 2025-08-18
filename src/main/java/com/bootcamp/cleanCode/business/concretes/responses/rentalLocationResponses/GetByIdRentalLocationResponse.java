package com.bootcamp.cleanCode.business.concretes.responses.rentalLocationResponses;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetByIdRentalLocationResponse {
    private int id;
    private String locationType;
    private LocalDate rentDate;
}
