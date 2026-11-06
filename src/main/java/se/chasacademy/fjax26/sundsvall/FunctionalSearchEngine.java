package se.chasacademy.fjax26.sundsvall;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;


public class FunctionalSearchEngine {

    public List<Item> searchItems(List<Item> items, Predicate<Item> searchCriteria, Comparator<Item> sortCriteria) {
        return items.stream()
                .filter(searchCriteria)
                .sorted(sortCriteria)
                .toList();
    }

}
