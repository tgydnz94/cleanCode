package com.bootcamp.cleanCode.business.abstracts;

import java.util.List;

import com.bootcamp.cleanCode.business.concretes.requests.brandRequests.CreateBrandRequest;
import com.bootcamp.cleanCode.business.concretes.requests.brandRequests.UpdateBrandRequest;
import com.bootcamp.cleanCode.business.concretes.responses.brandResponses.GetAllBrandsResponse;
import com.bootcamp.cleanCode.business.concretes.responses.brandResponses.GetByIdBrandResponse;

public interface BrandService {
    List<GetAllBrandsResponse> getAll();
    GetByIdBrandResponse getById(int id);
    void add(CreateBrandRequest createBrandRequest);
    void update(UpdateBrandRequest updateBrandRequest);
    void deleteById(int id);
    
}
