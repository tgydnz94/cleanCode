package com.bootcamp.cleanCode.webApi.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bootcamp.cleanCode.business.abstracts.BrandService;
import com.bootcamp.cleanCode.business.concretes.requests.brandRequests.CreateBrandRequest;
import com.bootcamp.cleanCode.business.concretes.requests.brandRequests.UpdateBrandRequest;
import com.bootcamp.cleanCode.business.concretes.responses.brandResponses.GetAllBrandsResponse;
import com.bootcamp.cleanCode.business.concretes.responses.brandResponses.GetByIdBrandResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/brands")
@AllArgsConstructor
public class BrandsController {
    private BrandService brandService;


    @GetMapping("getall")
    public List<GetAllBrandsResponse> getAll() {
        return brandService.getAll();
    }

    @GetMapping("/getbyid/{id}")
    public GetByIdBrandResponse getById(@PathVariable int id) {
        return brandService.getById(id);
    }

    @PreAuthorize("hasRole('COMPANY')")
    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void add(@RequestBody() @Valid() CreateBrandRequest createBrandRequest) {
        this.brandService.add(createBrandRequest);

    }

    @PreAuthorize("hasAnyRole('COMPANY','ADMIN')")
    @PutMapping("/update")
    public void updateBrand(@RequestBody() UpdateBrandRequest updateBrandRequest){
        this.brandService.update(updateBrandRequest);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id) {
        this.brandService.deleteById(id);
    }
}
