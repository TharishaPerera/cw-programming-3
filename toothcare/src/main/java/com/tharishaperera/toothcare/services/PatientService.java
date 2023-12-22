package com.tharishaperera.toothcare.services;

import com.tharishaperera.toothcare.models.Patient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PatientService {
    public static final List<Patient> patientList = new ArrayList<>();

    // get all patients
    public List<Patient> getAllPatients() {
        return patientList;
    }

    // create patient
    public Patient createPatient(Patient patient) {
        patientList.add(patient);
        return patient;
    }

    // get patient by user id
    public Optional<Patient> getPatientById(long userId) {
        return patientList.stream()
                .filter(patient -> patient.getUserId() == userId)
                .findFirst();
    }

    // update patients by id
    public void updatePatientById(long userId, Patient updatedPatient) {
        Optional<Patient> patientToUpdate = patientList.stream()
                .filter(patient -> patient.getUserId() == userId)
                .findFirst();

        patientToUpdate.ifPresent(patient -> {
            patient.setFirstName(updatedPatient.getFirstName());
            patient.setLastName(updatedPatient.getLastName());
            patient.setEmail(updatedPatient.getEmail());
            patient.setMobile(updatedPatient.getMobile());
            patient.setAddress(updatedPatient.getAddress());
            patient.setDob(updatedPatient.getDob());
        });
    }

    // delete patient by id
    public void deletePatientById(long userId) {
        patientList.removeIf(patient -> patient.getUserId() == userId);
    }
}
