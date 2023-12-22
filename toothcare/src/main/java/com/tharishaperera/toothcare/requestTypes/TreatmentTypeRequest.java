package com.tharishaperera.toothcare.requestTypes;

public class TreatmentTypeRequest {
    private String treatmentTypeName;
    private double treatmentTypePrice;

    public String getTreatmentTypeName() {
        return treatmentTypeName;
    }
    public void setTreatmentTypeName(String treatmentTypeName) {
        this.treatmentTypeName = treatmentTypeName;
    }
    public double getTreatmentTypePrice() {
        return treatmentTypePrice;
    }
    public void setTreatmentTypePrice(double treatmentTypePrice) {
        this.treatmentTypePrice = treatmentTypePrice;
    }
}
