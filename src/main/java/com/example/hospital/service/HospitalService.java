package com.example.hospital.service;

import com.example.hospital.dtos.AppointmentReqDto;
import com.example.hospital.dtos.DoctorReqDto;
import com.example.hospital.dtos.PatientReqDto;
import com.example.hospital.exception.ResourceNotFoundException;
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

    public Patient addPatient(PatientReqDto dto) {

        Patient patient = new Patient();

        patient.setName(dto.getName());
        patient.setAge(dto.getAge());

        return patientRepo.save(patient);
    }

    public Doctor addDoctor(DoctorReqDto dto) {

        Doctor doctor = new Doctor();

        doctor.setName(dto.getName());
        doctor.setSpecialization(dto.getSpecialization());

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


    public Patient getPatientById(Long id) {

        return patientRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Patient not found with id " + id
                        )
                );
    }


    public Doctor getDoctorById(Long id) {

        return doctorRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Doctor not found with id " + id
                        )
                );
    }


    public Appointment bookAppointment(AppointmentReqDto dto) {

    
        Patient patient = patientRepo.findById(dto.getPatientId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Patient not found with id " + dto.getPatientId()
                        )
                );

        Doctor doctor = doctorRepo.findById(dto.getDoctorId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Doctor not found with id " + dto.getDoctorId()
                        )
                );

        Appointment appointment = new Appointment();

        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setReason(dto.getReason());
        appointment.setDate(dto.getDate());

        // Save appointment to database.
        return appointmentRepo.save(appointment);
    }
}