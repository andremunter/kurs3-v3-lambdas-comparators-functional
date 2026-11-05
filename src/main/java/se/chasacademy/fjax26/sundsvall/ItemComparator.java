package se.chasacademy.fjax26.sundsvall;

import java.util.Comparator;

public class ItemComparator implements Comparator<Item> {

    private static final Comparator<Item> DELEGATE = Comparator
            .comparing(Item::getCategory, Comparator.nullsLast(Comparator.naturalOrder()))
            .thenComparing((i1, i2) -> Double.compare(i2.getPrice(), i1.getPrice()))
            .thenComparing(Item::getName, Comparator.nullsLast(Comparator.naturalOrder()));

    @Override
    public int compare(Item o1, Item o2) {
        return DELEGATE.compare(o1, o2);
    }

}
