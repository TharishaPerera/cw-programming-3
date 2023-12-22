package com.tharishaperera.toothcare.services;

import com.tharishaperera.toothcare.factories.TreatmentTypeFactory;
import com.tharishaperera.toothcare.factories.TreatmentTypeFactoryProvider;
import com.tharishaperera.toothcare.models.TreatmentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TreatmentTypeService {
    public static final List<TreatmentType> treatmentTypesList = new ArrayList<>();
    private final TreatmentTypeFactoryProvider factoryProvider;

    // initialize factory provider
    @Autowired
    public TreatmentTypeService(TreatmentTypeFactoryProvider factoryProvider) {
        this.factoryProvider = factoryProvider;
    }

    // get all treatment types
    public List<TreatmentType> getAllTreatmentTypes () {
        return treatmentTypesList;
    }

    // get treatment type by id
    public Optional<TreatmentType> getTreatmentTypeById(Long id) {
        return treatmentTypesList.stream()
                .filter(treatmentType -> treatmentType.getId() == id)
                .findFirst();
    }

    // create treatment type
    public TreatmentType createTreatmentType(String treatmentTypeName, double treatmentTypePrice) {
        TreatmentTypeFactory factory = factoryProvider.getFactory(treatmentTypeName + "Factory");
        if (factory != null) {
            TreatmentType newTreatment = factory.createTreatmentType(treatmentTypeName, treatmentTypePrice);
            treatmentTypesList.add(newTreatment);
            return newTreatment;
        } else {
            throw new IllegalArgumentException("Unsupported treatment type: " + treatmentTypeName);
        }
    }
}
