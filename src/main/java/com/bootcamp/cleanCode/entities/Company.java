package com.bootcamp.cleanCode.entities;

import java.util.List;

import com.bootcamp.cleanCode.entities.userBase.UserBase;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Table(name = "companies")
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Company implements UserBase  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "company")
    private List<Car> cars;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;


    
}
