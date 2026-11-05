package se.chasacademy.fjax26.sundsvall;

import java.util.Comparator;

public class ItemComparators {

    public static final Comparator<Item> ESHOP_PRIORITY = Comparator
            .comparing(Item::isInStock, Comparator.reverseOrder())
            .thenComparing((i1, i2) -> Double.compare(i2.getRating(), i1.getRating()))
            .thenComparingDouble(Item::getPrice);

}
