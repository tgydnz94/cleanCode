package com.bootcamp.cleanCode.business.concretes.managers;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bootcamp.cleanCode.business.abstracts.TransmissionService;
import com.bootcamp.cleanCode.dataAccess.abstracts.TransmissionRepository;
import com.bootcamp.cleanCode.entities.Transmission;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TransmissionManager implements TransmissionService {
    private TransmissionRepository transmissionRepository;

    @Override
    public List<Transmission> getAll() {
        List<Transmission> fuels = transmissionRepository.findAll();
        return fuels;
    }

    @Override
    public void add(Transmission transmission) {
        this.transmissionRepository.save(transmission);
    }

    @Override
    public Transmission getById(int id) {
        Transmission transmission = this.transmissionRepository.findById(id).orElseThrow();
        return transmission;
    }

    @Override
    public Transmission update(int id, Transmission updatedTransmission) {
        Transmission transmission = transmissionRepository.findById(id)
                        .orElseThrow(()-> new RuntimeException("Transmission not found with id:"+id));
        transmission.setName(updatedTransmission.getName());
         return transmissionRepository.save(transmission);
    }

    @Override
    public void deleteById(int id) {
        this.transmissionRepository.deleteById(id);
    }
    
}
