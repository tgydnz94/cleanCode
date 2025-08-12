package com.bootcamp.cleanCode.business.abstracts;

import java.util.List;

import com.bootcamp.cleanCode.entities.Transmission;

public interface TransmissionService {
    List<Transmission> getAll();
    Transmission getById(int id);
    void add(Transmission transmission);
    Transmission update(int id, Transmission updatedTransmission);
    void deleteById(int id);
}
