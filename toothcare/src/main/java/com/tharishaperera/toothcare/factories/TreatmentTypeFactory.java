package com.tharishaperera.toothcare.factories;

import com.tharishaperera.toothcare.models.TreatmentType;

public interface TreatmentTypeFactory {
    TreatmentType createTreatmentType(String treatmentTypeName, double treatmentTypePrice);
}
