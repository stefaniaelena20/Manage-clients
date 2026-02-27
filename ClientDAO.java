package dataAccessLayer;

import model.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Data Access Object specifically for Client entities.
 */
public class ClientDAO extends AbstractDAO<Client> {

    public void insertClient(Client client) {
        String query = "INSERT INTO client (name, email, address) VALUES (?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, client.getName());
            statement.setString(2, client.getEmail());
            statement.setString(3, client.getAddress());

            int affectedRows = statement.executeUpdate();
            if (affectedRows > 0) {
                var rs = statement.getGeneratedKeys();
                if (rs.next()) {
                    client.setId(rs.getInt(1));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
