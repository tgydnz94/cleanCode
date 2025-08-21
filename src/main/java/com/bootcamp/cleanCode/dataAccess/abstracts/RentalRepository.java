package com.bootcamp.cleanCode.dataAccess.abstracts;
import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bootcamp.cleanCode.entities.Rental;

public interface RentalRepository extends JpaRepository<Rental, Integer>{
    boolean existsById(int id);
    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END FROM Rental r WHERE r.car.id = :carId AND (r.returnDate IS NULL OR r.returnDate > :today)")
    boolean checkCarIfReturned(@Param("carId") int carId, @Param("today") LocalDate today);
    
}
