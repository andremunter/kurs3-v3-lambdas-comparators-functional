package se.chasacademy.fjax26.sundsvall;

import java.time.LocalDate;

// =================================================================
// 2. DOMÄNMODELL FÖR BESTÄLLNINGAR (ORDER)
// =================================================================
public class Order {
    public enum Status { PENDING, PAID, SHIPPED, CANCELLED }

    private final String orderId;
    private final String customerName;
    private final double totalAmount;
    private final Status status;
    private final LocalDate orderDate;

    public Order(String orderId, String customerName, double totalAmount, Status status, LocalDate orderDate) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.totalAmount = totalAmount;
        this.status = status;
        this.orderDate = orderDate;
    }

    public String getOrderId() { return orderId; }
    public String getCustomerName() { return customerName; }
    public double getTotalAmount() { return totalAmount; }
    public Status getStatus() { return status; }
    public LocalDate getOrderDate() { return orderDate; }

    @Override
    public String toString() {
        return String.format("Order %-6s | Kund: %-12s | Belopp: %8.2f kr | Status: %-9s | Datum: %s",
                orderId, customerName, totalAmount, status, orderDate);
    }
}