package com.tharishaperera.toothcare.services;

import com.tharishaperera.toothcare.models.Treatment;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TreatmentService {
    // get all treatments
    public List<Treatment> getAllTreatments() {
        return Treatment.treatments;
    }

    // get treatment by id
    public Optional<Treatment> getTreatmentById(long treatmentId) {
        return Treatment.treatments.stream()
                .filter(treatment -> treatment.getTreatmentId() == treatmentId)
                .findFirst();
    }

    // get treatments by appointment id
    public Optional<Treatment> getTreatmentByAppointmentId(long appointmentId) {
        return Treatment.treatments.stream()
                .filter(treatment -> treatment.getAppointment().getAppointmentId() == appointmentId)
                .findFirst();
    }

    // create schedule
    public Treatment createTreatment(Treatment treatment) {
        Treatment.treatments.add(treatment);
        return treatment;
    }

    // update treatment
    public Optional<Treatment> updateTreatment(long id, Treatment updatedTreatment) {
        Optional<Treatment> treatmentToUpdate = Treatment.treatments.stream()
                .filter(treatment -> treatment.getTreatmentId() == id)
                .findFirst();

        treatmentToUpdate.ifPresent(treatment -> {
            treatment.setAppointment(updatedTreatment.getAppointment());
            treatment.setTreatmentTypes(updatedTreatment.getTreatmentTypes());
        });
        return treatmentToUpdate;
    }

    // delete treatment
    public boolean deleteTreatment(long id) {
        return Treatment.treatments.removeIf(treatment -> treatment.getTreatmentId() == id);
    }
}
