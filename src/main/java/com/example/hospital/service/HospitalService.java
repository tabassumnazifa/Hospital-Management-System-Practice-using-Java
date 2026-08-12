package com.example.hospital.service;

import com.example.hospital.dtos.AppointmentReqDto;
import com.example.hospital.dtos.DoctorReqDto;
import com.example.hospital.dtos.PatientReqDto;
import com.example.hospital.model.Appointment;
import com.example.hospital.model.Doctor;
import com.example.hospital.model.Patient;
import com.example.hospital.repo.AppointmentRepo;
import com.example.hospital.repo.DoctorRepo;
import com.example.hospital.repo.PatientRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HospitalService {

    private final PatientRepo patientRepo;
    private final DoctorRepo doctorRepo;
    private final AppointmentRepo appointmentRepo;

    // Create a Patient from PatientReqDto
    public Patient addPatient(PatientReqDto dto) {

        Patient patient = new Patient();

        patient.setName(dto.getName());
        patient.setAge(dto.getAge());

        return patientRepo.save(patient);
    }

    // Create a Doctor from DoctorReqDto
    public Doctor addDoctor(DoctorReqDto dto) {

        Doctor doctor = new Doctor();

        doctor.setName(dto.getName());
        doctor.setSpecialization(dto.getSpecialization());

        return doctorRepo.save(doctor);
    }

    // Get all patients
    public List<Patient> getAllPatients() {
        return patientRepo.findAll();
    }

    // Get all doctors
    public List<Doctor> getAllDoctors() {
        return doctorRepo.findAll();
    }

    // Book an appointment
    public Appointment bookAppointment(AppointmentReqDto dto) {

        Patient patient = patientRepo.findById(dto.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        Doctor doctor = doctorRepo.findById(dto.getDoctorId())
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        Appointment appointment = new Appointment();

        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setReason(dto.getReason());

        return appointmentRepo.save(appointment);
    }

    // Get all appointments
    public List<Appointment> getAllAppointments() {
        return appointmentRepo.findAll();
    }
}