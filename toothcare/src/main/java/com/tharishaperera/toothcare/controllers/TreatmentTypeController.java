package com.tharishaperera.toothcare.controllers;

import com.tharishaperera.toothcare.models.TreatmentType;
import com.tharishaperera.toothcare.requestTypes.TreatmentTypeRequest;
import com.tharishaperera.toothcare.services.TreatmentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/treatment-types")
public class TreatmentTypeController {
    @Autowired
    private TreatmentTypeService treatmentTypeService;

    // get all treatment types
    @GetMapping
    public List<TreatmentType> getAllTreatmentTypes() {
        return treatmentTypeService.getAllTreatmentTypes();
    }

    // get treatment type by id
    @GetMapping("/{id}")
    public ResponseEntity<TreatmentType> getTreatmentTypeById(@PathVariable long id) {
        Optional<TreatmentType> treatmentType = treatmentTypeService.getTreatmentTypeById(id);
        return treatmentType.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // create a new treatment type
    @PostMapping
    public ResponseEntity<TreatmentType> createTreatment(@RequestBody TreatmentTypeRequest treatmentTypeRequest) {
        TreatmentType newTreatmentType = treatmentTypeService.createTreatmentType(treatmentTypeRequest.getTreatmentTypeName(), treatmentTypeRequest.getTreatmentTypePrice());
        return ResponseEntity.ok(newTreatmentType);
    }

}
