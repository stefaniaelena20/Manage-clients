package presentation;

import businessLayer.ClientBLL;
import model.Client;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * GUI window for managing clients.
 */
public class ClientWindow extends JFrame {
    private JTable clientTable;
    private DefaultTableModel tableModel;
    private JTextField nameField;
    private JTextField emailField;
    private JTextField addressField;
    private final ClientBLL clientBLL;

    public ClientWindow() {
        clientBLL = new ClientBLL();
        initializeUI();
        loadClients();
    }

    private void initializeUI() {
        setTitle("Client Management");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        clientTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(clientTable);
        add(scrollPane, BorderLayout.CENTER);

        JPanel formPanel = new JPanel(new GridLayout(2, 4, 10, 10));
        nameField = new JTextField();
        emailField = new JTextField();
        addressField = new JTextField();

        formPanel.add(new JLabel("Name:"));
        formPanel.add(nameField);
        formPanel.add(new JLabel("Email:"));
        formPanel.add(emailField);
        formPanel.add(new JLabel("Address:"));
        formPanel.add(addressField);

        JButton addButton = new JButton("Add Client");
        formPanel.add(new JLabel()); // filler
        formPanel.add(addButton);

        add(formPanel, BorderLayout.SOUTH);

        addButton.addActionListener(e -> addClient());
    }

    private void loadClients() {
        List<Client> clients = clientBLL.findAllClients();
        tableModel = TableUtility.createTable(clients);
        clientTable.setModel(tableModel);
    }

    private void addClient() {
        String name = nameField.getText().trim();
        String email = emailField.getText().trim();
        String address = addressField.getText().trim();

        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Name is required.");
            return;
        }


        Client client = new Client();
        client.setName(name);
        client.setEmail(email);
        client.setAddress(address);

        clientBLL.insertClient(client);
        loadClients();
        clearFields();
    }

    private void clearFields() {
        nameField.setText("");
        emailField.setText("");
        addressField.setText("");
    }
}
