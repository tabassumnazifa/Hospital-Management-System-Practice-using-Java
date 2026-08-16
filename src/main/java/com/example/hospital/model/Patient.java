package com.example.hospital.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "patients")
@Getter
@Setter
public class Patient extends Person {

    @Column(nullable = false)        
    private Integer age;

    @Override
    public String getRole() {
        return "Patient";
    }
}