package org.example.generator;

import org.example.domain.ClothingItem;

import java.time.LocalDate;
import java.util.Random;
import java.util.stream.Stream;

/**
 * The type ClothingItemGenerator.
 * <p>
 * This class generates random clothing items with random names, fabric types, production cities, production dates,
 * and prices. The generated items can be streamed for further processing.
 */
public class ClothingItemGenerator {

    // Arrays containing sample values for names, fabric types, and cities
    private static final String[] NAMES = {"Футболка", "Светр", "Штани", "Куртка", "Шорти"};
    private static final String[] FABRIC_TYPES = {"Бавовна", "Поліестер", "Шерсть", "Льон", "Нейлон"};
    private static final String[] CITIES = {"Київ", "Львів", "Харків", "Одеса", "Дніпро"};

    // Random instance to generate random values
    private static final Random RANDOM = new Random();

    /**
     * Generates a stream of random ClothingItem objects.
     * <p>
     * Each clothing item is generated with a random name, fabric type, production city, production date
     * (within the last 20 years), and price (randomly between 100 and 5100).
     *
     * @return a stream of random ClothingItem objects
     */
    public static Stream<ClothingItem> generateRandomClothingItem() {
        return Stream.generate(() -> new ClothingItem(
                NAMES[RANDOM.nextInt(NAMES.length)],
                FABRIC_TYPES[RANDOM.nextInt(FABRIC_TYPES.length)],
                CITIES[RANDOM.nextInt(CITIES.length)],
                LocalDate.now().minusMonths(RANDOM.nextInt(240)),
                (int) (100 + (5000 * RANDOM.nextDouble()))
        ));
    }
}
