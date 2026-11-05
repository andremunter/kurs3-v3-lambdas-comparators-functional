package se.chasacademy.fjax26.sundsvall;

// =================================================================
// 1. DOMÄNMODELL FÖR ARTIKLAR / UTRUSTNING
// =================================================================
public class Item {
    private final String id;
    private final String name;
    private final String category;
    private final double price;
    private final boolean inStock;
    private final double rating; // 1.0 till 5.0

    public Item(String id, String name, String category, double price, boolean inStock, double rating) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.inStock = inStock;
        this.rating = rating;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public double getPrice() { return price; }
    public boolean isInStock() { return inStock; }
    public double getRating() { return rating; }

    @Override
    public String toString() {
        return String.format("%-6s | %-16s | %-12s | %7.2f kr | %-11s | Betyg: %.1f",
                id, name, category, price, inStock ? "I lager" : "Slut i lager", rating);
    }
}