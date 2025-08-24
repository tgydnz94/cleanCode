package com.bootcamp.cleanCode.business.concretes.managers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bootcamp.cleanCode.business.abstracts.CustomerService;
import com.bootcamp.cleanCode.business.concretes.requests.customerRequests.CreateCustomerRequest;
import com.bootcamp.cleanCode.business.concretes.requests.customerRequests.UpdateCustomerRequest;
import com.bootcamp.cleanCode.business.concretes.responses.customerResponses.GetAllCustomersResponse;
import com.bootcamp.cleanCode.business.concretes.responses.customerResponses.GetByIdCustomerResponse;
import com.bootcamp.cleanCode.business.concretes.rules.CustomerBusinessRules;
import com.bootcamp.cleanCode.core.utilities.exceptions.BusinessException;
import com.bootcamp.cleanCode.core.utilities.mappers.ModelMapperService;
import com.bootcamp.cleanCode.dataAccess.abstracts.CustomerRepository;
import com.bootcamp.cleanCode.dataAccess.abstracts.RoleRepository;
import com.bootcamp.cleanCode.entities.Customer;
import com.bootcamp.cleanCode.entities.Role;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomerManager implements CustomerService{
    private CustomerRepository customerRepository;
    private CustomerBusinessRules businessRules;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private ModelMapperService modelMapperService;

    @Override
    public List<GetAllCustomersResponse> getAll() {
        List<Customer> customers = customerRepository.findAll();
        List<GetAllCustomersResponse> customersResponse = customers.stream()
				.map(customer-> this.modelMapperService.forResponse()
				.map(customer, GetAllCustomersResponse.class))
				.collect(Collectors.toList());
        return customersResponse;
    }

    @Override
    public GetByIdCustomerResponse getById(int id) {
        businessRules.checkIfCustomerExists(id);

        Customer customer = this.customerRepository.findById(id).orElseThrow();
        GetByIdCustomerResponse response = this.modelMapperService.forResponse()
				.map(customer, GetByIdCustomerResponse.class);
        return response;
    }

    @Override
    public void add(CreateCustomerRequest createCustomerRequest) {
      businessRules.checkIfEmailExists(createCustomerRequest.getEmail());
      Role userRole = roleRepository.findByName("USER")
            .orElseThrow(() -> new BusinessException("Rol bulunamadı"));

        Customer customer = this.modelMapperService.forRequest()
				.map(createCustomerRequest, Customer.class);
        customer.setRole(userRole);

        customer.setPassword(passwordEncoder.encode(createCustomerRequest.getPassword()));
        this.customerRepository.save(customer);
    }

    @Override
    public void update(UpdateCustomerRequest updateCustomerRequest) {
      businessRules.checkIfCustomerExists(updateCustomerRequest.getId());

        Customer customer = this.modelMapperService.forRequest()
				.map(updateCustomerRequest, Customer.class);
         this.customerRepository.save(customer);
    }

    @Override
    public void deleteById(int id) {
        this.customerRepository.deleteById(id);
    }
    
}
