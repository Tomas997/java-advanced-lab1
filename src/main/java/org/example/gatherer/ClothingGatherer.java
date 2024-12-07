package org.example.gatherer;

import lombok.AllArgsConstructor;
import org.example.domain.ClothingItem;

import java.util.Optional;
import java.util.stream.Gatherer;

@AllArgsConstructor
class ClothingGatherer implements Gatherer<ClothingItem, Optional<ClothingItem>, ClothingItem> {

    private final String skipCity;
    private int skipCount;

    @Override
    public Integrator<Optional<ClothingItem>, ClothingItem, ClothingItem> integrator() {
        return Gatherer.Integrator.of((_, element, downstream) -> {
            if (element.getProductionCity().equals(skipCity) && skipCount > 0) {
                skipCount--;
                return true;
            }
            return downstream.push(element);
        });
    }
}