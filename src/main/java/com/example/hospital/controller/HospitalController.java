package com.example.hospital.controller;

import com.example.hospital.dtos.AppointmentReqDto;
import com.example.hospital.dtos.DoctorReqDto;
import com.example.hospital.dtos.PatientReqDto;
import com.example.hospital.model.Appointment;
import com.example.hospital.model.Doctor;
import com.example.hospital.model.Patient;
import com.example.hospital.service.HospitalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hospital")
@RequiredArgsConstructor
public class HospitalController {

    private final HospitalService service;

    @PostMapping("/patients")
    public Patient addPatient(@Valid @RequestBody PatientReqDto dto) {
        return service.addPatient(dto);
    }

    @PostMapping("/doctors")
    public Doctor addDoctor(@Valid @RequestBody DoctorReqDto dto) {
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

    @GetMapping("/patients/{id}")
    public Patient getPatientById(@PathVariable Long id) {
        return service.getPatientById(id);  
    }

    @GetMapping("/doctors/{id}")
    public Doctor getDoctorById(@PathVariable Long id) {
        return service.getDoctorById(id);  
    }

    // ========== Appointments ==========
    @PostMapping("/appointments")
    public Appointment bookAppointment(@Valid @RequestBody AppointmentReqDto dto) {
        return service.bookAppointment(dto);
    }

    @GetMapping("/appointments")
    public List<Appointment> getAppointments() {
        return service.getAllAppointments();
    }
}