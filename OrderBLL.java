package businessLayer;

import dataAccessLayer.ClientDAO;
import dataAccessLayer.LogDAO;
import dataAccessLayer.OrderDAO;
import dataAccessLayer.ProductDAO;
import model.Bill;
import model.Client;
import model.Order;
import model.Product;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

/**
 * Business Logic Layer for Order operations.
 */
public class OrderBLL {
    private final OrderDAO orderDAO;
    private final ProductDAO productDAO;
    private final ClientDAO clientDAO;
    private final LogDAO logDAO;

    public OrderBLL() {
        this.orderDAO = new OrderDAO();
        this.productDAO = new ProductDAO();
        this.clientDAO = new ClientDAO();
        this.logDAO = new LogDAO();
    }

    /**
     * Places an order with full validation, stock update, and bill generation.
     */
    public String placeOrder(int clientId, int productId, int quantity) {
        if (quantity <= 0) {
            return "Quantity must be greater than 0.";
        }

        Product product = productDAO.findById(productId);
        if (product == null) {
            return "Product not found.";
        }

        if (product.getStock() < quantity) {
            return "Not enough stock available.";
        }

        Order order = new Order(0, clientId, productId, quantity, Timestamp.from(Instant.now()));
        Order createdOrder = orderDAO.createOrder(order);

        if (createdOrder != null) {
            boolean stockUpdated = productDAO.updateStock(productId, product.getStock() - quantity);
            if (!stockUpdated) {
                return "Order created, but failed to update stock.";
            }

            generateBill(createdOrder);
            return "Order placed successfully.";
        } else {
            return "Failed to create order.";
        }
    }

    /**
     * Generates a bill for an order and logs it.
     */
    public Bill generateBill(Order order) {
        Product product = productDAO.findById(order.getProductId());
        Client client = clientDAO.findById(order.getClientId());

        if (product == null || client == null) {
            return null;
        }

        double totalPrice = product.getPrice() * order.getQuantity();
        Bill bill = new Bill(
                order.getId(),
                client.getName(),
                product.getName(),
                order.getQuantity(),
                totalPrice,
                Timestamp.from(Instant.now())
        );

        logDAO.insertBill(bill);
        return bill;
    }

    public List<Client> getAllClients() {
        return clientDAO.findAll();
    }

    public List<Product> getAllProducts() {
        return productDAO.findAll();
    }
}
