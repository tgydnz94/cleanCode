package com.bootcamp.cleanCode.dataAccess.abstracts;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bootcamp.cleanCode.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{
    boolean existsByNameIgnoreCase(String name);
    Optional<Role> findByName(String name);
}
