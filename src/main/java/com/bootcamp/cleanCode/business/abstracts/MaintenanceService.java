package com.bootcamp.cleanCode.business.abstracts;

import java.util.List;

import com.bootcamp.cleanCode.business.concretes.requests.maintenanceRequests.CreateMaintenanceRequest;
import com.bootcamp.cleanCode.business.concretes.requests.maintenanceRequests.UpdateMaintenanceRequest;
import com.bootcamp.cleanCode.business.concretes.responses.maintenanceResponses.GetAllMaintenancesResponse;
import com.bootcamp.cleanCode.business.concretes.responses.maintenanceResponses.GetByIdMaintenanceResponse;

public interface MaintenanceService {
    List<GetAllMaintenancesResponse> getAll();
    GetByIdMaintenanceResponse getById(int id);
    void add(CreateMaintenanceRequest createMaintenanceRequest);
    void update(UpdateMaintenanceRequest updateMaintenanceRequest);
    void deleteById(int id);
}
