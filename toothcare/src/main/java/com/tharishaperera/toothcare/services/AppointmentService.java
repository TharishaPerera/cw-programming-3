package com.tharishaperera.toothcare.services;

import com.tharishaperera.toothcare.config.enums.Status;
import com.tharishaperera.toothcare.models.Appointment;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AppointmentService {
    // get all appointments
    public List<Appointment> getAllAppointments() {
        return Appointment.appointments;
    }

    // get appointment by id
    public Optional<Appointment> getAppointmentById(long appointmentId) {
        return Appointment.appointments.stream()
                .filter(appointment -> appointment.getAppointmentId() == appointmentId)
                .findFirst();
    }

    // get appointments by date
    public List<Appointment> getAppointmentsByDate(LocalDate date) {
        return Appointment.appointments.stream().filter(appointment -> appointment.getAppointmentDate().equals(date))
                .collect(Collectors.toList());
    }

    // get appointments by patient name
    public List<Appointment> getAppointmentsByPatientName(String name) {
        List<Appointment> appointments = new ArrayList<>();
        for (Appointment appointment: Appointment.appointments) {
            String patientName = appointment.getPatient().getFirstName() + " " + appointment.getPatient().getLastName();
            if (patientName.toLowerCase().contains(name.toLowerCase())) {
                appointments.add(appointment);
            }
        }
        if (appointments.isEmpty()) {
            return null;
        } else {
            return appointments;
        }
    }

    // create appointment
    public Appointment createAppointment(Appointment appointment) {
        Appointment.appointments.add(appointment);
        return appointment;
    }

    // update appointment by id
    public Optional<Appointment> updateAppointmentById(long appointmentId, Appointment updatedAppointment) {
        Optional<Appointment> appointmentToUpdate = Appointment.appointments.stream()
                .filter(appointment -> appointment.getAppointmentId() == appointmentId)
                .findFirst();

        appointmentToUpdate.ifPresent(appointment -> {
            appointment.setAppointmentDate(updatedAppointment.getAppointmentDate());
            appointment.setAppointmentTime(updatedAppointment.getAppointmentTime());
            appointment.setPatient(updatedAppointment.getPatient());
            appointment.setDentist(updatedAppointment.getDentist());
            appointment.setRegFeeStatus(updatedAppointment.getRegFeeStatus());
            appointment.setStatus(updatedAppointment.getStatus());
        });
        return appointmentToUpdate;
    }

    // update registration fee status
    public Optional<Appointment> updateRegistrationFee(Long id, Status updatedStatus) {
        Optional<Appointment> appointmentToUpdate = Appointment.appointments.stream()
                .filter(appointment -> appointment.getAppointmentId() == id)
                .findFirst();

        appointmentToUpdate.ifPresent(appointment -> {
            appointment.setRegFeeStatus(updatedStatus);
        });
        return appointmentToUpdate;
    }

    // update appointment status
    public Optional<Appointment> updateAppointmentStatus(Long id, Status updatedStatus) {
        Optional<Appointment> appointmentToUpdate = Appointment.appointments.stream()
                .filter(appointment -> appointment.getAppointmentId() == id)
                .findFirst();

        appointmentToUpdate.ifPresent(appointment -> {
            appointment.setStatus(updatedStatus);
        });
        return appointmentToUpdate;
    }

    // delete appointment by id
    public boolean deleteAppointmentById(long appointmentId) {
        return Appointment.appointments.removeIf(appointment -> appointment.getAppointmentId() == appointmentId);
    }
}
