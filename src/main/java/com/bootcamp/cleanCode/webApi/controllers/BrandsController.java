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

import com.bootcamp.cleanCode.business.abstracts.BrandService;
import com.bootcamp.cleanCode.entities.Brand;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/brands")
@AllArgsConstructor
public class BrandsController {
    private BrandService brandService;

    @GetMapping("getall")
    public List<Brand> getAll() {
        return brandService.getAll();
    }

    @GetMapping("/getbyid/{id}")
    public Brand getById(@PathVariable int id) {
        return brandService.getById(id);
    }

    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void add(@RequestBody() Brand brand) {
        this.brandService.add(brand);

    }
    @PutMapping("/update/{id}")
    public Brand updateBrand(@PathVariable int id, @RequestBody() Brand updatedBrand){
        return brandService.update(id, updatedBrand);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id) {
        this.brandService.deleteById(id);
    }
}
