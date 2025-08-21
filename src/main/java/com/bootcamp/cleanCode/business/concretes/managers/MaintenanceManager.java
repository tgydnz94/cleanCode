package com.bootcamp.cleanCode.business.concretes.managers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.bootcamp.cleanCode.business.abstracts.MaintenanceService;
import com.bootcamp.cleanCode.business.concretes.requests.maintenanceRequests.CreateMaintenanceRequest;
import com.bootcamp.cleanCode.business.concretes.requests.maintenanceRequests.UpdateMaintenanceRequest;
import com.bootcamp.cleanCode.business.concretes.responses.maintenanceResponses.GetAllMaintenancesResponse;
import com.bootcamp.cleanCode.business.concretes.responses.maintenanceResponses.GetByIdMaintenanceResponse;
import com.bootcamp.cleanCode.business.concretes.rules.MaintenancesBusinessRules;
import com.bootcamp.cleanCode.core.utilities.mappers.ModelMapperService;
import com.bootcamp.cleanCode.dataAccess.abstracts.CarRepository;
import com.bootcamp.cleanCode.dataAccess.abstracts.MaintenanceRepository;
import com.bootcamp.cleanCode.entities.Car;
import com.bootcamp.cleanCode.entities.Maintenance;
import com.bootcamp.cleanCode.entities.enums.CarState;

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class MaintenanceManager implements MaintenanceService{
    private MaintenanceRepository maintenanceRepository;
    private MaintenancesBusinessRules businessRules;
    private CarRepository carRepository;
    private ModelMapperService modelMapperService;

    @Override
    public List<GetAllMaintenancesResponse> getAll() {
        List<Maintenance> maintenances = maintenanceRepository.findAll();
        List<GetAllMaintenancesResponse> maintenancesResponse = maintenances.stream()
				.map(maintenance-> this.modelMapperService.forResponse()
				.map(maintenance, GetAllMaintenancesResponse.class))
				.collect(Collectors.toList());
        return maintenancesResponse;
    }

    @Override
    public GetByIdMaintenanceResponse getById(int id) {
        Maintenance maintenance = this.maintenanceRepository.findById(id).orElseThrow();
        GetByIdMaintenanceResponse response = this.modelMapperService.forResponse()
				.map(maintenance, GetByIdMaintenanceResponse.class);
        return response;
    }

    @Override
    public void add(CreateMaintenanceRequest createMaintenanceRequest) {
        businessRules.checkIfCarExists(createMaintenanceRequest.getCarId());
        businessRules.checkIfCarAlreadyInMaintenance(createMaintenanceRequest.getCarId());
        businessRules.checkIfCarIsRented(createMaintenanceRequest.getCarId());
        businessRules.validateDates(createMaintenanceRequest.getSentDate(), createMaintenanceRequest.getReturnDate());
        
        Maintenance maintenance = this.modelMapperService.forRequest()
				.map(createMaintenanceRequest, Maintenance.class);

        // Aracın durumu Maintenance olarak güncellensin.
        Car car = maintenance.getCar();
        car.setState(CarState.MAINTENANCE);
        carRepository.save(car);

        this.maintenanceRepository.save(maintenance);
    }

    @Override
    public void update(UpdateMaintenanceRequest updateMaintenanceRequest) {
        businessRules.checkIfMaintenanceExists(updateMaintenanceRequest.getId());
        businessRules.checkIfCarExists(updateMaintenanceRequest.getCarId());
        businessRules.validateDates(updateMaintenanceRequest.getSentDate(), updateMaintenanceRequest.getReturnDate());

        Maintenance maintenance = this.modelMapperService.forRequest()
				.map(updateMaintenanceRequest, Maintenance.class);

        //bakımdan dönen aracı available yap
        if (updateMaintenanceRequest.getReturnDate() != null) {
        Car car = maintenance.getCar();
        car.setState(CarState.AVAILABLE);
        carRepository.save(car);
        }
         this.maintenanceRepository.save(maintenance);
    }

    @Override
    public void deleteById(int id) {
        this.maintenanceRepository.deleteById(id);
    }
    
}
