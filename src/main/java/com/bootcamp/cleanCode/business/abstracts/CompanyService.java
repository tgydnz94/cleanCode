package com.bootcamp.cleanCode.business.abstracts;

import java.util.List;

import com.bootcamp.cleanCode.business.concretes.requests.companyRequests.CreateCompanyRequest;
import com.bootcamp.cleanCode.business.concretes.requests.companyRequests.UpdateCompanyRequest;
import com.bootcamp.cleanCode.business.concretes.responses.companyResponses.GetAllCompaniesResponses;
import com.bootcamp.cleanCode.business.concretes.responses.companyResponses.GetByIdCompanyResponse;

public interface CompanyService {
    List<GetAllCompaniesResponses> getAll();
    GetByIdCompanyResponse getById(int id);
    void add(CreateCompanyRequest createCompanyRequest);
    void update(UpdateCompanyRequest updateCompanyRequest);
    void deleteById(int id);
        
}
    

