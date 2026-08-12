package com.example.hospital.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientReqDto {

    @NotBlank(message = "Patient name cannot be empty")
    @Size(min = 3, max = 50, message = "Name must be 3-50 characters")
    private String name;

    @NotNull(message = "Age is required")
    @Positive(message = "Age must be a positive number")
    private Integer age;        
}