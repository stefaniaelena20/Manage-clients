package businessLayer;

import dataAccessLayer.ClientDAO;
import model.Client;
import java.util.List;

/**
 * Business Logic Layer for Client operations.
 */
public class ClientBLL {
    private final ClientDAO clientDAO;

    public ClientBLL() {
        this.clientDAO = new ClientDAO();
    }

    /**
     * Inserts a new client.
     * @param client The client to insert
     */
    public void insertClient(Client client) {
        clientDAO.insertClient(client);
    }


    /**
     * Retrieves all clients.
     * @return List of all clients
     */
    public List<Client> findAllClients() {
        return clientDAO.findAll();
    }
}
