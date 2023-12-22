package com.tharishaperera.toothcare.factories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class TreatmentTypeFactoryProvider {
    private final Map<String, TreatmentTypeFactory> treatmentTypeFactories;

    @Autowired
    public TreatmentTypeFactoryProvider(List<TreatmentTypeFactory> treatmentTypeFactories) {
        this.treatmentTypeFactories = treatmentTypeFactories.stream()
                .collect(Collectors.toMap(factory -> factory.getClass().getSimpleName(), Function.identity()));
    }
    public TreatmentTypeFactory getFactory(String factoryName) {
        // get the treatment type factory with the class name
        Optional<TreatmentTypeFactory> result = treatmentTypeFactories.values().stream()
                .filter(value -> value != null && value.getClass().getName().contains(factoryName))
                .findFirst();

        return result.orElse(null);
    }
}
