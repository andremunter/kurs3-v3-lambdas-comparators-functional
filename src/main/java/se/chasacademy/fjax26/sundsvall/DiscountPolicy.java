package se.chasacademy.fjax26.sundsvall;

@FunctionalInterface
public interface DiscountPolicy<T> {
    double applyDiscount(T item, double currentPrice);

    default DiscountPolicy<T> combine(DiscountPolicy<T> after) {
        return (item, currentPrice) -> {
            double discountedPrice = this.applyDiscount(item, currentPrice);
            return after.applyDiscount(item, discountedPrice);
        };
    }
}
