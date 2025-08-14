package com.bootcamp.cleanCode.business.abstracts;

import java.util.List;

import com.bootcamp.cleanCode.business.concretes.requests.brand.CreateBrandRequest;
import com.bootcamp.cleanCode.business.concretes.requests.brand.UpdateBrandRequest;
import com.bootcamp.cleanCode.business.concretes.responses.brand.GetAllBrandsResponse;
import com.bootcamp.cleanCode.business.concretes.responses.brand.GetByIdBrandResponse;

public interface BrandService {
    List<GetAllBrandsResponse> getAll();
    GetByIdBrandResponse getById(int id);
    void add(CreateBrandRequest createBrandRequest);
    void update(UpdateBrandRequest updateBrandRequest);
    void deleteById(int id);
    
}
