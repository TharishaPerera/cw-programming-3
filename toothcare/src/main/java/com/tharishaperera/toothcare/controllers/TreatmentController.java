package com.tharishaperera.toothcare.controllers;

import com.tharishaperera.toothcare.models.Treatment;
import com.tharishaperera.toothcare.services.TreatmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/treatments")
public class TreatmentController {
    @Autowired
    private TreatmentService treatmentService;

    // get all treatments
    @GetMapping
    public List<Treatment> getAllTreatments() {
        return treatmentService.getAllTreatments();
    }

    // get  treatment by id
    @GetMapping("/{id}")
    public Optional<Treatment> getTreatmentById(@PathVariable Long id) {
        return treatmentService.getTreatmentById(id);
    }

    // create treatment
    @PostMapping
    public Treatment createTreatment(@RequestBody Treatment treatment) {
        Treatment newTreatment = Treatment.createTreatment(treatment.getAppointment(), treatment.getTreatmentTypes());
        return treatmentService.createTreatment(newTreatment);
    }

    // update treatment
    @PutMapping("/{id}")
    public Optional<Treatment> updateTreatment(@PathVariable Long id, @RequestBody Treatment treatment) {
        return treatmentService.updateTreatment(id, treatment);
    }

    // delete treatment
    @DeleteMapping("/{id}")
    public boolean deleteTreatment(@PathVariable Long id) {
        return treatmentService.deleteTreatment(id);
    }
}
