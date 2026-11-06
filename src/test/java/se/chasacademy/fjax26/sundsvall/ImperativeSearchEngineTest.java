package se.chasacademy.fjax26.sundsvall;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ImperativeSearchEngineTest {

    private ImperativeSearchEngine engine;
    private List<Item> items;

    @BeforeEach
    void setUp() {
        engine = new ImperativeSearchEngine();
        items = SampleData.getItems();
    }

    @Test
    void shouldFindVerktygUnder1000InStock() {
        // Borrmaskin (1250kr, in stock), Cirkelståg (1800kr, not in stock), Slipmaskin (650kr, in stock), Skruvdragare (950kr, in stock)
        List<Item> result = engine.searchItems(items, "verktyg", 1000.0, true);

        assertEquals(2, result.size());
        assertTrue(result.stream().allMatch(i -> i.getPrice() < 1000.0 && i.isInStock()));
        assertEquals("Slipmaskin", result.get(0).getName());
        assertEquals("Skruvdragare", result.get(1).getName());
    }

    @Test
    void shouldFilterTradgardInStock() {
        // Häcksax (850, in stock), Gräsklippare (3400, in stock), Vattenslang (250, not in stock)
        List<Item> result = engine.searchItems(items, "trädgård", 5000.0, true);

        assertEquals(2, result.size());
        assertEquals("Häcksax", result.get(0).getName());
        assertEquals("Gräsklippare", result.get(1).getName());
    }
}