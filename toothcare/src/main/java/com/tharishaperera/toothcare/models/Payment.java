package com.tharishaperera.toothcare.models;

import com.tharishaperera.toothcare.config.enums.PaymentMethod;
import com.tharishaperera.toothcare.config.enums.Status;
import com.tharishaperera.toothcare.utils.Utils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Payment {
    public static final List<Payment> payments = new ArrayList<>();
    private Long paymentId;
    private Invoice invoice;
    private double amount;
    private LocalDateTime dateTime = LocalDateTime.now();
    private PaymentMethod paymentMethod;
    private Status paymentStatus = Status.PENDING;

    public Payment(Long paymentId, Invoice invoice, double amount, LocalDateTime dateTime, PaymentMethod paymentMethod, Status paymentStatus) {
        this.paymentId = paymentId;
        this.invoice = invoice;
        this.amount = amount;
        this.dateTime = dateTime;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
    }
    public Long getPaymentId() {
        return paymentId;
    }
    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }
    public Invoice getInvoice() {
        return invoice;
    }
    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public LocalDateTime getDateTime() {
        return dateTime;
    }
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }
    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
    public Status getPaymentStatus() {
        return paymentStatus;
    }
    public void setPaymentStatus(Status paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    // crate payment
    public static Payment createPayment(Invoice invoice, PaymentMethod paymentMethod) {
        long paymentId = Utils.generateId();
        double amount = invoice.getTotalAmount();
        LocalDateTime createdAt = LocalDateTime.now();
        Status paymentStatus = Status.PENDING;

        return new Payment(paymentId, invoice, amount, createdAt, paymentMethod, paymentStatus);
    }
}
