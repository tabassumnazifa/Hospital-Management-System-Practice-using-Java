package com.example.hospital.controller;

import com.example.hospital.dtos.AppointmentReqDto;
import com.example.hospital.dtos.AppointmentResponseDto;
import com.example.hospital.dtos.DoctorReqDto;
import com.example.hospital.dtos.DoctorResponseDto;
import com.example.hospital.dtos.PatientReqDto;
import com.example.hospital.dtos.PatientResponseDto;
import com.example.hospital.dtos.UpdateDoctorDto;
import com.example.hospital.dtos.UpdatePatientDto;
import com.example.hospital.service.HospitalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/hospital")
@RequiredArgsConstructor
public class HospitalController {

    private final HospitalService service;

   
    @PostMapping("/patients")
    public ResponseEntity<PatientResponseDto> addPatient(@Valid @RequestBody PatientReqDto dto) {
        PatientResponseDto response = service.addPatient(dto);
        return ResponseEntity
                .created(URI.create("/api/hospital/patients/" + response.getId()))
                .body(response);
    }

    @PostMapping("/doctors")
    public ResponseEntity<DoctorResponseDto> addDoctor(@Valid @RequestBody DoctorReqDto dto) {
        DoctorResponseDto response = service.addDoctor(dto);
        return ResponseEntity
                .created(URI.create("/api/hospital/doctors/" + response.getId()))
                .body(response);
    }

    @PostMapping("/appointments")
    public ResponseEntity<AppointmentResponseDto> bookAppointment(@Valid @RequestBody AppointmentReqDto dto) {
        AppointmentResponseDto response = service.bookAppointment(dto);
        return ResponseEntity
                .created(URI.create("/api/hospital/appointments/" + response.getId()))
                .body(response);
    }

    

    @GetMapping("/patients")
    public Page<PatientResponseDto> getPatients(Pageable pageable) {
        return service.getAllPatients(pageable);
    }

    @GetMapping("/patients/{id}")
    public PatientResponseDto getPatientById(@PathVariable Long id) {
        return service.getPatientById(id);
    }

    @PutMapping("/patients/{id}")
    public PatientResponseDto updatePatient(@PathVariable Long id, @Valid @RequestBody UpdatePatientDto dto) {
        return service.updatePatient(id, dto);
    }

    @GetMapping("/doctors")
    public Page<DoctorResponseDto> getDoctors(Pageable pageable) {
        return service.getAllDoctors(pageable);
    }

    @GetMapping("/doctors/{id}")
    public DoctorResponseDto getDoctorById(@PathVariable Long id) {
        return service.getDoctorById(id);
    }

    @PutMapping("/doctors/{id}")
    public DoctorResponseDto updateDoctor(@PathVariable Long id, @Valid @RequestBody UpdateDoctorDto dto) {
        return service.updateDoctor(id, dto);
    }

    @GetMapping("/appointments")
    public Page<AppointmentResponseDto> getAppointments(Pageable pageable) {
        return service.getAllAppointments(pageable);
    }
}          