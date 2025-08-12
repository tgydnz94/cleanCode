package com.bootcamp.cleanCode.business.abstracts;

import java.util.List;

import com.bootcamp.cleanCode.entities.Brand;

public interface BrandService {
    List<Brand> getAll();
    Brand getById(int id);
    void add(Brand brand);
    Brand update(int id, Brand updatedBrand);
    void deleteById(int id);
    
}
