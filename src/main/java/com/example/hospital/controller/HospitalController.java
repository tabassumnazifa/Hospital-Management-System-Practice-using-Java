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
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/hospital")
@RequiredArgsConstructor
public class HospitalController {

    private final HospitalService service;

    // ================= PATIENT =================

    @PostMapping("/patients")
    @ResponseStatus(HttpStatus.CREATED)
    public PatientResponseDto addPatient(@Valid @RequestBody PatientReqDto dto) {
        return service.addPatient(dto);
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

    // ================= DOCTOR =================

    @PostMapping("/doctors")
    @ResponseStatus(HttpStatus.CREATED)
    public DoctorResponseDto addDoctor(@Valid @RequestBody DoctorReqDto dto) {
        return service.addDoctor(dto);
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

    // ================= APPOINTMENT =================

    @PostMapping("/appointments")
    @ResponseStatus(HttpStatus.CREATED)
    public AppointmentResponseDto bookAppointment(@Valid @RequestBody AppointmentReqDto dto) {
        return service.bookAppointment(dto);
    }

    @GetMapping("/appointments")
    public Page<AppointmentResponseDto> getAppointments(Pageable pageable) {
        return service.getAllAppointments(pageable);
    }
}