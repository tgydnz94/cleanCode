package com.bootcamp.cleanCode.business.abstracts;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.bootcamp.cleanCode.business.concretes.requests.carRequests.CreateCarRequest;
import com.bootcamp.cleanCode.business.concretes.requests.carRequests.UpdateCarRequest;
import com.bootcamp.cleanCode.business.concretes.responses.carResponses.GetAllCarsResponse;
import com.bootcamp.cleanCode.business.concretes.responses.carResponses.GetByIdCarResponse;

public interface CarService {
    List<GetAllCarsResponse> getAll();
    GetByIdCarResponse getById(int id);
    void add(CreateCarRequest createCarRequest);
    void update(UpdateCarRequest updateCarRequest);
    void deleteById(int id);
    void uploadCarImage(int carId, MultipartFile file);
}
