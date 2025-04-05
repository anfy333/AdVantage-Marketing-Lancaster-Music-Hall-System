import javax.swing.*;
import java.awt.*;

public class GUIFilmBookings extends JPanel {
    private JFrame mainFrame; // Changed to JFrame for proper navigation handling

    public GUIFilmBookings(JFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout());
        setBackground(new Color(224, 237, 255)); // Light blue background

        JPanel sidebar = createSidebar();
        add(sidebar, BorderLayout.WEST);
    }

    private JPanel createSidebar() {
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new GridLayout(6, 1, 10, 10));
        sidebar.setBackground(new Color(204, 225, 255));
        JPanel content = createContentPanel();
        add(content, BorderLayout.CENTER);

        String[] menuItems = {"Dashboard", "Film Bookings", "Calendar", "Event Bookings", "Analytics"};

        for (String item : menuItems) {
            JButton button = new JButton(item);
            button.setFont(new Font("Arial", Font.BOLD, 14));
            button.setBackground(new Color(50, 50, 150));
            button.setForeground(Color.WHITE);
            button.setFocusPainted(false);
            sidebar.add(button);

            button.addActionListener(e -> switchPage(item));
        }

        return sidebar;
    }
    private void switchPage(String page) {
        mainFrame.getContentPane().removeAll();
        switch (page) {
            case "Dashboard":
                mainFrame.add(new GUIDashboard(mainFrame));
                break;
            case "Film Bookings":
                mainFrame.add(new GUIFilmBookings(mainFrame));
                break;
            case "Event Bookings":
                mainFrame.add(new GUIEventBookings(mainFrame));
                break;
            /*case "Analytics":
                mainFrame.add(new GUIAnalytics(mainFrame));
                break;*/
            case "Calendar":
                mainFrame.add(new GUICalendar(mainFrame));
                break;
        }
        mainFrame.revalidate();
        mainFrame.repaint();
    }
    private JPanel createContentPanel() {
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(new Color(224, 237, 255));

        JLabel title = new JLabel("Film Catalogue", JLabel.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 24));
        title.setForeground(new Color(20, 20, 80));

        // ===== Film Table + Search (Top Half) =====
        String[] columns = {"ID", "Title", "Release", "Certificate", "Price", "Availability"};
        Object[][] data = {}; // Placeholder
        JTable filmTable = new JTable(data, columns);
        filmTable.setBackground(new Color(224, 237, 255));
        JScrollPane scrollPane = new JScrollPane(filmTable);

        JPanel searchPanel = new JPanel(new BorderLayout());
        JTextField searchField = new JTextField();
        JButton searchButton = new JButton("Search");
        searchPanel.add(searchField, BorderLayout.CENTER);
        searchPanel.add(searchButton, BorderLayout.EAST);

        JPanel tableContainer = new JPanel(new BorderLayout());
        tableContainer.setBackground(new Color(224, 237, 255));
        tableContainer.add(scrollPane, BorderLayout.CENTER);
        tableContainer.add(searchPanel, BorderLayout.SOUTH);

        // ===== Film List / Booked Films (Bottom Half) =====
        JPanel filmListPanel = new JPanel(new BorderLayout());
        filmListPanel.setBackground(new Color(224, 237, 255));
        JLabel listLabel = new JLabel("Booked Films List", JLabel.CENTER);
        listLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        listLabel.setForeground(new Color(20, 20, 80));
        filmListPanel.add(listLabel, BorderLayout.NORTH);

        // Optional: Add booked films table
        String[] bookedColumns = {"Title", "Hall", "Date"};
        Object[][] bookedData = {{"2001: A Space Odyssey", "Main Hall", "April 15, 2025"}};
        JTable bookedTable = new JTable(bookedData, bookedColumns);
        JScrollPane bookedScroll = new JScrollPane(bookedTable);
        filmListPanel.add(bookedScroll, BorderLayout.CENTER);

        // ===== Combine vertically =====
        JPanel verticalSplitPanel = new JPanel(new GridLayout(2, 1)); // 2 rows, 1 column
        verticalSplitPanel.add(tableContainer); // Top half
        verticalSplitPanel.add(filmListPanel);  // Bottom half

        // ===== Final Layout =====
        contentPanel.add(title, BorderLayout.NORTH);
        contentPanel.add(verticalSplitPanel, BorderLayout.CENTER);

        return contentPanel;
    }

    public static void createAndShowGUI() {
        JFrame frame = new JFrame("Film Bookings");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);

        GUIFilmBookings filmBookings = new GUIFilmBookings(frame);
        frame.add(filmBookings);

        frame.setVisible(true);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(GUIFilmBookings::createAndShowGUI);
    }
}
