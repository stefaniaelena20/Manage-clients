package model;

import java.sql.Timestamp;

/**
 * Immutable record representing a bill for an order.
 */
public record Bill(
        int orderId,
        String clientName,
        String productName,
        int quantity,
        double totalPrice,
        Timestamp billDate
) {
    public Bill {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }
        if (totalPrice <= 0) {
            throw new IllegalArgumentException("Total price must be positive");
        }
        if (clientName == null || clientName.isBlank()) {
            throw new IllegalArgumentException("Client name cannot be empty");
        }
        if (productName == null || productName.isBlank()) {
            throw new IllegalArgumentException("Product name cannot be empty");
        }
    }
}
