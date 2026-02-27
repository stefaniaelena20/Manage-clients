package presentation;

import businessLayer.OrderBLL;
import model.Client;
import model.Product;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class OrderWindow extends JFrame {
    private final JComboBox<Client> clientComboBox;
    private final JComboBox<Product> productComboBox;
    private final JTextField quantityField;
    private final JButton placeOrderButton;

    private final OrderBLL orderBLL;

    public OrderWindow() {
        super("Place Order");
        orderBLL = new OrderBLL();

        setSize(400, 300);
        setLayout(new GridLayout(5, 2, 10, 10));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        clientComboBox = new JComboBox<>();
        productComboBox = new JComboBox<>();
        quantityField = new JTextField();
        placeOrderButton = new JButton("Place Order");

        add(new JLabel("Select Client:"));
        add(clientComboBox);

        add(new JLabel("Select Product:"));
        add(productComboBox);

        add(new JLabel("Quantity:"));
        add(quantityField);

        add(new JLabel());
        add(placeOrderButton);

        loadClientsAndProducts();
        addListeners();
    }

    private void loadClientsAndProducts() {
        List<Client> clients = orderBLL.getAllClients();
        for (Client client : clients) {
            clientComboBox.addItem(client);
        }

        List<Product> products = orderBLL.getAllProducts();
        for (Product product : products) {
            productComboBox.addItem(product);
        }
    }

    private void addListeners() {
        placeOrderButton.addActionListener(e -> {
            Client selectedClient = (Client) clientComboBox.getSelectedItem();
            Product selectedProduct = (Product) productComboBox.getSelectedItem();
            int quantity;

            try {
                quantity = Integer.parseInt(quantityField.getText());
                if (quantity <= 0) throw new NumberFormatException();

                String result = orderBLL.placeOrder(selectedClient.getId(), selectedProduct.getId(), quantity);
                JOptionPane.showMessageDialog(this, result);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid quantity.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Order failed: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
