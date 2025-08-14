package com.bootcamp.cleanCode.business.concretes.managers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.bootcamp.cleanCode.business.abstracts.TransmissionService;
import com.bootcamp.cleanCode.business.concretes.requests.transmissionRequests.CreateTransmissionRequest;
import com.bootcamp.cleanCode.business.concretes.requests.transmissionRequests.UpdateTransmissionRequest;
import com.bootcamp.cleanCode.business.concretes.responses.transmissionResponses.GetAllTransmissionsResponse;
import com.bootcamp.cleanCode.business.concretes.responses.transmissionResponses.GetByIdTransmissionResponse;
import com.bootcamp.cleanCode.core.utilities.mappers.ModelMapperService;
import com.bootcamp.cleanCode.dataAccess.abstracts.TransmissionRepository;
import com.bootcamp.cleanCode.entities.Transmission;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TransmissionManager implements TransmissionService {
    private TransmissionRepository transmissionRepository;
    private ModelMapperService modelMapperService;

    @Override
    public List<GetAllTransmissionsResponse> getAll() {
        List<Transmission> transmissions = transmissionRepository.findAll();

        List<GetAllTransmissionsResponse> transmissionsResponse = transmissions.stream()
				.map(transmission-> this.modelMapperService.forResponse()
				.map(transmission, GetAllTransmissionsResponse.class))
				.collect(Collectors.toList());
        return transmissionsResponse;
    }

    @Override
    public void add(CreateTransmissionRequest createTransmissionRequest) {
        Transmission transmission = this.modelMapperService.forRequest()
				.map(createTransmissionRequest, Transmission.class);
        this.transmissionRepository.save(transmission);
    }

    @Override
    public GetByIdTransmissionResponse getById(int id) {
        Transmission transmission = this.transmissionRepository.findById(id).orElseThrow();

        GetByIdTransmissionResponse response = this.modelMapperService.forResponse()
				.map(transmission, GetByIdTransmissionResponse.class);

        return response;
    }

    @Override
    public void update(UpdateTransmissionRequest updateTransmissionRequest) {
        Transmission transmission = this.modelMapperService.forRequest()
				.map(updateTransmissionRequest, Transmission.class);

         this.transmissionRepository.save(transmission);
    }

    @Override
    public void deleteById(int id) {
        this.transmissionRepository.deleteById(id);
    }
    
}
