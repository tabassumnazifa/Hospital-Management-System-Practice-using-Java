package com.example.hospital.service;

import com.example.hospital.dtos.AppointmentReqDto;
import com.example.hospital.dtos.AppointmentResponseDto;
import com.example.hospital.dtos.DoctorReqDto;
import com.example.hospital.dtos.DoctorResponseDto;
import com.example.hospital.dtos.PatientReqDto;
import com.example.hospital.dtos.PatientResponseDto;
import com.example.hospital.dtos.UpdateDoctorDto;
import com.example.hospital.dtos.UpdatePatientDto;
import com.example.hospital.exception.ResourceNotFoundException;
import com.example.hospital.model.Appointment;
import com.example.hospital.model.Doctor;
import com.example.hospital.model.Patient;
import com.example.hospital.model.Person;
import com.example.hospital.repo.AppointmentRepo;
import com.example.hospital.repo.DoctorRepo;
import com.example.hospital.repo.PatientRepo;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HospitalService {

    private final PatientRepo patientRepo;
    private final DoctorRepo doctorRepo;
    private final AppointmentRepo appointmentRepo;
    
    // 🌫️ Abstraction + Dependency Injection:
    // আমরা সরাসরি EmailNotificationService কে ইনজেক্ট করিনি!
    // আমরা শুধু Interface (NotificationService) কে ইনজেক্ট করেছি।
    private final NotificationService notificationService;

    // ==================== CREATE PATIENT ====================

    public PatientResponseDto addPatient(PatientReqDto dto) {

        Patient patient = new Patient();
        patient.setName(dto.getName());
        patient.setAge(dto.getAge());

        Patient savedPatient = patientRepo.save(patient);

        return new PatientResponseDto(
                savedPatient.getId(),
                savedPatient.getName(),
                savedPatient.getAge()
        );
    }

    // ==================== CREATE DOCTOR ====================

    public DoctorResponseDto addDoctor(DoctorReqDto dto) {

        Doctor doctor = new Doctor();
        doctor.setName(dto.getName());
        doctor.setSpecialization(dto.getSpecialization());

        Doctor savedDoctor = doctorRepo.save(doctor);

        return new DoctorResponseDto(
                savedDoctor.getId(),
                savedDoctor.getName(),
                savedDoctor.getSpecialization()
        );
    }

    // ==================== GET ALL PATIENTS ====================

    public Page<PatientResponseDto> getAllPatients(Pageable pageable) {
        return patientRepo.findAll(pageable)
                .map(patient -> new PatientResponseDto(
                        patient.getId(),
                        patient.getName(),
                        patient.getAge()
                ));
    }

    // ==================== GET ALL DOCTORS ====================

    public Page<DoctorResponseDto> getAllDoctors(Pageable pageable) {
        return doctorRepo.findAll(pageable)
                .map(doctor -> new DoctorResponseDto(
                        doctor.getId(),
                        doctor.getName(),
                        doctor.getSpecialization()
                ));
    }

    // ==================== GET ALL APPOINTMENTS ====================

    public Page<AppointmentResponseDto> getAllAppointments(Pageable pageable) {
        return appointmentRepo.findAll(pageable)
                .map(appointment -> new AppointmentResponseDto(
                        appointment.getId(),
                        appointment.getPatient().getId(),
                        appointment.getPatient().getName(),
                        appointment.getDoctor().getId(),
                        appointment.getDoctor().getName(),
                        appointment.getReason(),
                        appointment.getDate()
                ));
    }

    // ==================== GET PATIENT BY ID ====================

    public PatientResponseDto getPatientById(Long id) {
        Patient patient = patientRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Patient not found with id " + id
                        )
                );

        return new PatientResponseDto(
                patient.getId(),
                patient.getName(),
                patient.getAge()
        );
    }

    // ==================== GET DOCTOR BY ID ====================

    public DoctorResponseDto getDoctorById(Long id) {
        Doctor doctor = doctorRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Doctor not found with id " + id
                        )
                );

        return new DoctorResponseDto(
                doctor.getId(),
                doctor.getName(),
                doctor.getSpecialization()
        );
    }

    // ==================== 📦 BOOK APPOINTMENT (ENCAPSULATED + ABSTRACTED!) ====================

    public AppointmentResponseDto bookAppointment(AppointmentReqDto dto) {

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
        appointment.book(patient, doctor, dto.getReason(), dto.getDate());

        Appointment savedAppointment = appointmentRepo.save(appointment);

        
        String recipient = patient.getName(); 
        String message = "Dear " + patient.getName() + ", your appointment with Dr. " 
                         + doctor.getName() + " on " + savedAppointment.getDate() + " is confirmed!";
        
        notificationService.send(recipient, message);

        return new AppointmentResponseDto(
                savedAppointment.getId(),
                savedAppointment.getPatient().getId(),
                savedAppointment.getPatient().getName(),
                savedAppointment.getDoctor().getId(),
                savedAppointment.getDoctor().getName(),
                savedAppointment.getReason(),
                savedAppointment.getDate()
        );
    }

    // ==================== UPDATE PATIENT ====================

    public PatientResponseDto updatePatient(Long id, UpdatePatientDto dto) {
        Patient patient = patientRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id " + id));

        patient.setName(dto.getName());
        patient.setAge(dto.getAge());

        Patient updatedPatient = patientRepo.save(patient);

        return new PatientResponseDto(
                updatedPatient.getId(),
                updatedPatient.getName(),
                updatedPatient.getAge()
        );
    }

    // ==================== UPDATE DOCTOR ====================

    public DoctorResponseDto updateDoctor(Long id, UpdateDoctorDto dto) {
        Doctor doctor = doctorRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id " + id));

        doctor.setName(dto.getName());
        doctor.setSpecialization(dto.getSpecialization());

        Doctor updatedDoctor = doctorRepo.save(doctor);

        return new DoctorResponseDto(
                updatedDoctor.getId(),
                updatedDoctor.getName(),
                updatedDoctor.getSpecialization()
        );
    }

    // ==================== POLYMORPHISM DEMO ====================

    public List<String> describeAllPeople() {
        List<Person> people = new ArrayList<>();
        people.addAll(patientRepo.findAll());
        people.addAll(doctorRepo.findAll());

        return people.stream()
                .map(person -> person.getRole() + " : " + person.getName())
                .toList();
    }
}