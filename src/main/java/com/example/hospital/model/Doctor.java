package com.example.hospital.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "doctors")                   
@Setter
@Getter
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Doctor name cannot be empty")
    @Size(min = 3, max = 50, message = "Name must be 3-50 characters")
    @Column(nullable = false, length = 50)
    private String name;

    @NotBlank(message = "Specialization is required")
    @Size(min = 2, max = 50, message = "Specialization must be 2-50 characters")
    @Column(nullable = false, length = 50)
    private String specialization;
}