package org.example.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.Period;

@AllArgsConstructor
@Data
public class ClothingItem {

    private String name;
    private String fabricType;
    private String productionCity;
    private LocalDate productionDate;
    private Integer price;


    public int getMonthsSinceProduction() {
        return Period.between(productionDate, LocalDate.now()).getMonths();
    }

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
