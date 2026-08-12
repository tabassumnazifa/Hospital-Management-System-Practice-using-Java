package com.example.hospital.controller;

import com.example.hospital.dtos.AppointmentReqDto;
import com.example.hospital.dtos.DoctorReqDto;
import com.example.hospital.dtos.PatientReqDto;
import com.example.hospital.model.Appointment;
import com.example.hospital.model.Doctor;
import com.example.hospital.model.Patient;
import com.example.hospital.service.HospitalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hospital")

// Lombok automatically generates the constructor
// for the final HospitalService field.
@RequiredArgsConstructor
public class HospitalController {

    private final HospitalService service;

    @PostMapping("/patients")
    public Patient addPatient(@RequestBody PatientReqDto dto) {  // using DTO 
        return service.addPatient(dto);
    }

    @PostMapping("/doctors")
    public Doctor addDoctor(@RequestBody DoctorReqDto dto) {
        return service.addDoctor(dto);
    }

    @GetMapping("/patients")
    public List<Patient> getPatients() {
        return service.getAllPatients();
    }

    @GetMapping("/doctors")
    public List<Doctor> getDoctors() {
        return service.getAllDoctors();
    }

    @PostMapping("/appointments")
    public Appointment bookAppointment(@RequestBody AppointmentReqDto dto) {
        return service.bookAppointment(dto);
    }

    @GetMapping("/appointments")
    public List<Appointment> getAppointments() {
        return service.getAllAppointments();
    }
}