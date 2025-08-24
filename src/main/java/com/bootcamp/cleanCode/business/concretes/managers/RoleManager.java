package com.bootcamp.cleanCode.business.concretes.managers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.bootcamp.cleanCode.business.abstracts.RoleService;
import com.bootcamp.cleanCode.business.concretes.requests.roleRequests.CreateRoleRequest;
import com.bootcamp.cleanCode.business.concretes.responses.roleResponses.GetAllRolesResponse;
import com.bootcamp.cleanCode.business.concretes.responses.roleResponses.GetByIdRoleResponse;
import com.bootcamp.cleanCode.core.utilities.exceptions.BusinessException;
import com.bootcamp.cleanCode.core.utilities.exceptions.NotFoundException;
import com.bootcamp.cleanCode.core.utilities.mappers.ModelMapperService;
import com.bootcamp.cleanCode.dataAccess.abstracts.RoleRepository;
import com.bootcamp.cleanCode.entities.Role;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RoleManager implements RoleService {
    private final RoleRepository roleRepository;
    private final ModelMapperService modelMapperService;

    @Override
    public void add(CreateRoleRequest request) {
        if (roleRepository.existsByNameIgnoreCase(request.getName())) {
            throw new BusinessException("Bu rol zaten mevcut.");
        }

        Role role = modelMapperService.forRequest().map(request, Role.class);
        roleRepository.save(role);
    }

    @Override
    public List<GetAllRolesResponse> getAll() {
        return roleRepository.findAll().stream()
                .map(role -> modelMapperService.forResponse().map(role, GetAllRolesResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public GetByIdRoleResponse getById(int id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Rol bulunamadı."));
        return modelMapperService.forResponse().map(role, GetByIdRoleResponse.class);
    }

    @Override
    public void deleteById(int id) {
        if (!roleRepository.existsById(id)) {
            throw new NotFoundException("Silinecek rol bulunamadı.");
        }
        roleRepository.deleteById(id);
    }
}
