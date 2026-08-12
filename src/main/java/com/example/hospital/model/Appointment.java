package com.example.hospital.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "appointments")
@Getter
@Setter
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Patient is required")
    @ManyToOne(optional = false)                          
    @JoinColumn(name = "patient_id", nullable = false)    
    private Patient patient;

    @NotNull(message = "Doctor is required")
    @ManyToOne(optional = false)
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    @NotBlank(message = "Reason cannot be empty")
    @Size(max = 200, message = "Reason must be within 200 characters")
    @Column(nullable = false, length = 200)
    private String reason;

    @NotNull(message = "Date is required")
    @FutureOrPresent(message = "Appointment date cannot be in the past")  
    @Column(nullable = false)
    private LocalDate date;
}