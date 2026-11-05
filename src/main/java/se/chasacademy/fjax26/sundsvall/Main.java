package se.chasacademy.fjax26.sundsvall;

import java.util.Comparator;
import java.util.List;

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

        // Komplex affärsregel för sortering i e-butiken:
        items.sort((a, b) -> {
            // 1. Artiklar i lager ska alltid visas före artiklar som är slut i lager
            if (a.isInStock() != b.isInStock()) {
                return a.isInStock() ? -1 : 1;
            }
            // 2. Högsta betyg först (rating >= 4.5 har prio)
            int ratingCompare = Double.compare(b.getRating(), a.getRating());
            if (ratingCompare != 0) {
                return ratingCompare;
            }
            // 3. Lägst pris först
            return Double.compare(a.getPrice(), b.getPrice());
        });

    }
}
