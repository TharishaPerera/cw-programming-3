package com.tharishaperera.toothcare.controllers;

import com.tharishaperera.toothcare.config.enums.Status;
import com.tharishaperera.toothcare.models.Appointment;
import com.tharishaperera.toothcare.requestTypes.AppointmentRequest;
import com.tharishaperera.toothcare.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/appointments")
@CrossOrigin(origins = "${frontend.allowed-origin}")
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;

    // get all appointments
    @GetMapping
    public List<Appointment> getAllAppointments() {
        return appointmentService.getAllAppointments();
    }

    // get appointment by id
    @GetMapping("/{id}")
    public Optional<Appointment> getAppointmentById(@PathVariable Long id) {
        return appointmentService.getAppointmentById(id);
    }

    // get appointment by date
    @GetMapping("/by-date")
    public List<Appointment> getAppointmentsByDate(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return appointmentService.getAppointmentsByDate(date);
    }

    // get appointment by patient name
    @GetMapping("/by-name")
    public List<Appointment> getAppointmentsByPatientName(@RequestParam String name) {
        return appointmentService.getAppointmentsByPatientName(name);
    }

    // create new appointment
    @PostMapping
    public Appointment createAppointment(@RequestBody AppointmentRequest appointment) {
        Appointment newAppointment = Appointment.createAppointment(appointment.getAppointmentDate(),appointment.getAppointmentTime(),appointment.getPatient(),appointment.getDentist(),appointment.getRegFeeStatus());
        return appointmentService.createAppointment(newAppointment);
    }

    // update appointment
    @PutMapping("/{id}")
    public Optional<Appointment> updateAppointment(@PathVariable Long id, @RequestBody Appointment appointment) {
        return appointmentService.updateAppointmentById(id, appointment);
    }

    // update registration fee status
    @PatchMapping("/registration-fee/{id}")
    public Optional<Appointment> updateRegistrationFee(@PathVariable Long id, @RequestParam String status) {
        return appointmentService.updateRegistrationFee(id, Status.valueOf(status));
    }

    // update appointment status
    @PatchMapping("/status/{id}")
    public Optional<Appointment> updateAppointmentStatus(@PathVariable Long id, @RequestParam String status) {
        return appointmentService.updateAppointmentStatus(id, Status.valueOf(status));
    }

    // delete appointment
    @DeleteMapping("/{id}")
    public boolean deleteAppointment(@PathVariable Long id) {
        return appointmentService.deleteAppointmentById(id);
    }
}
