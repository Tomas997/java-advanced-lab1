package org.example.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.Period;

import java.time.LocalDate;
import java.time.Period;

/**
 * Represents a clothing item with details such as name, fabric type, production city,
 * production date, and price. It also includes functionality to calculate the months
 * since the item was produced.
 */
@AllArgsConstructor
@Data
public class ClothingItem {

    private String name;
    private String fabricType;
    private String productionCity;
    private LocalDate productionDate;
    private Integer price;

    /**
     * Gets the number of months since the clothing item was produced.
     * This calculation is based on the difference between the current date
     * and the production date of the item.
     *
     * @return the number of months since production
     */
    public int getMonthsSinceProduction() {
        return Period.between(productionDate, LocalDate.now()).getMonths();
    }

    /**
     * Returns a string representation of the ClothingItem object, including all fields
     * and the calculated months since production.
     *
     * @return a string representation of the clothing item
     */
    @Override
    public String toString() {
        return "ClothingItem{" +
                "name='" + name + '\'' +
                ", fabricType='" + fabricType + '\'' +
                ", productionCity='" + productionCity + '\'' +
                ", productionDate=" + productionDate +
                ", price=" + price +
                ", monthsSinceProduction=" + getMonthsSinceProduction() +
                '}';
    }
}

