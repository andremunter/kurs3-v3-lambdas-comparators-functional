package se.chasacademy.fjax26.sundsvall;

public class CategoryDiscountPolicy implements DiscountPolicy<Item> {

    private final String targetCategory;
    private final double discountRate;

    public CategoryDiscountPolicy(String targetCategory, double discountRate) {
        this.targetCategory = targetCategory;
        this.discountRate = discountRate;
    }

    public CategoryDiscountPolicy() {
        this("Trädgård", 0.20);
    }

    @Override
    public double applyDiscount(Item item, double currentPrice) {
        if (item.getCategory() != null && item.getCategory().equalsIgnoreCase(targetCategory)) {
            return currentPrice * (1.0 - discountRate);
        }
        return currentPrice;
    }
}
