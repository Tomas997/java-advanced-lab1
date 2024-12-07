package org.example;

import org.example.domain.ClothingItem;
import org.example.gatherer.ClothingGatherer;
import org.example.generator.ClothingItemGenerator;

import java.util.List;


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
    }
}




