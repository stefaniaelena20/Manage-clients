package presentation;

import businessLayer.ProductBLL;
import model.Product;
import presentation.TableUtility;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * GUI window for managing products.
 */
public class ProductWindow extends JFrame {
    private JTable productTable;
    private DefaultTableModel tableModel;
    private JTextField nameField;
    private JTextField priceField;
    private JTextField stockField;
    private final ProductBLL productBLL;

    public ProductWindow() {
        productBLL = new ProductBLL();
        initializeUI();
        loadProducts();
    }

    private void initializeUI() {
        setTitle("Product Management");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        productTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(productTable);
        add(scrollPane, BorderLayout.CENTER);

        JPanel formPanel = new JPanel(new GridLayout(2, 4, 10, 10));
        nameField = new JTextField();
        priceField = new JTextField();
        stockField = new JTextField();

        formPanel.add(new JLabel("Name:"));
        formPanel.add(nameField);
        formPanel.add(new JLabel("Price:"));
        formPanel.add(priceField);
        formPanel.add(new JLabel("Stock:"));
        formPanel.add(stockField);

        JButton addButton = new JButton("Add Product");
        formPanel.add(new JLabel());
        formPanel.add(addButton);
        JButton updateButton = new JButton("Update Product");
        formPanel.add(new JLabel());
        formPanel.add(updateButton);

        add(formPanel, BorderLayout.SOUTH);

        addButton.addActionListener(e -> addProduct());
        updateButton.addActionListener(e -> updateProduct());
    }

    private void loadProducts() {
        List<Product> products = productBLL.findAll();
        tableModel = TableUtility.createTableProducts(products);
        productTable.setModel(tableModel);
    }

    private void addProduct() {
        String name = nameField.getText().trim();
        String priceText = priceField.getText().trim();
        String stockText = stockField.getText().trim();

        if (name.isEmpty() || priceText.isEmpty() || stockText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.");
            return;
        }

        if (stockText.contains("-"))
        {
            JOptionPane.showMessageDialog(this, "Stock is negative.");
            return;
        }

        try {
            double price = Double.parseDouble(priceText);
            int stock = Integer.parseInt(stockText);

            Product product = new Product(name, price, stock);
            productBLL.insertProduct(product);
            loadProducts();
            clearFields();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid numeric values for price or stock.");
        }
    }

    private void updateProduct() {
        String name = nameField.getText().trim();
        String priceText = priceField.getText().trim();
        String stockText = stockField.getText().trim();

        if (name.isEmpty() || priceText.isEmpty() || stockText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.");
            return;
        }

        if (stockText.contains("-"))
        {
            JOptionPane.showMessageDialog(this, "Stock is negative.");
            return;
        }

        try {
            double price = Double.parseDouble(priceText);
            int stock = Integer.parseInt(stockText);

            Product product = new Product(name, price, stock);
            productBLL.updateProduct(product);
            loadProducts();
            clearFields();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid numeric values for price or stock.");
        }
    }

    private void clearFields() {
        nameField.setText("");
        priceField.setText("");
        stockField.setText("");
    }
}
