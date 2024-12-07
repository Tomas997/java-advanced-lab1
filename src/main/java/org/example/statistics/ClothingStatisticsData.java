package org.example.statistics;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * The type Clothing statistics data.
 * <p>
 * This class stores the statistical data for clothing items, including the minimum,
 * maximum, average price, and the standard deviation of the prices.
 */
@AllArgsConstructor
@ToString
@Data
public class ClothingStatisticsData {
    private final int min;
    private final int max;
    private final double avg;
    private final double stdDeviation;
}
