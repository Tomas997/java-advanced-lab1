package org.example.statistics;

import org.example.domain.ClothingItem;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collector;


public class ClothingStatisticsCollector implements Collector<ClothingItem, List<Integer>, ClothingStatisticsData> {
    @Override
    public Supplier<List<Integer>> supplier() {
        return ArrayList::new;
    }
    @Override
    public BiConsumer<List<Integer>, ClothingItem> accumulator() {
        return (accumulator, item) -> accumulator.add(item.getPrice());
    }
    @Override
    public BinaryOperator<List<Integer>> combiner() {
        return (list1, list2) -> {
            list1.addAll(list2);
            return list1;
        };
    }
    @Override
    public Function<List<Integer>, ClothingStatisticsData> finisher() {
        return prices -> {
            int min = prices.stream().min(Integer::compare).orElse(0);
            int max = prices.stream().max(Integer::compare).orElse(0);
            double avg = prices.stream().mapToDouble(Integer::doubleValue).average().orElse(0.0);
            double dev = Math.sqrt(prices.stream()
                    .mapToDouble(price -> Math.pow(price - avg, 2))
                    .average().orElse(0.0));
            return new ClothingStatisticsData(min, max, avg, dev);
        };
    }
    @Override
    public Set<Characteristics> characteristics() {
        Set<Characteristics> characteristics = new HashSet<>();
        characteristics.add(Characteristics.CONCURRENT);
        return characteristics;
    }
}