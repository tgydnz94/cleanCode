package com.bootcamp.cleanCode.business.concretes.requests.paymentRequests;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePaymentRequest {
    @NotNull
    private int rentalId;

    @NotBlank
    @Size(min = 3, max = 50)
    private String cardHolder;

    @NotBlank
    @Pattern(regexp = "\\d{16}", message = "Kart numarası 16 haneli olmalıdır.")
    private String cardNumber;

    @NotBlank
    @Pattern(regexp = "\\d{3}", message = "CVV 3 haneli olmalıdır.")
    private String cardCvv;

    @NotBlank
    @Pattern(regexp = "^(0[1-9]|1[0-2])$", message = "Geçerli bir ay girin (01-12)")
    private String cardExpiryMonth;

    @NotBlank
    @Pattern(regexp = "^(\\d{4})$", message = "Yıl 4 haneli olmalıdır.")
    private String cardExpiryYear;
}
