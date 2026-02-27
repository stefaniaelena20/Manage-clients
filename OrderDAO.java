package dataAccessLayer;

import model.Order;

import java.sql.*;

/**
 * Data Access Object specifically for Order entities.
 */
public class OrderDAO extends AbstractDAO<Order> {

    /**
     * Creates a new order and returns it with generated ID.
     * @param order The order to create
     * @return The created order with ID, or null if failed
     */
    public Order createOrder(Order order) {
        String query = "INSERT INTO orders (clientId, productId, quantity, orderDate) VALUES (?, ?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            statement.setInt(1, order.getClientId());
            statement.setInt(2, order.getProductId());
            statement.setInt(3, order.getQuantity());
            statement.setTimestamp(4, order.getOrderDate());

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating order failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    order.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating order failed, no ID obtained.");
                }
            }

            return order;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


}
