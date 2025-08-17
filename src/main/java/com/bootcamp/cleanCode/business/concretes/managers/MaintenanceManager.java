package com.bootcamp.cleanCode.business.concretes.managers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.bootcamp.cleanCode.business.abstracts.MaintenanceService;
import com.bootcamp.cleanCode.business.concretes.requests.maintenanceRequests.CreateMaintenanceRequest;
import com.bootcamp.cleanCode.business.concretes.requests.maintenanceRequests.UpdateMaintenanceRequest;
import com.bootcamp.cleanCode.business.concretes.responses.maintenanceResponses.GetAllMaintenancesResponse;
import com.bootcamp.cleanCode.business.concretes.responses.maintenanceResponses.GetByIdMaintenanceResponse;
import com.bootcamp.cleanCode.core.utilities.mappers.ModelMapperService;
import com.bootcamp.cleanCode.dataAccess.abstracts.MaintenanceRepository;
import com.bootcamp.cleanCode.entities.Maintenance;
import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class MaintenanceManager implements MaintenanceService{
    private MaintenanceRepository maintenanceRepository;
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
        Maintenance maintenance = this.modelMapperService.forRequest()
				.map(createMaintenanceRequest, Maintenance.class);
        this.maintenanceRepository.save(maintenance);
    }

    @Override
    public void update(UpdateMaintenanceRequest updateMaintenanceRequest) {
        Maintenance maintenance = this.modelMapperService.forRequest()
				.map(updateMaintenanceRequest, Maintenance.class);
         this.maintenanceRepository.save(maintenance);
    }

    @Override
    public void deleteById(int id) {
        this.maintenanceRepository.deleteById(id);
    }
    
}
