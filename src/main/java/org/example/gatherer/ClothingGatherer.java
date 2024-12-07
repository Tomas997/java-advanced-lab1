package org.example.gatherer;

import lombok.AllArgsConstructor;
import org.example.domain.ClothingItem;

import java.util.Optional;
import java.util.stream.Gatherer;

/**
 * The type ClothingGatherer.
 *
 * A gatherer implementation that filters out clothing items based on a specific city
 * (skipCity) and a limit (skipCount). The gatherer skips the first 'skipCount' items
 * that have the production city matching the specified skipCity.
 */
@AllArgsConstructor
public class ClothingGatherer implements Gatherer<ClothingItem, Optional<ClothingItem>, ClothingItem> {

    // The city to skip during gathering
    private final String skipCity;

    // The number of items to skip with the specified skipCity
    private int skipCount;

    /**
     * Provides the integrator to process elements during the gathering.
     * The integrator checks whether the current element should be skipped (if it belongs
     * to the specified skipCity) and pushes the item downstream only if it is not skipped.
     *
     * @return the integrator for processing ClothingItem elements
     */
    @Override
    public Integrator<Optional<ClothingItem>, ClothingItem, ClothingItem> integrator() {
        return Gatherer.Integrator.of((_, element, downstream) -> {
            // Check if the element's production city matches the skipCity and if there are remaining items to skip
            if (element.getProductionCity().equals(skipCity) && skipCount > 0) {
                skipCount--;  // Decrease the skip count for each skipped item
                return true;  // Skip this item
            }
            // Otherwise, pass the item downstream
            return downstream.push(element);
        });
    }
}
