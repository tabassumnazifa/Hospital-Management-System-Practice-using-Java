package com.example.hospital.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "doctors")
@Getter
@Setter
public class Doctor extends Person {

    @Column(nullable = false, length = 50)
    private String specialization;

    @Override
    public String getRole() {
        return "Doctor";
    }
}