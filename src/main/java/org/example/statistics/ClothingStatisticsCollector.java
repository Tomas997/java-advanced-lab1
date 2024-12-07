package org.example.statistics;

import org.example.domain.ClothingItem;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collector;


import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

/**
 * The type ClothingStatisticsCollector.
 * <p>
 * This collector calculates statistics (min, max, average, and standard deviation)
 * on a list of ClothingItem objects based on their price.
 */
public class ClothingStatisticsCollector implements Collector<ClothingItem, List<Integer>, ClothingStatisticsData> {

    /**
     * Provides a new empty list to hold prices of clothing items.
     *
     * @return an empty List<Integer>
     */
    @Override
    public Supplier<List<Integer>> supplier() {
        return ArrayList::new;
    }

    /**
     * Accumulates the price of each ClothingItem into the list.
     *
     * @return a BiConsumer that adds the price of a ClothingItem to the accumulator list
     */
    @Override
    public BiConsumer<List<Integer>, ClothingItem> accumulator() {
        return (accumulator, item) -> accumulator.add(item.getPrice());
    }

    /**
     * Combines two lists of prices (from different threads) into one list.
     *
     * @return a BinaryOperator that combines two lists of prices
     */
    @Override
    public BinaryOperator<List<Integer>> combiner() {
        return (list1, list2) -> {
            list1.addAll(list2);
            return list1;
        };
    }

    /**
     * Finishes the accumulation by calculating the min, max, average, and standard deviation of prices.
     *
     * @return a Function that computes statistics from the list of prices and returns a ClothingStatisticsData object
     */
    @Override
    public Function<List<Integer>, ClothingStatisticsData> finisher() {
        return prices -> {
            // Calculate min, max, average, and standard deviation
            int min = prices.stream().min(Integer::compare).orElse(0);
            int max = prices.stream().max(Integer::compare).orElse(0);
            double avg = prices.stream().mapToDouble(Integer::doubleValue).average().orElse(0.0);
            double dev = Math.sqrt(prices.stream()
                    .mapToDouble(price -> Math.pow(price - avg, 2))
                    .average().orElse(0.0));

            // Return the computed statistics encapsulated in a ClothingStatisticsData object
            return new ClothingStatisticsData(min, max, avg, dev);
        };
    }

    /**
     * Specifies the characteristics of the collector, indicating it is safe for concurrent use.
     *
     * @return a Set of Characteristics indicating the collector is CONCURRENT
     */
    @Override
    public Set<Characteristics> characteristics() {
        return Collections.singleton(Characteristics.CONCURRENT);
    }
}
