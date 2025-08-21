package com.bootcamp.cleanCode.business.concretes.rules;

import java.time.YearMonth;

import org.springframework.stereotype.Service;

import com.bootcamp.cleanCode.core.utilities.exceptions.BusinessException;
import com.bootcamp.cleanCode.dataAccess.abstracts.PaymentRepository;
import com.bootcamp.cleanCode.dataAccess.abstracts.RentalRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PaymentBusinessRules {
    private RentalRepository rentalRepository;
    private PaymentRepository paymentRepository;

    public void checkIfRentalExists(int rentalId) {
        if (!rentalRepository.existsById(rentalId)) {
            throw new BusinessException("Kiralama kaydı bulunamadı.");
        }
    }

    public void checkIfPaymentAlreadyExistsForRental(int rentalId) {
        if (paymentRepository.existsByRentalId(rentalId)) {
            throw new BusinessException("Bu kiralama için zaten ödeme yapılmış.");
        }
    }

    public void validateCardExpiry(String month, String year) {
        YearMonth expiry = YearMonth.of(Integer.parseInt(year), Integer.parseInt(month));
        YearMonth now = YearMonth.now();

        if (expiry.isBefore(now)) {
            throw new BusinessException("Kartın son kullanma tarihi geçmiş.");
        }
    }    
}
