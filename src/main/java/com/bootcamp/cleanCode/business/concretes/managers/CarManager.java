package com.bootcamp.cleanCode.business.concretes.managers;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bootcamp.cleanCode.business.abstracts.CarService;
import com.bootcamp.cleanCode.dataAccess.abstracts.CarRepository;
import com.bootcamp.cleanCode.entities.Car;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CarManager implements CarService {
    private CarRepository carRepository;

    @Override
    public List<Car> getAll() {
        List<Car> cars = carRepository.findAll();
        return cars;
    }

    @Override
    public void add(Car car) {
        this.carRepository.save(car);
    }

    @Override
    public Car getById(int id) {
        Car car = this.carRepository.findById(id).orElseThrow();
        return car;
    }

    @Override
    public Car update(int id, Car updatedCar) {
        Car car = carRepository.findById(id)
            .orElseThrow(()-> new RuntimeException("Car not found with id:"+id));
            car.setDailyPrice(updatedCar.getDailyPrice());
            //car.setModel(updatedCar.getModel());
            car.setModelYear(updatedCar.getModelYear());
            car.setPlate(updatedCar.getPlate());
            car.setState(updatedCar.getState());
         return carRepository.save(car);
    }

    @Override
    public void deleteById(int id) {
        this.carRepository.deleteById(id);
    }
    
}

