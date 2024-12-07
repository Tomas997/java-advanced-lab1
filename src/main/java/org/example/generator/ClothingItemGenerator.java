package org.example.generator;

import org.example.domain.ClothingItem;

import java.time.LocalDate;
import java.util.Random;
import java.util.stream.Stream;

public class ClothingItemGenerator {
    private static int numberOfItems=500;

    private static final String[] NAMES = {"Футболка", "Светр", "Штани", "Куртка", "Шорти"};
    private static final String[] FABRIC_TYPES = {"Бавовна", "Поліестер", "Шерсть", "Льон", "Нейлон"};
    private static final String[] CITIES = {"Київ", "Львів", "Харків", "Одеса", "Дніпро"};
    private static final Random RANDOM = new Random();

    public static void main(String[] args) {
        Stream<ClothingItem> clothingStream = Stream.generate(ClothingItemGenerator::generateRandomClothingItem);

        clothingStream.limit(numberOfItems).forEach(System.out::println);
    }

    private static ClothingItem generateRandomClothingItem() {
        String name = NAMES[RANDOM.nextInt(NAMES.length)];
        String fabricType = FABRIC_TYPES[RANDOM.nextInt(FABRIC_TYPES.length)];
        String productionCity = CITIES[RANDOM.nextInt(CITIES.length)];

        LocalDate productionDate = LocalDate.now().minusDays(RANDOM.nextInt(365 * 2));

        double price = 100 + (2000 - 100) * RANDOM.nextDouble();

        return new ClothingItem(name, fabricType, productionCity, productionDate, price);
    }
}