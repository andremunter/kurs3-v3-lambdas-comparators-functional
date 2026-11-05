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
        items.sort(new ItemComparator());
        items.forEach(IO::println);

        IO.println("""
              
              ======== ========== ========
              ======== Uppgift 3: ========
              ======== ========== ========
              
              """);

    }
}
