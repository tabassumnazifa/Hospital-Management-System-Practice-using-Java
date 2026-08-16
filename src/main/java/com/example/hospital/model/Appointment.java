package com.example.hospital.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;

import java.time.LocalDate;

@Entity
@Table(name = "appointments")
@Getter 
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @ManyToOne(optional = false)
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    @Column(nullable = false, length = 200)
    private String reason;

    @Column(nullable = false)
    private LocalDate date;

    public void book(Patient patient, Doctor doctor, String reason, LocalDate date) {
        
        if (patient == null || doctor == null) {
            throw new IllegalArgumentException("Patient and Doctor cannot be null!");
        }
        
        if (reason == null || reason.isBlank()) {
            throw new IllegalArgumentException("Reason cannot be empty!");
        }
        
    
        if (date.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Appointment date cannot be in the past!");
        }

        this.patient = patient;
        this.doctor = doctor;
        this.reason = reason;
        this.date = date;
    }
}