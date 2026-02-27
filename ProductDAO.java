package dataAccessLayer;

import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Data Access Object specifically for Product entities.
 */
public class ProductDAO extends AbstractDAO<Product> {

    /**
     * Updates the stock of a product.
     * @param productId The ID of the product
     * @param newStock The new stock value
     * @return true if update was successful, false otherwise
     */
    public boolean updateStock(int productId, int newStock) {
        String query = "UPDATE product SET stock = ? WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, newStock);
            statement.setInt(2, productId);

            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateProduct(Product product) {
        String query = "UPDATE product SET price = ?, stock = ? WHERE name = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setDouble(1, product.getPrice());
            statement.setInt(2, product.getStock());
            statement.setString(3, product.getName());

            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void insertProduct(Product product) {
        String query = "INSERT INTO product (name, price, stock) VALUES (?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());
            statement.setInt(3, product.getStock());

            int affectedRows = statement.executeUpdate();
            if (affectedRows > 0) {
                var rs = statement.getGeneratedKeys();
                if (rs.next()) {
                    product.setId(rs.getInt(1));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
