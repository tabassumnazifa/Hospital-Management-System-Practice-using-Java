package com.example.hospital.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "patients")                
@Getter
@Setter
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Patient name cannot be empty")     
    @Size(min = 3, max = 50, message = "Name must be 3-50 characters")  
    @Column(nullable = false, length = 50)                  
    private String name;

    @NotNull(message = "Age is required")                  
    @Positive(message = "Age must be a positive number")  
    @Column(nullable = false)                           
    private Integer age; 
}