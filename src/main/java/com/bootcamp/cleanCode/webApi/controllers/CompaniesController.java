package com.bootcamp.cleanCode.webApi.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bootcamp.cleanCode.business.abstracts.CompanyService;
import com.bootcamp.cleanCode.business.concretes.requests.companyRequests.CreateCompanyRequest;
import com.bootcamp.cleanCode.business.concretes.requests.companyRequests.UpdateCompanyRequest;
import com.bootcamp.cleanCode.business.concretes.responses.companyResponses.GetAllCompaniesResponses;
import com.bootcamp.cleanCode.business.concretes.responses.companyResponses.GetByIdCompanyResponse;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
@RestController
@RequestMapping("/api/companies")
@AllArgsConstructor
public class CompaniesController {
    private CompanyService companyService;

    @GetMapping("/getall")
    public List<GetAllCompaniesResponses> getAll() {
        return companyService.getAll();
    }

    @GetMapping("/getbyid/{id}")
    public GetByIdCompanyResponse getById(@PathVariable int id) {
        return companyService.getById(id);
    }

    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void add(@RequestBody() @Valid() CreateCompanyRequest createCompanyRequest) {
        this.companyService.add(createCompanyRequest);

    }
    @PutMapping("/update/{id}")
    public void updateCar(@RequestBody() UpdateCompanyRequest updateCompanyRequest){
        this.companyService.update(updateCompanyRequest);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id) {
        this.companyService.deleteById(id);
    }
    
}
