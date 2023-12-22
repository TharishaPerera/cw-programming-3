package com.tharishaperera.toothcare.requestTypes;

import com.tharishaperera.toothcare.config.enums.PaymentMethod;
import com.tharishaperera.toothcare.models.Invoice;

public class PaymentRequest {
    private Invoice invoice;
    private PaymentMethod paymentMethod;

    public Invoice getInvoice() {
        return invoice;
    }
    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }
    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }
    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
