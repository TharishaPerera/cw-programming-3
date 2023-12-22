package com.tharishaperera.toothcare.models;

import com.tharishaperera.toothcare.utils.Utils;

public class TreatmentType {
    private long id = Utils.generateId();;
    private String treatmentTypeName;
    private double treatmentTypePrice;

    public TreatmentType(String treatmentTypeName, double treatmentTypePrice) {
        this.treatmentTypeName = treatmentTypeName;
        this.treatmentTypePrice = treatmentTypePrice;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
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
