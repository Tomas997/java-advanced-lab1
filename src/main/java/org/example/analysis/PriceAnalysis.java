package org.example.analysis;

import org.example.domain.ClothingItem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * The class performs price analysis on a list of ClothingItem objects.
 * It calculates the number of valid items and outliers based on the Interquartile Range (IQR) method.
 */
public class PriceAnalysis {

    /**
     * Analyzes the prices of the given clothing items and returns a map containing
     * the count of valid items (within the IQR range) and the count of outliers.
     *
     * The outlier detection is based on the Interquartile Range (IQR) method,
     * where values outside the range [Q1 - 1.5 * IQR, Q3 + 1.5 * IQR] are considered outliers.
     *
     * @param clothingItems the list of ClothingItem objects to analyze
     * @return a map with two entries:
     *         - "data": the count of clothing items with prices within the valid range
     *         - "outliers": the count of clothing items with prices considered outliers
     */
    public static Map<String, Long> analyzePrices(List<ClothingItem> clothingItems) {
        List<Integer> prices = clothingItems.stream()
                .map(ClothingItem::getPrice)
                .sorted()
                .toList();

        double Q1 = prices.get((int) Math.ceil(0.25 * prices.size()) - 1);
        double Q3 = prices.get((int) Math.ceil(0.75 * prices.size()) - 1);

        double IQR = Q3 - Q1;

        Map<Boolean, Long> result = clothingItems.stream()
                .collect(Collectors.partitioningBy(
                        item -> item.getPrice() >= Q1 - 1.5 * IQR && item.getPrice() <= Q3 + 1.5 * IQR,
                        Collectors.counting()
                ));

        Map<String, Long> finalResult = new HashMap<>();
        finalResult.put("data", result.get(true));
        finalResult.put("outliers", result.get(false));

        return finalResult;
    }
}
