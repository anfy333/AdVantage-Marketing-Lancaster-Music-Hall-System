
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIDashboard {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(GUIDashboard::createAndShowGUI); // Create an instance of GUI to invoke the constructor
    }
    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Lancaster Music Hall Marketing System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 600);
        frame.setLayout(new BorderLayout());

        // Sidebar Panel
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new GridLayout(6, 1, 10, 10));
        sidebar.setBackground(new Color(204, 225, 255));

        String[] menuItems = {"Dashboard", "Film Bookings", "Calendar", "Event Bookings", "Analytics"};
        for (String item : menuItems) {
            JButton button = new JButton(item);
            button.setFont(new Font("Arial", Font.BOLD, 14));
            button.setBackground(new Color(50, 50, 150));
            button.setForeground(Color.WHITE);
            button.setFocusPainted(false);
            sidebar.add(button);
            if (item.equals("Film Bookings")) {
                button.addActionListener(e -> GUIFilmBookings.createAndShowGUI());
            }
        }



        // Main Content Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Dashboard", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(50, 50, 150));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Adding panels
        frame.add(sidebar, BorderLayout.WEST);
        frame.add(mainPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }
}