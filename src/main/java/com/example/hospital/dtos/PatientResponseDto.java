package com.example.hospital.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PatientResponseDto {

    private Long id;
    private String name;
    private int age;
}