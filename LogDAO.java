package dataAccessLayer;

import model.Bill;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Data Access Object for the Log table (immutable bills).
 */
public class LogDAO {
    private static final Logger LOGGER = Logger.getLogger(LogDAO.class.getName());

    /**
     * Inserts a bill into the Log table.
     * @param bill The bill to log
     * @return true if insertion was successful, false otherwise
     */
    public boolean insertBill(Bill bill) {
        String query = "INSERT INTO log (orderId, clientName, productName, quantity, totalPrice, billDate) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, bill.orderId());
            statement.setString(2, bill.clientName());
            statement.setString(3, bill.productName());
            statement.setInt(4, bill.quantity());
            statement.setDouble(5, bill.totalPrice());
            statement.setTimestamp(6, bill.billDate());

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            LOGGER.warning("Failed to insert bill: " + e.getMessage());
            return false;
        }
    }

    /**
     * Retrieves all bills from the log table.
     * @return list of all bills
     */
    public List<Bill> findAllBills() {
        List<Bill> bills = new ArrayList<>();
        String query = "SELECT * FROM log";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                Bill bill = new Bill(
                        rs.getInt("orderId"),
                        rs.getString("clientName"),
                        rs.getString("productName"),
                        rs.getInt("quantity"),
                        rs.getDouble("totalPrice"),
                        rs.getTimestamp("billDate")
                );
                bills.add(bill);
            }

        } catch (SQLException e) {
            LOGGER.warning("Failed to retrieve bills: " + e.getMessage());
        }

        return bills;
    }
}
