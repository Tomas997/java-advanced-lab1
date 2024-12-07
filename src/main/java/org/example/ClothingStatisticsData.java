package org.example;

import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class ClothingStatisticsData {
    private final int min;
    private final int max;
    private final double avg;
    private final double stdDeviation;
}
