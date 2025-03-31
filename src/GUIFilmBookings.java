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

            /*if (item.equals("Dashboard")) {
                button.addActionListener(e -> {
                    mainFrame.getContentPane().removeAll();
                    mainFrame.add(new GUIDashboard()); // Assuming you have a DashboardPage class
                    mainFrame.revalidate();
                    mainFrame.repaint();
                });
            }*/
        }

        return sidebar;
    }
    private JPanel createContentPanel() {
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setBackground(new Color(224, 237, 255));

        JLabel title = new JLabel("Film Catalogue", JLabel.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 24));
        title.setForeground(new Color(20, 20, 80));

        // Table for Films
        String[] columns = {"ID", "Title", "Release", "Certificate", "Price", "Availability"};
        Object[][] data = {}; // Placeholder, replace with real data
        JTable filmTable = new JTable(data, columns);
        JScrollPane scrollPane = new JScrollPane(filmTable);

        // Search Bar
        JPanel searchPanel = new JPanel(new BorderLayout());
        JTextField searchField = new JTextField();
        JButton searchButton = new JButton("Search");
        searchPanel.add(searchField, BorderLayout.CENTER);
        searchPanel.add(searchButton, BorderLayout.EAST);

        // Booked Films Section
        JPanel bookedFilmsPanel = new JPanel();
        bookedFilmsPanel.setLayout(new BorderLayout());
        bookedFilmsPanel.setBackground(Color.WHITE);
        JLabel bookedTitle = new JLabel("Booked Films", JLabel.CENTER);
        bookedTitle.setFont(new Font("SansSerif", Font.BOLD, 20));

        String[] bookedColumns = {"Title", "Hall", "Date"};
        Object[][] bookedData = {{"2001: A Space Odyssey", "Main Hall", "April 15, 2025"}};
        JTable bookedTable = new JTable(bookedData, bookedColumns);
        JScrollPane bookedScroll = new JScrollPane(bookedTable);

        bookedFilmsPanel.add(bookedTitle, BorderLayout.NORTH);
        bookedFilmsPanel.add(bookedScroll, BorderLayout.CENTER);

        // Adding components to content panel
        contentPanel.add(title, BorderLayout.NORTH);
        contentPanel.add(scrollPane, BorderLayout.CENTER);
        contentPanel.add(searchPanel, BorderLayout.SOUTH);
        //contentPanel.add(bookedFilmsPanel, BorderLayout.SOUTH);

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
