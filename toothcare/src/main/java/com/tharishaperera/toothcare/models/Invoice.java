package com.tharishaperera.toothcare.models;

import com.tharishaperera.toothcare.utils.Utils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Invoice {
    public static final List<Invoice> invoices = new ArrayList<>();
    private Long invoiceId;
    private LocalDate dateIssued;
    private Treatment treatment;
    private Double totalAmount = 0.00;

    public Invoice(Long invoiceId, LocalDate dateIssued, Treatment treatment, Double totalAmount) {
        this.invoiceId = invoiceId;
        this.dateIssued = dateIssued;
        this.treatment = treatment;
        this.totalAmount = totalAmount;
    }
    public Long getInvoiceId() {
        return invoiceId;
    }
    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }
    public LocalDate getDateIssued() {
        return dateIssued;
    }
    public void setDateIssued(LocalDate dateIssued) {
        this.dateIssued = dateIssued;
    }
    public Treatment getTreatment() {
        return treatment;
    }
    public void setTreatment(Treatment treatment) {
        this.treatment = treatment;
    }
    public Double getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    // crate invoice
    public static Invoice createInvoice(Treatment treatment) {
        long invoiceId = Utils.generateId();
        LocalDate dateIssued = LocalDate.now();
        double total = calculateBillAmount(treatment.getTreatmentTypes());
        return new Invoice(invoiceId, dateIssued, treatment, total);
    }

    //calculate the total bill amount
    public static double calculateBillAmount(List<TreatmentType> treatmentTypes) {
        double registrationFee = 1000;
        double total = registrationFee;
        for (TreatmentType treatmentType: treatmentTypes) {
            total += treatmentType.getTreatmentTypePrice();
        }
        return total;
    }
}
