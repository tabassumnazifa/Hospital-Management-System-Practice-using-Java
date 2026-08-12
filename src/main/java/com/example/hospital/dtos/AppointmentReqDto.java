package com.example.hospital.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppointmentReqDto {

    private Long patientId;
    private Long doctorId;
    private String reason;
}