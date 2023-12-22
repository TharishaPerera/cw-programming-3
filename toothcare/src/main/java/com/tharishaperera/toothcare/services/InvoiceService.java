package com.tharishaperera.toothcare.services;

import com.tharishaperera.toothcare.models.Invoice;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {
    // get all invoices
    public List<Invoice> getAllInvoices() {
        return Invoice.invoices;
    }

    // get invoice by id
    public Optional<Invoice> getInvoiceById(long invoiceId) {
        return Invoice.invoices.stream()
                .filter(invoice -> invoice.getInvoiceId() == invoiceId)
                .findFirst();
    }

    // get invoice by appointment id
    public Optional<Invoice> getInvoiceByAppointmentId(long appointmentId) {
        return Invoice.invoices.stream()
                .filter(invoice -> invoice.getTreatment().getAppointment().getAppointmentId() == appointmentId)
                .findFirst();
    }

    // get invoice by patient id
    public Optional<Invoice> getInvoiceByPatientId(long patientId) {
        return Invoice.invoices.stream()
                .filter(invoice -> invoice.getTreatment().getAppointment().getPatient().getUserId() == patientId)
                .findFirst();
    }

    // create invoice
    public Invoice createInvoice(Invoice invoice) {
        Invoice.invoices.add(invoice);
        return invoice;
    }

//    // update invoice by id
//    public void updateInvoiceById(long invoiceId, InvoiceRequest updatedTreatmentTypes) {
//        Optional<Invoice> invoiceToUpdate = Invoice.invoices.stream()
//                .filter(invoice -> invoice.getInvoiceId() == invoiceId)
//                .findFirst();
//
//        invoiceToUpdate.ifPresent(invoice -> {
//            invoice.setTreatment(updatedTreatmentTypes.getTreatment());
//            invoice.setTotalAmount(Invoice.calculateBillAmount(updatedTreatmentTypes.getTreatment().getTreatmentTypes()));
//        });
//    }

    // delete invoice by id
    public boolean deleteInvoiceById(long invoiceId) {
        return Invoice.invoices.removeIf(invoice -> invoice.getInvoiceId() == invoiceId);
    }
}
