package org.example;

import org.example.domain.ClothingItem;
import org.example.gatherer.ClothingGatherer;
import org.example.generator.ClothingItemGenerator;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class Main {
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
    }
}




