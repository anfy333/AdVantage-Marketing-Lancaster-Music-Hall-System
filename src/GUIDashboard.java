
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIDashboard extends JPanel {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(GUIDashboard::createAndShowGUI); // Create an instance of GUI to invoke the constructor
    }
    private JFrame mainFrame; // Changed to JFrame for proper navigation handling

    public GUIDashboard(JFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout()); // Set layout to properly align elements
        setBackground(new Color(224, 237, 255)); // Light blue background

        JPanel sidebar = createSidebar();
        add(sidebar, BorderLayout.WEST);
    }
    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Dashboard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);

        GUIDashboard dashboard = new GUIDashboard(frame);
        frame.add(dashboard);

        frame.setVisible(true);
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
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setBackground(new Color(224, 237, 255));

        JLabel title = new JLabel("Dashboard", JLabel.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 24));
        title.setForeground(new Color(20, 20, 80));

        contentPanel.add(title, BorderLayout.NORTH);
        return contentPanel;
    }
}