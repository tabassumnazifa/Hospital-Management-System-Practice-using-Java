package com.example.hospital.dtos;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;  

import java.time.LocalDate;

@Data
public class AppointmentReqDto {

    @NotNull(message = "patientId is required")         
    private Long patientId;

    @NotNull(message = "doctorId is required")           
    private Long doctorId;

    @NotBlank(message = "Reason cannot be empty")       
    @Size(max = 200, message = "Reason must be within 200 characters")
    private String reason;

    @NotNull(message = "Date is required")           
    @FutureOrPresent(message = "Appointment date cannot be in the past")
    private LocalDate date;
}