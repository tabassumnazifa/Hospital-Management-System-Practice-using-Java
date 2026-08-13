package com.example.hospital.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DoctorResponseDto {

    private Long id;
    private String name;
    private String specialization;
}