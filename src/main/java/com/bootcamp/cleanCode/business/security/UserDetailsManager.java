package com.bootcamp.cleanCode.business.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bootcamp.cleanCode.dataAccess.abstracts.CompanyRepository;
import com.bootcamp.cleanCode.dataAccess.abstracts.CustomerRepository;
import com.bootcamp.cleanCode.dataAccess.abstracts.WorkerRepository;
import com.bootcamp.cleanCode.entities.userBase.UserBase;

@Service
public class UserDetailsManager implements UserDetailsService {

    @Autowired private CompanyRepository companyRepo;
    @Autowired private CustomerRepository customerRepo;
    @Autowired private WorkerRepository adminRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserBase user;

        if (companyRepo.findByEmail(email).isPresent()) {
            user = companyRepo.findByEmail(email).get();
        } else if (customerRepo.findByEmail(email).isPresent()) {
            user = customerRepo.findByEmail(email).get();
        } else if (adminRepo.findByEmail(email).isPresent()) {
            user = adminRepo.findByEmail(email).get();
        } else {
            throw new UsernameNotFoundException("User not found");
        }

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                List.of(new SimpleGrantedAuthority(user.getRole().getName()))
        );
    }
}
