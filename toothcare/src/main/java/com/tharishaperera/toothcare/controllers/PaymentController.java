package com.tharishaperera.toothcare.controllers;

import com.tharishaperera.toothcare.config.enums.Status;
import com.tharishaperera.toothcare.models.Payment;
import com.tharishaperera.toothcare.requestTypes.PaymentRequest;
import com.tharishaperera.toothcare.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    // get all payments
    @GetMapping()
    public List<Payment> getAllPayments() {
        return paymentService.getAllPayments();
    }

    // get payment by id
    @GetMapping("/{id}")
    public Optional<Payment> getPaymentById(@PathVariable long id) {
        return paymentService.getPaymentById(id);
    }

    // get payment by appointment id
    @GetMapping("/appointment/{id}")
    public Optional<Payment> getPaymentByAppointmentId(@PathVariable long id) {
        return paymentService.getPaymentByAppointmentId(id);
    }

    // get payment by invoice id
    @GetMapping("/invoice/{id}")
    public Optional<Payment> getPaymentByInvoiceId(@PathVariable long id) {
        return paymentService.getPaymentByInvoiceId(id);
    }

    // create payment
    @PostMapping()
    public Payment createPayment(@RequestBody PaymentRequest payment) {
        Payment newPayment = Payment.createPayment(payment.getInvoice(), payment.getPaymentMethod());
        return paymentService.createPayment(newPayment);
    }

    // update payment
    @PutMapping("/{id}")
    public void updatePayment(@PathVariable long id, @RequestParam String paymentStatus) {
        System.out.println(paymentStatus);
        paymentService.updatePayment(id, Status.valueOf(paymentStatus));
    }
}
