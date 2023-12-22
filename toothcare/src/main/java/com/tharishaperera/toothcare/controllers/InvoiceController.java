package com.tharishaperera.toothcare.controllers;

import com.tharishaperera.toothcare.models.Invoice;
import com.tharishaperera.toothcare.models.Treatment;
import com.tharishaperera.toothcare.services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {
    @Autowired
    private InvoiceService invoiceService;

    // get all invoices
    @GetMapping
    public List<Invoice> getAllInvoices() {
        return invoiceService.getAllInvoices();
    }

    // get invoice by id
    @GetMapping("/{id}")
    public Optional<Invoice> getInvoiceById(@PathVariable Long id) {
        return invoiceService.getInvoiceById(id);
    }

    // get invoice by appointment id
    @GetMapping("/appointment/{id}")
    public Optional<Invoice> getInvoiceByAppointmentId(@PathVariable Long id) {
        return invoiceService.getInvoiceByAppointmentId(id);
    }

    // get invoice by patient id
    @GetMapping("/patient/{id}")
    public Optional<Invoice> getInvoiceByPatientId(@PathVariable Long id) {
        return invoiceService.getInvoiceByPatientId(id);
    }

    // create invoice
    @PostMapping
    public Invoice createInvoice(@RequestBody Treatment treatment) {
        Invoice newInvoice = Invoice.createInvoice(treatment);
        return invoiceService.createInvoice(newInvoice);
    }

    // delete invoice by id
    @DeleteMapping("/{id}")
    public boolean deleteInvoice(@PathVariable long id) {
        return invoiceService.deleteInvoiceById(id);
    }
}
