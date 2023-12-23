package com.tharishaperera.toothcare.controllers;

import com.tharishaperera.toothcare.models.Patient;
import com.tharishaperera.toothcare.services.PatientService;
import com.tharishaperera.toothcare.utils.CheckExistingEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users/patients")
@CrossOrigin(origins = "${frontend.allowed-origin}")
public class PatientController {
    @Autowired
    private PatientService patientService;

    // get all patients
    @GetMapping()
    public List<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }

    // get patient by id
    @GetMapping("/{userId}")
    public Optional<Patient> getPatientById(@PathVariable long userId) {
        return patientService.getPatientById(userId);
    }

    // create patient
    @PostMapping()
    public Patient createPatient(@RequestBody Patient patient) {
        if (CheckExistingEmail.checkEmailExists(patient.getEmail())) {
            return null;
        }
        Patient newPatient = Patient.createPatient(patient.getFirstName(), patient.getLastName(), patient.getEmail(), patient.getMobile(), patient.getAddress(), patient.getDob());
        return patientService.createPatient(newPatient);
    }

    // update patient
    @PutMapping("/{userId}")
    public void updatePatient(@PathVariable long userId, @RequestBody Patient updatedPatient) {
        patientService.updatePatientById(userId, updatedPatient);
    }

    // delete patient by id
    @DeleteMapping("/{userId}")
    public void deletePatient(@PathVariable long userId) {
        patientService.deletePatientById(userId);
    }
}
