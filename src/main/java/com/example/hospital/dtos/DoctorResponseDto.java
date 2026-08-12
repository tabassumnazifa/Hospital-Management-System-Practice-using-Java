package com.example.hospital.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DoctorResponseDto {

    private Long id;
    private String name;
    private String specialization;
}