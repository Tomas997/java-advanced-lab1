package org.example;

import org.example.analysis.PriceAnalysis;
import org.example.domain.ClothingItem;
import org.example.gatherer.ClothingGatherer;
import org.example.generator.ClothingItemGenerator;
import org.example.statistics.ClothingStatisticsCollector;
import org.example.statistics.ClothingStatisticsData;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * The type Main.
 */
public class Main {
    /**
     * The entry point of the application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        final int N = 50;
        String cityToSkip = "Київ";

        List<ClothingItem> clothingItems = ClothingItemGenerator.generateRandomClothingItem()
                .gather(new ClothingGatherer(cityToSkip, N))
                .limit(500)
                .toList();
        for (ClothingItem clothingItem : clothingItems) {
            System.out.println(clothingItem);
        }

        Map<String, List<ClothingItem>> groupedClothingItems = clothingItems.stream()
                .filter(item -> item.getProductionDate().isAfter(LocalDate.now().minusMonths(240)))
                .collect(Collectors.groupingBy(ClothingItem::getFabricType));
        for (Map.Entry<String, List<ClothingItem>> entry : groupedClothingItems.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue().size());
        }

        ClothingStatisticsData statistics = clothingItems.stream()
                .collect(new ClothingStatisticsCollector());
        System.out.println(statistics);

        Map<String, Long> priceAnalysis = PriceAnalysis.analyzePrices(clothingItems);
        System.out.println(priceAnalysis);
    }
}




