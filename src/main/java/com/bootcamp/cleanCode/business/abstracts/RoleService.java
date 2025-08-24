package com.bootcamp.cleanCode.business.abstracts;

import java.util.List;

import com.bootcamp.cleanCode.business.concretes.requests.roleRequests.CreateRoleRequest;
import com.bootcamp.cleanCode.business.concretes.responses.roleResponses.*;

public interface RoleService {
    void add(CreateRoleRequest request);
    List<GetAllRolesResponse> getAll();
    GetByIdRoleResponse getById(int id);
    void deleteById(int id);
}
