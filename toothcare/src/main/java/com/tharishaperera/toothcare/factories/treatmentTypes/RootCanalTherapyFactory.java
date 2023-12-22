package com.tharishaperera.toothcare.factories.treatmentTypes;

import com.tharishaperera.toothcare.factories.TreatmentTypeFactory;
import com.tharishaperera.toothcare.models.TreatmentType;
import org.springframework.stereotype.Component;

@Component
public class RootCanalTherapyFactory implements TreatmentTypeFactory {
    @Override
    public TreatmentType createTreatmentType(String treatmentTypeName, double treatmentTypePrice) {
        return new TreatmentType(treatmentTypeName, treatmentTypePrice);
    }
}
