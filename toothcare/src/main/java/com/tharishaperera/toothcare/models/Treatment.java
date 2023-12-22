package com.tharishaperera.toothcare.models;

import com.tharishaperera.toothcare.utils.Utils;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Treatment {
    public static final List<Treatment> treatments = new ArrayList<>();
    private long treatmentId;
    private Appointment appointment;
    private List<TreatmentType> treatmentTypes;

    public Treatment(long treatmentId, Appointment appointment, List<TreatmentType> treatmentTypes) {
        this.treatmentId = treatmentId;
        this.appointment = appointment;
        this.treatmentTypes = treatmentTypes;
    }
    public long getTreatmentId() {
        return treatmentId;
    }
    public void setTreatmentId(long treatmentId) {
        this.treatmentId = treatmentId;
    }
    public Appointment getAppointment() {
        return appointment;
    }
    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }
    public List<TreatmentType> getTreatmentTypes() {
        return treatmentTypes;
    }
    public void setTreatmentTypes(List<TreatmentType> treatmentTypes) {
        this.treatmentTypes = treatmentTypes;
    }

    // crate treatment
    public static Treatment createTreatment(Appointment appointment, List<TreatmentType> treatmentTypes) {
        long treatmentId = Utils.generateId();
        return new Treatment(treatmentId, appointment, treatmentTypes);
    }
}
