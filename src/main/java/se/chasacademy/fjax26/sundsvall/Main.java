package se.chasacademy.fjax26.sundsvall;

import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Main {
    static void main() {

        List<Item> items = SampleData.getItems();

        IO.println("""
              
              ======== ========== ========
              ======== Uppgift 1: ========
              ======== ========== ========
              """);
        IO.println("Items raw: ");
        items.forEach(IO::println);

        IO.println("""
              
                """);
        IO.println("Sorted by price, rising");
        items.sort(Comparator.comparingDouble(Item::getPrice));
        items.forEach(IO::println);

        IO.println("""
              
                """);
        IO.println("Sorted by price, descending");
        items.sort(Comparator.comparingDouble(Item::getPrice).reversed());
        items.forEach(IO::println);

        IO.println("""
              
                """);
        IO.println("Sorted by Name, alphabetically");
        items.sort(Comparator.comparing(Item::getName));
        items.forEach(IO::println);

        IO.println("""
              
                """);
        IO.println("Sorted by score, descending");
        items.sort(Comparator.comparingDouble(Item::getPrice).reversed());
        items.forEach(IO::println);

        IO.println("""
              
              ======== ========== ========
              ======== Uppgift 2: ========
              ======== ========== ========
              """);
        IO.println("""
                Sort by:
                1. Category(alphabetically) | 2. Price(descending) | 3. Name(alphabetically)
                """);

        Comparator<Item> sorted = Comparator
                .comparing(Item::getCategory, Comparator.nullsLast(Comparator.naturalOrder()))
                .thenComparing((i1, i2) -> Double.compare(i2.getPrice(), i1.getPrice()))
                .thenComparing(Item::getName, Comparator.nullsLast(Comparator.naturalOrder()));

        items.sort(sorted);
        items.forEach(IO::println);

        IO.println("""
              
              ======== ========== ========
              ======== Uppgift 3: ========
              ======== ========== ========
              """);
        IO.println("""
                Sort by:
                1. In Stock | 2. Score(descending) | 3. Price(ascending)
                """);
        items.sort(new ItemPriorityComparator());
        items.forEach(IO::println);

        IO.println("""
              
              ======== ========== ========
              ======== Uppgift 4: ========
              ======== ========== ========
              """);

        Predicate<Item> isAffordableAndInStock = item -> item.isInStock() && item.getPrice() < 1000;
        Function<Item, String> itemLabelFormatter = item -> String.format(
                "NIVÅ [%s] - %s (%.1f kr)",
                item.getCategory(), item.getName(), item.getPrice()
        );
        Consumer<Item> printAuditLog = item -> IO.println("[AUDIT] " + item.getName());
        Supplier<Item> defaultItemSupplier = () -> new Item("DEFAULT", "Standardverktyg", "Allmänt", 0.0, true, 5.0);

        IO.println("\n[isAffordableAndInStock]\n");
        items.forEach(item -> IO.println(isAffordableAndInStock.test(item) ? (item.getName() + " is in stock") : (item.getName() + " is NOT in stock")));

        IO.println("\n[itemLabelFormatter]\n");
        items.forEach(item -> IO.println(itemLabelFormatter.apply(item)));

        IO.println("\n[printAuditLog]\n");
        items.forEach(printAuditLog);

        IO.println("\n[defaultItem]\n");
        Item defaultItem = defaultItemSupplier.get();
        IO.println(defaultItem);

        IO.println("\n[combinedPredicate]\n");
        Predicate<Item> hasHighRating = item -> item.getRating() > 4.5;
        Predicate<Item> premiumBudgetChoice = isAffordableAndInStock.and(hasHighRating);
        items.stream()
                .filter(premiumBudgetChoice)
                .forEach(item -> {
                    IO.println("Matches: " + itemLabelFormatter.apply(item) + " | Betyg: " + item.getRating());
                    printAuditLog.accept(item);
                });

        IO.println("""
              
              ======== ========== ========
              ======== Uppgift 5: ========
              ======== ========== ========
              """);

        DiscountPolicy<Item> volumeDiscount = (item, price) -> item.getPrice() > 1000 ? price * 0.9 : price;
        DiscountPolicy<Item> gardenDiscount = new CategoryDiscountPolicy();
        DiscountPolicy<Item> combinedDiscount = volumeDiscount.combine(gardenDiscount);

        items.forEach(item -> IO.println(String.format("""
                        %s | Discounted Price: %.2f""",
                item, combinedDiscount.applyDiscount(item, item.getPrice()))
        ));

        DiscountPolicy<Item> toolDiscount = new CategoryDiscountPolicy("Verktyg", 0.50);
        items.forEach(item -> IO.println(String.format("""
                        %s | Discounted Price: %.2f""",
                item, toolDiscount.applyDiscount(item, item.getPrice()))
        ));

        IO.println("""
              
              ======== ========== ========
              ======== Uppgift 5: ========
              ======== ========== ========
              """);

        IO.println("""
              
              ======== ========== ========
              ======== Uppgift 5: ========
              ======== ========== ========
              """);

        Function<Item, String> nameToUpper = item -> new Item(
                item.getId(),
                item.getName().toUpperCase(),
                item.getCategory(),
                item.getPrice(),
                item.isInStock(),
                item.getRating()
        ).toString();
        Consumer<String> printItem = IO::println;
        DataPipeline<Item, String> pipeline = new DataPipeline<>();
        pipeline
                .filter(Item::isInStock)
                .map(nameToUpper)
                .execute(items, printItem);

    }
}
