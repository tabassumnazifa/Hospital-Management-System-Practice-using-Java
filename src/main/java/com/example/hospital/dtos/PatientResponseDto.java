package com.example.hospital.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PatientResponseDto {

    private Long id;
    private String name;
    private int age;
}