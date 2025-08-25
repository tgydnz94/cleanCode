package com.bootcamp.cleanCode.entities;

import com.bootcamp.cleanCode.entities.userBase.UserBase;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "workers")
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Worker implements UserBase  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "firstName")
    private String name;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "position")
    private String position;

    @Column(name = "email")
    private String email;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "password")
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @Override
    public String getName() {
        return name;
    }
    
}
