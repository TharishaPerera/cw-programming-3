package com.tharishaperera.toothcare.requestTypes;

import com.tharishaperera.toothcare.config.enums.Status;
import com.tharishaperera.toothcare.models.Dentist;
import com.tharishaperera.toothcare.models.Patient;

import java.time.LocalDate;
import java.time.LocalTime;

public class AppointmentRequest {
    private LocalDate appointmentDate;
    private LocalTime appointmentTime;
    private Patient patient;
    private Dentist dentist;
    private Status regFeeStatus;

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public LocalTime getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(LocalTime appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Dentist getDentist() {
        return dentist;
    }

    public void setDentist(Dentist dentist) {
        this.dentist = dentist;
    }

    public Status getRegFeeStatus() {
        return regFeeStatus;
    }

    public void setRegFeeStatus(Status regFeeStatus) {
        this.regFeeStatus = regFeeStatus;
    }
}
