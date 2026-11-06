package se.chasacademy.fjax26.sundsvall;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class OrderProcessor {

    public static List<String> processOrders(
            List<Order> orders,
            Predicate<Order> filterRule,
            Comparator<Order> sortRule,
            Function<Order, String> receiptFormatter
    ) {

        return orders.stream()
                .filter(filterRule)
                .sorted(sortRule)
                .map(receiptFormatter)
                .toList();

    }
}
