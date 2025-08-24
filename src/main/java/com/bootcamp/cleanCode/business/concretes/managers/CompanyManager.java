package com.bootcamp.cleanCode.business.concretes.managers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bootcamp.cleanCode.business.abstracts.CompanyService;
import com.bootcamp.cleanCode.business.concretes.requests.companyRequests.CreateCompanyRequest;
import com.bootcamp.cleanCode.business.concretes.requests.companyRequests.UpdateCompanyRequest;
import com.bootcamp.cleanCode.business.concretes.responses.companyResponses.GetAllCompaniesResponses;
import com.bootcamp.cleanCode.business.concretes.responses.companyResponses.GetByIdCompanyResponse;
import com.bootcamp.cleanCode.business.concretes.rules.CompanyBusinessRules;
import com.bootcamp.cleanCode.core.utilities.exceptions.BusinessException;
import com.bootcamp.cleanCode.core.utilities.mappers.ModelMapperService;
import com.bootcamp.cleanCode.dataAccess.abstracts.CompanyRepository;
import com.bootcamp.cleanCode.dataAccess.abstracts.RoleRepository;
import com.bootcamp.cleanCode.entities.Company;
import com.bootcamp.cleanCode.entities.Role;

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class CompanyManager implements CompanyService{
    private CompanyRepository companyRepository;
    private ModelMapperService modelMapperService;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private CompanyBusinessRules businessRules;

    @Override
    public List<GetAllCompaniesResponses> getAll() {
        List<Company> companies = companyRepository.findAll();
        List<GetAllCompaniesResponses> companiesResponse = companies.stream()
				.map(company-> this.modelMapperService.forResponse()
				.map(company, GetAllCompaniesResponses.class))
				.collect(Collectors.toList());
        return companiesResponse;
    }

    @Override
    public GetByIdCompanyResponse getById(int id) {
      businessRules.checkIfCompanyExistsById(id);

        Company company = this.companyRepository.findById(id).orElseThrow();
        GetByIdCompanyResponse response = this.modelMapperService.forResponse()
				.map(company, GetByIdCompanyResponse.class);
        return response;
    }

    @Override
    public void add(CreateCompanyRequest createCompanyRequest) {
      businessRules.checkIfEmailExists(createCompanyRequest.getEmail());
      businessRules.checkIfPhoneExists(createCompanyRequest.getPhone());
      Role companyRole = roleRepository.findByName("COMPANY")
            .orElseThrow(() -> new BusinessException("Rol bulunamadı"));

        Company company = this.modelMapperService.forRequest()
				.map(createCompanyRequest, Company.class);
        company.setRole(companyRole);

        company.setPassword(passwordEncoder.encode(createCompanyRequest.getPassword()));
        this.companyRepository.save(company);
    }

    @Override
    public void update(UpdateCompanyRequest updateCompanyRequest) {
      businessRules.checkIfEmailExists(updateCompanyRequest.getEmail());
      businessRules.checkIfPhoneExists(updateCompanyRequest.getPhone());
      businessRules.checkIfCompanyExistsById(updateCompanyRequest.getId());

        Company company = this.modelMapperService.forRequest()
				.map(updateCompanyRequest, Company.class);
         this.companyRepository.save(company);
    }

    @Override
    public void deleteById(int id) {
        this.companyRepository.deleteById(id);
    }
    
}
