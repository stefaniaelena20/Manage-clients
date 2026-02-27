package model;

import java.sql.Timestamp;

/**
 * Represents an order in the warehouse management system.
 */
public class Order {
    private int id;
    private int clientId;
    private int productId;
    private int quantity;
    private Timestamp orderDate;

    public Order() {
    }

    public Order(int id, int clientId, int productId, int quantity, Timestamp orderDate) {
        this.id = id;
        this.clientId = clientId;
        this.productId = productId;
        this.quantity = quantity;
        this.orderDate = orderDate;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }
}
