package presentation;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Order Management System");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);
            frame.setLocationRelativeTo(null);
            frame.setLayout(null);

            JButton clientButton = new JButton("Manage Clients");
            clientButton.setBounds(100, 30, 200, 40);
            frame.add(clientButton);

            JButton productButton = new JButton("Manage Products");
            productButton.setBounds(100, 90, 200, 40);
            frame.add(productButton);

            JButton orderButton = new JButton("Manage Orders");
            orderButton.setBounds(100, 150, 200, 40);
            frame.add(orderButton);

            clientButton.addActionListener(e -> new ClientWindow().setVisible(true));
            productButton.addActionListener(e -> new ProductWindow().setVisible(true));
            orderButton.addActionListener(e -> new OrderWindow().setVisible(true));

            frame.setVisible(true);
        });
    }
}
