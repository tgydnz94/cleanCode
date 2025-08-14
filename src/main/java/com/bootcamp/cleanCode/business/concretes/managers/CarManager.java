package com.bootcamp.cleanCode.business.concretes.managers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.bootcamp.cleanCode.business.abstracts.CarService;
import com.bootcamp.cleanCode.business.concretes.requests.carRequests.CreateCarRequest;
import com.bootcamp.cleanCode.business.concretes.requests.carRequests.UpdateCarRequest;
import com.bootcamp.cleanCode.business.concretes.responses.carResponses.GetAllCarsResponse;
import com.bootcamp.cleanCode.business.concretes.responses.carResponses.GetByIdCarResponse;
import com.bootcamp.cleanCode.core.utilities.mappers.ModelMapperService;
import com.bootcamp.cleanCode.dataAccess.abstracts.CarRepository;
import com.bootcamp.cleanCode.entities.Car;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CarManager implements CarService {
    private CarRepository carRepository;
    private ModelMapperService modelMapperService;

    @Override
    public List<GetAllCarsResponse> getAll() {
        List<Car> cars = carRepository.findAll();
        List<GetAllCarsResponse> carsResponse = cars.stream()
				.map(car-> this.modelMapperService.forResponse()
				.map(car, GetAllCarsResponse.class))
				.collect(Collectors.toList());
        return carsResponse;
    }

    @Override
    public void add(CreateCarRequest createCarRequest) {
        Car car = this.modelMapperService.forRequest()
				.map(createCarRequest, Car.class);
        this.carRepository.save(car);
    }

    @Override
    public GetByIdCarResponse getById(int id) {
        Car car = this.carRepository.findById(id).orElseThrow();
        GetByIdCarResponse response = this.modelMapperService.forResponse()
				.map(car, GetByIdCarResponse.class);
        return response;
    }

    @Override
    public void update(UpdateCarRequest updateCarRequest) {
        Car car = this.modelMapperService.forRequest()
				.map(updateCarRequest, Car.class);
         this.carRepository.save(car);
    }

    @Override
    public void deleteById(int id) {
        this.carRepository.deleteById(id);
    }
    
}

