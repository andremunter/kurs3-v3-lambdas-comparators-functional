package se.chasacademy.fjax26.sundsvall;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FunctionalSearchEngineTest {

    private FunctionalSearchEngine engine;
    private List<Item> items;

    @BeforeEach
    void setUp() {
        engine = new FunctionalSearchEngine();
        items = SampleData.getItems();
    }

    @Test
    void shouldFilterInStockAndSortByPriceAscending() {
        Predicate<Item> inStock = Item::isInStock;
        Comparator<Item> byPrice = Comparator.comparingDouble(Item::getPrice);

        List<Item> result = engine.searchItems(items, inStock, byPrice);

        assertEquals(5, result.size());
        assertEquals("Slipmaskin", result.get(0).getName());      // 650 kr (cheapest in stock)
        assertEquals("Gräsklippare", result.get(4).getName());    // 3400 kr (most expensive in stock)
    }

    @Test
    void shouldFilterWithDynamicPredicatesAndSortByRatingDescending() {
        // Only tools in stock with pricetag under 1000 kr, sorted by score(descending)
        Predicate<Item> criteria = ((Predicate<Item>) Item::isInStock)
                .and(item -> "Verktyg".equalsIgnoreCase(item.getCategory()))
                .and(item -> item.getPrice() < 1000.0);

        Comparator<Item> byRatingDesc = (i1, i2) -> Double.compare(i2.getRating(), i1.getRating());

        List<Item> result = engine.searchItems(items, criteria, byRatingDesc);

        assertEquals(2, result.size());
        assertEquals("Skruvdragare", result.get(0).getName()); // Rating 4.6
        assertEquals("Slipmaskin", result.get(1).getName());   // Rating 3.9
    }

    @Test
    void shouldIntegrateWithEshopPriority() {
        // All items, sorted with e-shoprule: In Stock > Rating(descending), price(ascending)
        List<Item> result = engine.searchItems(items, item -> true, ItemComparators.ESHOP_PRIORITY);

        assertEquals(7, result.size());
        assertTrue(result.get(0).isInStock());
        assertEquals("Gräsklippare", result.get(0).getName()); // Rating 4.7 (highest among items in stock)
        assertEquals("Cirkelsåg", result.get(5).getName());    // Out of stock
        assertEquals("Vattenslang", result.get(6).getName());  // not in stock
    }
}