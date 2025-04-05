import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class GUIEventBookings extends JPanel {
    private JFrame mainFrame;

    public GUIEventBookings(JFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout());
        setBackground(new Color(224, 237, 255));

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
            case "Calendar":
                mainFrame.add(new GUICalendar(mainFrame));
                break;
            case "Event Bookings":
                mainFrame.add(new GUIEventBookings(mainFrame));
                break;
            /*case "Analytics":
                mainFrame.add(new GUIAnalytics(mainFrame));
                break;*/
        }
        mainFrame.revalidate();
        mainFrame.repaint();
    }

    private JPanel createContentPanel() {
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setBackground(new Color(224, 237, 255));
        contentPanel.add(createTopPanel(), BorderLayout.NORTH);
        contentPanel.add(createEventListPanel(), BorderLayout.CENTER);
        return contentPanel;
    }

    private JPanel createTopPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JButton createEventButton = new JButton("Create Event");
        createEventButton.setFont(new Font("Arial", Font.BOLD, 16));
        createEventButton.setBackground(new Color(50, 50, 150));
        createEventButton.setForeground(Color.WHITE);
        createEventButton.addActionListener(e -> openCreateEventWindow());

        panel.add(createEventButton, BorderLayout.CENTER);
        return panel;
    }

    private JPanel createEventListPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JTextField searchField = new JTextField(20);
        JButton searchButton = new JButton("Search");
        searchPanel.add(new JLabel("Search: "));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        String[] columnNames = {"Event Name", "Location", "Time/Date"};
        Object[][] data = {
                {"Group Booking", "[Room]", "5 PM, Today"},
                {"2001: A Space Odyssey", "Main Hall", "7 PM, April 15, 2025"}
        };
        JTable eventTable = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(eventTable);

        panel.add(searchPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        return panel;
    }

    private void openCreateEventWindow() {
        JFrame eventFrame = new JFrame("Create Event");
        eventFrame.setSize(400, 300);
        eventFrame.setLayout(new GridLayout(5, 2));

        JLabel nameLabel = new JLabel("Event Name:");
        JTextField nameField = new JTextField();

        JLabel typeLabel = new JLabel("Event Type:");
        String[] eventTypes = {"Film Screening", "Tour", "Group Booking", "Meeting"};
        JComboBox<String> typeDropdown = new JComboBox<>(eventTypes);

        JLabel dateLabel = new JLabel("Event Date:");
        JSpinner dateSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateSpinner, "yyyy-MM-dd");
        dateSpinner.setEditor(dateEditor);

        JLabel locationLabel = new JLabel("Location:");
        JTextField locationField = new JTextField();

        JButton createButton = new JButton("Create Event");

        createButton.addActionListener(e -> {
            String eventName = nameField.getText();
            String eventType = (String) typeDropdown.getSelectedItem();
            Date eventDate = (Date) dateSpinner.getValue();
            String eventLocation = locationField.getText();

            // Check if the date is within 3 weeks
            /*if (!isValidBookingDate(eventDate)) {
                JOptionPane.showMessageDialog(eventFrame, "Cannot book beyond 3 weeks.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }*/

            // Store event (you can connect this to a database instead)
            //events.add(new Event(eventName, eventType, eventDate, eventLocation));

            // Refresh the calendar view (Assuming GUI contains a refreshCalendar method)
            /*if (calendarView != null) {
                calendarView.refreshCalendar();
            }*/

            JOptionPane.showMessageDialog(eventFrame, "Event Created Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            eventFrame.dispose();
        });

        eventFrame.add(nameLabel);
        eventFrame.add(nameField);
        eventFrame.add(typeLabel);
        eventFrame.add(typeDropdown);
        eventFrame.add(dateLabel);
        eventFrame.add(dateSpinner);
        eventFrame.add(locationLabel);
        eventFrame.add(locationField);
        eventFrame.add(createButton);

        eventFrame.setVisible(true);

    }

    public static void createAndShowGUI() {
        JFrame frame = new JFrame("Event Bookings");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);

        GUIEventBookings eventBookings = new GUIEventBookings(frame);
        frame.add(eventBookings);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GUIEventBookings::createAndShowGUI);
    }
}
