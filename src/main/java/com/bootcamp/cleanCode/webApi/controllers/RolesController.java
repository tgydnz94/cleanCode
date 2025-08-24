package com.bootcamp.cleanCode.webApi.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bootcamp.cleanCode.business.abstracts.RoleService;
import com.bootcamp.cleanCode.business.concretes.requests.roleRequests.CreateRoleRequest;
import com.bootcamp.cleanCode.business.concretes.responses.roleResponses.GetAllRolesResponse;
import com.bootcamp.cleanCode.business.concretes.responses.roleResponses.GetByIdRoleResponse;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/roles")
@AllArgsConstructor
public class RolesController {
    private RoleService roleService;

    @GetMapping("/getall")
    public List<GetAllRolesResponse> getAll() {
        return roleService.getAll();
    }

    @GetMapping("/{id}")
    public GetByIdRoleResponse getById(@PathVariable int id) {
        return roleService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@RequestBody @Valid CreateRoleRequest createRoleRequest) {
        roleService.add(createRoleRequest);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        roleService.deleteById(id);
    }
}
