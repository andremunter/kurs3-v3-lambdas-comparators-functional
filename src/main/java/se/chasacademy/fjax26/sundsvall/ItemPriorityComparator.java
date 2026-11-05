package se.chasacademy.fjax26.sundsvall;

import java.util.Comparator;

public class ItemPriorityComparator implements Comparator<Item> {

    @Override
    public int compare(Item o1, Item o2) {
       return ItemComparators.ESHOP_PRIORITY.compare(o1, o2);
    }

}
