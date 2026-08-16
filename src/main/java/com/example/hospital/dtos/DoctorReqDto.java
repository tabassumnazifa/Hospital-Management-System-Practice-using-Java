package com.example.hospital.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class DoctorReqDto {

    @NotBlank(message = "Doctor name cannot be empty")
    @Size(min = 3, max = 50, message = "Name must be 3-50 characters")
    private String name;

    @NotBlank(message = "Specialization is required")
    @Size(min = 2, max = 50, message = "Specialization must be 2-50 characters")
    private String specialization;
}