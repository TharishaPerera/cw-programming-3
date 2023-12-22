package com.tharishaperera.toothcare.models;

import com.tharishaperera.toothcare.config.enums.Status;
import com.tharishaperera.toothcare.utils.Utils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

// singleton design pattern appointment class
public class Appointment {
    public static final List<Appointment> appointments = new ArrayList<>();
    private static Appointment instance;
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

    // public method to get the instance of the Appointment class
    public static Appointment getInstance() {
        if (instance == null) {
            instance = new Appointment();
        }
        return instance;
    }

    public static void setInstance(Appointment instance) {
        Appointment.instance = instance;
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

    // create appointment
    public static Appointment createAppointment(LocalDate appointmentDate, LocalTime appointmentTime, Patient patient, Dentist dentist, Status regFeeStatus){
        long appointmentId = Utils.generateId();
        Appointment appointment = Appointment.getInstance();
        appointment.setAppointmentId(appointmentId);
        appointment.setAppointmentDate(appointmentDate);
        appointment.setAppointmentTime(appointmentTime);
        appointment.setPatient(patient);
        appointment.setDentist(dentist);
        appointment.setRegFeeStatus(regFeeStatus);

        return appointment;
    }
}
