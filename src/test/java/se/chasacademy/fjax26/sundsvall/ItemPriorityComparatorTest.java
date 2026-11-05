package se.chasacademy.fjax26.sundsvall;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ItemPriorityComparatorTest {

    private ItemPriorityComparator comparator;

    @BeforeEach
    void setup() {
        comparator = new ItemPriorityComparator();
    }

    @Test
    void availableItemShouldSortBeforeUnavailableItem() {
        // Arrange
        Item inStockItem = new Item("1", "I lager", "Test", 500.0, true, 3.0);
        Item outOfStockItem = new Item("2", "Slut i lager", "Test", 200.0, false, 5.0);

        List<Item> items = new ArrayList<>(List.of(outOfStockItem, inStockItem));

        // Act
        items.sort(comparator);

        // Assert: Artikeln i lager ska komma först, oavsett betyg och pris
        assertEquals(inStockItem, items.get(0));
        assertEquals(outOfStockItem, items.get(1));
        assertTrue(comparator.compare(inStockItem, outOfStockItem) < 0);
    }

    @Test
    void itemsWithDifferentRatingsShouldSortByRatingDescending() {
        // Arrange: Båda i lager och samma pris, men olika betyg
        Item lowRatingItem = new Item("1", "Lågt betyg", "Test", 1000.0, true, 3.5);
        Item highRatingItem = new Item("2", "Högt betyg", "Test", 1000.0, true, 4.8);

        List<Item> items = new ArrayList<>(List.of(lowRatingItem, highRatingItem));

        // Act
        items.sort(comparator);

        // Assert: Högst betyg först
        assertEquals(highRatingItem, items.get(0));
        assertEquals(lowRatingItem, items.get(1));
        assertTrue(comparator.compare(highRatingItem, lowRatingItem) < 0);
    }

    @Test
    void itemsWithSameRatingShouldSortByPriceAscending() {
        // Arrange: Båda i lager och samma betyg, men olika pris
        Item expensiveItem = new Item("1", "Dyr", "Test", 1500.0, true, 4.5);
        Item cheapItem = new Item("2", "Billig", "Test", 450.0, true, 4.5);

        List<Item> items = new ArrayList<>(List.of(expensiveItem, cheapItem));

        // Act
        items.sort(comparator);

        // Assert: Lägst pris först
        assertEquals(cheapItem, items.get(0));
        assertEquals(expensiveItem, items.get(1));
        assertTrue(comparator.compare(cheapItem, expensiveItem) < 0);
    }


}
