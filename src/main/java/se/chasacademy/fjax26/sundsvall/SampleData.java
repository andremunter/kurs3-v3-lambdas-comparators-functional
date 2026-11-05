package se.chasacademy.fjax26.sundsvall;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// =================================================================
// 3. FABRIK FÖR TESTDATA
// =================================================================
public class SampleData {

    public static List<Item> getItems() {
        List<Item> items = new ArrayList<>();
        items.add(new Item("I101", "Borrmaskin", "Verktyg", 1250.0, true, 4.5));
        items.add(new Item("I102", "Cirkelsåg", "Verktyg", 1800.0, false, 4.8));
        items.add(new Item("I103", "Häcksax", "Trädgård", 850.0, true, 4.1));
        items.add(new Item("I104", "Gräsklippare", "Trädgård", 3400.0, true, 4.7));
        items.add(new Item("I105", "Slipmaskin", "Verktyg", 650.0, true, 3.9));
        items.add(new Item("I106", "Vattenslang", "Trädgård", 250.0, false, 3.5));
        items.add(new Item("I107", "Skruvdragare", "Verktyg", 950.0, true, 4.6));
        return items;
    }

    public static List<Order> getOrders() {
        List<Order> orders = new ArrayList<>();
        orders.add(new Order("ORD-01", "Anna", 2400.0, Order.Status.PAID, LocalDate.now().minusDays(2)));
        orders.add(new Order("ORD-02", "Björn", 450.0, Order.Status.PENDING, LocalDate.now().minusDays(1)));
        orders.add(new Order("ORD-03", "Cecilia", 8900.0, Order.Status.PAID, LocalDate.now().minusDays(5)));
        orders.add(new Order("ORD-04", "David", 1200.0, Order.Status.SHIPPED, LocalDate.now().minusDays(10)));
        orders.add(new Order("ORD-05", "Erik", 3100.0, Order.Status.CANCELLED, LocalDate.now().minusDays(3)));
        orders.add(new Order("ORD-06", "Frida", 150.0, Order.Status.PAID, LocalDate.now()));
        return orders;
    }
}