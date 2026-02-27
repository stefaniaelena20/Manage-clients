package presentation;

import model.Client;
import model.Product;

import javax.swing.table.DefaultTableModel;
import java.util.List;

/**
 * Utility class for converting model lists into table models.
 */
public class TableUtility {

    public static DefaultTableModel createTable(List<Client> clients) {
        String[] columns = {"ID", "Name", "Email", "Address"};
        Object[][] data = new Object[clients.size()][4];

        for (int i = 0; i < clients.size(); i++) {
            Client c = clients.get(i);
            data[i][0] = c.getId();
            data[i][1] = c.getName();
            data[i][2] = c.getEmail();
            data[i][3] = c.getAddress();
        }

        return new DefaultTableModel(data, columns);
    }

    public static DefaultTableModel createTableProducts(List<Product> products) {
        String[] columns = {"ID", "Name", "Price", "Stock"};
        Object[][] data = new Object[products.size()][4];

        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
            data[i][0] = p.getId();
            data[i][1] = p.getName();
            data[i][2] = p.getPrice();
            data[i][3] = p.getStock();
        }

        return new DefaultTableModel(data, columns);
    }
}
