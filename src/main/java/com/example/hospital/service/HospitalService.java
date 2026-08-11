package com.example.hospital.service;

import com.example.hospital.dtos.AppointmentReqDto;
import com.example.hospital.model.Appointment;
import com.example.hospital.model.Doctor;
import com.example.hospital.model.Patient;
import com.example.hospital.repo.AppointmentRepo;
import com.example.hospital.repo.DoctorRepo;
import com.example.hospital.repo.PatientRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class HospitalService {

    private final PatientRepo patientRepo;
    private final DoctorRepo doctorRepo;
    private final AppointmentRepo appointmentRepo;

    public HospitalService(PatientRepo patientRepo, DoctorRepo doctorRepo, AppointmentRepo appointmentRepo) {
        this.patientRepo = patientRepo;
        this.doctorRepo = doctorRepo;
        this.appointmentRepo = appointmentRepo;
    }

    public Patient addPatient(Patient patient) {
        return patientRepo.save(patient);
    }

    public Doctor addDoctor(Doctor doctor) {
        return doctorRepo.save(doctor);
    }

    public List<Patient> getAllPatients() {
        return patientRepo.findAll();
    }

    public List<Doctor> getAllDoctors() {
        return doctorRepo.findAll();
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepo.findAll();
    }

    public Appointment bookAppointment(AppointmentReqDto dto) {
        Patient patient = patientRepo.findById(dto.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient not found!"));

        Doctor doctor = doctorRepo.findById(dto.getDoctorId())
                .orElseThrow(() -> new RuntimeException("Doctor not found!"));

        Appointment appointment = new Appointment();
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setReason(dto.getReason());
        appointment.setDate(LocalDate.now());

        return appointmentRepo.save(appointment);
    }
}