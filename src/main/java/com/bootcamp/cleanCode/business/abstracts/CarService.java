package com.bootcamp.cleanCode.business.abstracts;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.bootcamp.cleanCode.business.concretes.requests.carRequests.CreateCarRequest;
import com.bootcamp.cleanCode.business.concretes.requests.carRequests.UpdateCarRequest;
import com.bootcamp.cleanCode.business.concretes.responses.carResponses.GetAllCarsResponse;
import com.bootcamp.cleanCode.business.concretes.responses.carResponses.GetByIdCarResponse;

public interface CarService {
    List<GetAllCarsResponse> getAll();
    GetByIdCarResponse getById(int id);
    void add(CreateCarRequest createCarRequest, List<MultipartFile> images) throws IOException;
    void update(UpdateCarRequest updateCarRequest, List<MultipartFile> images) throws IOException;
    void deleteById(int id);
    void deleteImage(int carId, int imageId) throws IOException;
}
