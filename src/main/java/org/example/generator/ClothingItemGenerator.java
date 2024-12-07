package org.example.generator;

import org.example.domain.ClothingItem;

import java.time.LocalDate;
import java.util.Random;
import java.util.stream.Stream;

public class ClothingItemGenerator {

    private static final String[] NAMES = {"Футболка", "Светр", "Штани", "Куртка", "Шорти"};
    private static final String[] FABRIC_TYPES = {"Бавовна", "Поліестер", "Шерсть", "Льон", "Нейлон"};
    private static final String[] CITIES = {"Київ", "Львів", "Харків", "Одеса", "Дніпро"};
    private static final Random RANDOM = new Random();


    public static Stream<ClothingItem> generateRandomClothingItem() {
        return Stream.generate(() -> new ClothingItem(
                NAMES[RANDOM.nextInt(NAMES.length)],
                FABRIC_TYPES[RANDOM.nextInt(FABRIC_TYPES.length)],
                CITIES[RANDOM.nextInt(CITIES.length)],
                LocalDate.now().minusMonths(RANDOM.nextInt(240)),
                (100 + (5000 * RANDOM.nextInt()))
        ));
    }
}