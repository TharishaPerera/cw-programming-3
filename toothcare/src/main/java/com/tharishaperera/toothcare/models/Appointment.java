package com.tharishaperera.toothcare.models;

import com.tharishaperera.toothcare.config.enums.Status;
import com.tharishaperera.toothcare.utils.Utils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

// builder design pattern appointment class
public class Appointment {
    public static final List<Appointment> appointments = new ArrayList<>();

    private long appointmentId;
    private LocalDate appointmentDate;
    private LocalTime appointmentTime;
    private Patient patient;
    private Dentist dentist;
    private Status status = Status.PENDING;
    private Status regFeeStatus = Status.PENDING;

    // private constructor used to prevent instantiation from outside the class
    private Appointment() {
    }

    public long getAppointmentId() {
        return appointmentId;
    }
    public void setAppointmentId(long appointmentId) {
        this.appointmentId = appointmentId;
    }
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
    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
    public Status getRegFeeStatus() {
        return regFeeStatus;
    }
    public void setRegFeeStatus(Status regFeeStatus) {
        this.regFeeStatus = regFeeStatus;
    }

    public static class Builder {
        private final Appointment appointment;

        public Builder() {
            this.appointment = new Appointment();
        }

        public Builder withAppointmentDate(LocalDate appointmentDate){
            appointment.setAppointmentDate(appointmentDate);
            return this;
        }
        public Builder withAppointmentTime(LocalTime appointmentTime){
            appointment.setAppointmentTime(appointmentTime);
            return this;
        }
        public Builder withPatient(Patient patient) {
            appointment.setPatient(patient);
            return this;
        }
        public Builder withDentist(Dentist dentist) {
            appointment.setDentist(dentist);
            return this;
        }
        public Builder withRegFeeStatus(Status regFeeStatus) {
            appointment.setRegFeeStatus(regFeeStatus);
            return this;
        }
        public Appointment build() {
            appointment.setAppointmentId(Utils.generateId());
            return appointment;
        }
    }

    // create appointment
    public static Appointment createAppointment(LocalDate appointmentDate, LocalTime appointmentTime, Patient patient, Dentist dentist, Status regFeeStatus){
        return new Appointment.Builder()
                .withAppointmentDate(appointmentDate)
                .withAppointmentTime(appointmentTime)
                .withPatient(patient)
                .withDentist(dentist)
                .withRegFeeStatus(regFeeStatus)
                .build();
    }
}
