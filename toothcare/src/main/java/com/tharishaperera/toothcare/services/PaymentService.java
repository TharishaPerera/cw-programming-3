package com.tharishaperera.toothcare.services;

import com.tharishaperera.toothcare.config.enums.Status;
import com.tharishaperera.toothcare.models.Payment;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {
    // get all payments
    public List<Payment> getAllPayments() {
        return Payment.payments;
    }

    // get payment by id
    public Optional<Payment> getPaymentById(long id) {
        return Payment.payments.stream()
                .filter(payment -> payment.getPaymentId() == id)
                .findFirst();
    }

    // get payment by appointment id
    public Optional<Payment> getPaymentByAppointmentId(long appointmentId) {
        return Payment.payments.stream()
                .filter(payment -> payment.getInvoice().getTreatment().getAppointment().getAppointmentId() == appointmentId)
                .findFirst();
    }

    // get payment by invoice id
    public Optional<Payment> getPaymentByInvoiceId(long invoiceId) {
        return Payment.payments.stream()
                .filter(payment -> payment.getInvoice().getInvoiceId() == invoiceId)
                .findFirst();
    }

    // create payment
    public Payment createPayment(Payment payment) {
        Payment.payments.add(payment);
        return payment;
    }

    // update payment status and date
    public void updatePayment(long id, Status paymentStatus) {
        Optional<Payment> paymentToUpdate = Payment.payments.stream()
                .filter(payment -> payment.getPaymentId() == id)
                .findFirst();

        paymentToUpdate.ifPresent(payment -> {
            payment.setDateTime(LocalDateTime.now());
            payment.setPaymentStatus(paymentStatus);
        });
    }
}
