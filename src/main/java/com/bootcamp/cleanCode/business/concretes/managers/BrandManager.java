package com.bootcamp.cleanCode.business.concretes.managers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.bootcamp.cleanCode.business.abstracts.BrandService;
import com.bootcamp.cleanCode.business.concretes.requests.brandRequests.CreateBrandRequest;
import com.bootcamp.cleanCode.business.concretes.requests.brandRequests.UpdateBrandRequest;
import com.bootcamp.cleanCode.business.concretes.responses.brandResponses.GetAllBrandsResponse;
import com.bootcamp.cleanCode.business.concretes.responses.brandResponses.GetByIdBrandResponse;
import com.bootcamp.cleanCode.business.concretes.rules.BrandBusinessRules;
import com.bootcamp.cleanCode.core.utilities.mappers.ModelMapperService;
import com.bootcamp.cleanCode.dataAccess.abstracts.BrandRepository;
import com.bootcamp.cleanCode.entities.Brand;

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class BrandManager implements BrandService {
    private BrandRepository brandRepository;
    private ModelMapperService modelMapperService;
    private BrandBusinessRules businessRules;

    @Override
    public List<GetAllBrandsResponse> getAll() {
        List<Brand> brands = brandRepository.findAll();

        List<GetAllBrandsResponse> brandsResponse = brands.stream()
				.map(brand-> this.modelMapperService.forResponse()
						.map(brand, GetAllBrandsResponse.class))
				.collect(Collectors.toList());

        return brandsResponse;
    }

    @Override
    public void add(CreateBrandRequest createBrandRequest) {
      businessRules.checkIfBrandNameExists(createBrandRequest.getName());

      Brand brand = this.modelMapperService.forRequest()
				.map(createBrandRequest, Brand.class);
        this.brandRepository.save(brand);
    }

    @Override
    public GetByIdBrandResponse getById(int id) {
      businessRules.checkIfBrandIdExists(id);

        Brand brand = this.brandRepository.findById(id).orElseThrow();

        GetByIdBrandResponse response = this.modelMapperService.forResponse()
				.map(brand, GetByIdBrandResponse.class);
        return response;
    }

    @Override
    public void update(UpdateBrandRequest updateBrandRequest) {
      businessRules.checkIfBrandIdExists(updateBrandRequest.getId());
      businessRules.checkIfBrandNameExists(updateBrandRequest.getName());

         Brand brand = this.modelMapperService.forRequest()
				.map(updateBrandRequest, Brand.class);
		
		this.brandRepository.save(brand);
    }

    @Override
    public void deleteById(int id) {
      businessRules.checkIfBrandIdExists(id);
      
        this.brandRepository.deleteById(id);
    }

    
}