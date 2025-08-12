package com.bootcamp.cleanCode.business.concretes.managers;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bootcamp.cleanCode.business.abstracts.BrandService;
import com.bootcamp.cleanCode.dataAccess.abstracts.BrandRepository;
import com.bootcamp.cleanCode.entities.Brand;

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class BrandManager implements BrandService {
    private BrandRepository brandRepository;

    @Override
    public List<Brand> getAll() {
        List<Brand> brands = brandRepository.findAll();
        return brands;
    }

    @Override
    public void add(Brand brand) {
        this.brandRepository.save(brand);
    }

    @Override
    public Brand getById(int id) {
        Brand brand = this.brandRepository.findById(id).orElseThrow();
        return brand;
    }

    @Override
    public Brand update(int id, Brand updatedBrand) {
        Brand brand = brandRepository.findById(id)
                        .orElseThrow(()-> new RuntimeException("Brand not found with id:"+id));
        brand.setName(updatedBrand.getName());
         return brandRepository.save(brand);
    }

    @Override
    public void deleteById(int id) {
        this.brandRepository.deleteById(id);
    }

    
}