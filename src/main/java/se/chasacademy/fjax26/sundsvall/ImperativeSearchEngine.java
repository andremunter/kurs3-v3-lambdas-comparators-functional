package se.chasacademy.fjax26.sundsvall;

import java.util.ArrayList;
import java.util.List;


public class ImperativeSearchEngine {

    public List<Item> searchItems(List<Item> items, String categoryFilter, Double maxPrice, Boolean onlyInStock) {

        List<Item> filteredItems = new ArrayList<>();
        for (Item item : items) {
            if (item.getCategory().toLowerCase().contains(categoryFilter.toLowerCase())) {
                if (item.getPrice() < maxPrice) {
                    if (!onlyInStock || item.isInStock()) {
                        filteredItems.add(item);
                    }
                }
            }
        }
        return filteredItems;
    }
}
