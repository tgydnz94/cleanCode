package com.bootcamp.cleanCode.business.concretes.managers;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bootcamp.cleanCode.business.abstracts.AuthService;
import com.bootcamp.cleanCode.business.concretes.requests.loginRequests.LoginRequest;
import com.bootcamp.cleanCode.business.concretes.requests.registerRequests.RegisterRequest;
import com.bootcamp.cleanCode.business.concretes.responses.loginResponses.LoginResponse;
import com.bootcamp.cleanCode.business.security.JwtTokenProvider;
import com.bootcamp.cleanCode.core.utilities.exceptions.BusinessException;
import com.bootcamp.cleanCode.core.utilities.mappers.ModelMapperService;
import com.bootcamp.cleanCode.dataAccess.abstracts.CompanyRepository;
import com.bootcamp.cleanCode.dataAccess.abstracts.CustomerRepository;
import com.bootcamp.cleanCode.dataAccess.abstracts.RoleRepository;
import com.bootcamp.cleanCode.dataAccess.abstracts.WorkerRepository;
import com.bootcamp.cleanCode.entities.Company;
import com.bootcamp.cleanCode.entities.Customer;
import com.bootcamp.cleanCode.entities.Role;
import com.bootcamp.cleanCode.entities.Worker;
import com.bootcamp.cleanCode.entities.userBase.UserBase;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthManager implements AuthService {

    private CompanyRepository companyRepository;
    private CustomerRepository customerRepository;
    private WorkerRepository adminRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private JwtTokenProvider jwtTokenProvider;
    private ModelMapperService modelMapperService;

    @Override
    public void register(RegisterRequest request) {
        boolean emailExists = companyRepository.existsByEmail(request.getEmail())
                || customerRepository.existsByEmail(request.getEmail())
                || adminRepository.existsByEmail(request.getEmail());

        if (emailExists) {
            throw new BusinessException("Bu email zaten kayıtlı.");
        }

        Role role = roleRepository.findByName(request.getUserType().toUpperCase())
                .orElseThrow(() -> new BusinessException("Rol bulunamadı."));

        switch (request.getUserType().toUpperCase()) {
            case "COMPANY":
                Company company = modelMapperService.forRequest().map(request, Company.class);
                company.setPassword(passwordEncoder.encode(request.getPassword()));
                company.setRole(role);
                companyRepository.save(company);
                break;
            case "CUSTOMER":
                Customer customer = modelMapperService.forRequest().map(request, Customer.class);
                customer.setPassword(passwordEncoder.encode(request.getPassword()));
                customer.setRole(role);
                customerRepository.save(customer);
                break;
            case "ADMIN":
                Worker admin = modelMapperService.forRequest().map(request, Worker.class);
                admin.setPassword(passwordEncoder.encode(request.getPassword()));
                admin.setRole(role);
                adminRepository.save(admin);
                break;
            default:
                throw new BusinessException("Geçersiz kullanıcı türü.");
        }
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        Optional<? extends UserBase> userOptional = companyRepository.findByEmail(request.getEmail());
        String userType = "COMPANY";

        if (userOptional.isEmpty()) {
            userOptional = customerRepository.findByEmail(request.getEmail());
            userType = "CUSTOMER";
        }
        if (userOptional.isEmpty()) {
            userOptional = adminRepository.findByEmail(request.getEmail());
            userType = "ADMIN";
        }

        if (userOptional.isEmpty()) {
            throw new BusinessException("Email veya şifre hatalı.");
        }

        UserBase user = userOptional.get();

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BusinessException("Email veya şifre hatalı.");
        }

        String token = jwtTokenProvider.createToken(user.getEmail(), List.of(userType));

        LoginResponse response = new LoginResponse();
        response.setToken(token);
        response.setEmail(user.getEmail());
        response.setName(user.getName());
        response.setUserType(userType);

        return response;
    }
}
