import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class GUICalendar extends JPanel{
    private static final String[] DAY_NAMES = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
    private static final String[] MONTH_NAMES = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

    private JComboBox<String> monthComboBox;
    private JLabel[][] dayLabel;
    private JLabel titleLabel;
    private JTextField dayField;
    private JTextField yearField;
    private LocalDate calendarDate;
    private JFrame mainFrame;

    public GUICalendar(JFrame mainFrame) {
        this.calendarDate = LocalDate.now();
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

    private JPanel createContentPanel() {
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setBackground(new Color(224, 237, 255));
        contentPanel.add(createTopPanel(), BorderLayout.NORTH);
        contentPanel.add(createCalendarPanel(), BorderLayout.CENTER);
        return contentPanel;
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

    private JPanel createTopPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(createDatePanel(), BorderLayout.NORTH);
        panel.add(createTitlePanel(), BorderLayout.SOUTH);
        return panel;
    }

    private JPanel createDatePanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JButton previousYearButton = new JButton("<<");
        previousYearButton.addActionListener(e -> updateYear(-1));
        panel.add(previousYearButton);

        JButton previousMonthButton = new JButton("<");
        previousMonthButton.addActionListener(e -> updateMonth(-1));
        panel.add(previousMonthButton);

        monthComboBox = new JComboBox<>(MONTH_NAMES);
        monthComboBox.setSelectedIndex(calendarDate.getMonthValue() - 1);
        panel.add(monthComboBox);

        dayField = new JTextField(2);
        dayField.setText(String.valueOf(calendarDate.getDayOfMonth()));
        panel.add(dayField);

        yearField = new JTextField(4);
        yearField.setText(String.valueOf(calendarDate.getYear()));
        panel.add(yearField);

        JButton createCalendarButton = new JButton("Create Calendar");
        createCalendarButton.addActionListener(e -> updateDayLabels());
        panel.add(createCalendarButton);

        JButton nextMonthButton = new JButton(">");
        nextMonthButton.addActionListener(e -> updateMonth(1));
        panel.add(nextMonthButton);

        JButton nextYearButton = new JButton(">>");
        nextYearButton.addActionListener(e -> updateYear(1));
        panel.add(nextYearButton);

        return panel;
    }

    private JPanel createTitlePanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titleLabel = new JLabel(" ");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(titleLabel);
        return panel;
    }

    private JPanel createCalendarPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        panel.add(createWeekdayLabels(), BorderLayout.NORTH);
        panel.add(createDayLabels(), BorderLayout.CENTER);
        updateDayLabels();

        return panel;
    }

    private JPanel createWeekdayLabels() {
        JPanel panel = new JPanel(new GridLayout(1, DAY_NAMES.length, 5, 5));
        for (String day : DAY_NAMES) {
            JLabel label = new JLabel(day, JLabel.CENTER);
            label.setFont(new Font("Arial", Font.BOLD, 12));
            panel.add(label);
        }
        return panel;
    }

    private JPanel createDayLabels() {
        JPanel panel = new JPanel(new GridLayout(6, 7, 5, 5));
        dayLabel = new JLabel[6][7];

        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 7; col++) {
                dayLabel[row][col] = new JLabel(" ", JLabel.CENTER);
                dayLabel[row][col].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
                panel.add(dayLabel[row][col]);
            }
        }
        return panel;
    }

    private void updateDayLabels() {
        int month = monthComboBox.getSelectedIndex() + 1;
        int year = parseInt(yearField.getText(), calendarDate.getYear());
        int day = parseInt(dayField.getText(), 1);

        calendarDate = LocalDate.of(year, month, Math.min(day, calendarDate.lengthOfMonth()));
        titleLabel.setText(MONTH_NAMES[month - 1] + " " + year);

        LocalDate firstDayOfMonth = LocalDate.of(year, month, 1);
        int startDay = firstDayOfMonth.getDayOfWeek().getValue() % 7;
        int daysInMonth = firstDayOfMonth.lengthOfMonth();

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                int dayNum = i * 7 + j - startDay + 1;
                if (dayNum > 0 && dayNum <= daysInMonth) {
                    dayLabel[i][j].setText(String.valueOf(dayNum));
                } else {
                    dayLabel[i][j].setText(" ");
                }
            }
        }
    }

    private void updateMonth(int delta) {
        int month = monthComboBox.getSelectedIndex() + delta;
        int year = parseInt(yearField.getText(), calendarDate.getYear());

        if (month < 0) {
            month = 11;
            year--;
        } else if (month > 11) {
            month = 0;
            year++;
        }

        monthComboBox.setSelectedIndex(month);
        yearField.setText(String.valueOf(year));
        updateDayLabels();
    }

    private void updateYear(int delta) {
        yearField.setText(String.valueOf(parseInt(yearField.getText(), calendarDate.getYear()) + delta));
        updateDayLabels();
    }

    private int parseInt(String text, int defaultValue) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Calendar");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);

        GUICalendar calendar = new GUICalendar(frame);
        frame.add(calendar);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GUICalendar::createAndShowGUI);
    }
}
