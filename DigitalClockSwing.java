import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DigitalClockSwing extends JFrame {
    private JLabel clockLabel, dateLabel;
    private Timer timer;
    private JButton startButton, stopButton, themeButton;
    private boolean isRunning = true;
    private boolean isDarkMode = true;

    public DigitalClockSwing() {
        // Set up the frame properties
        setTitle("Digital Clock");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        setResizable(false);

        // Create a panel for the clock and date
        JPanel clockPanel = new JPanel(new GridLayout(2, 1));
        
        // Set up the clock label (time display)
        clockLabel = new JLabel("", SwingConstants.CENTER);
        clockLabel.setFont(new Font("Arial", Font.BOLD, 40));
        
        // Set up the date label (date display)
        dateLabel = new JLabel("", SwingConstants.CENTER);
        dateLabel.setFont(new Font("Arial", Font.PLAIN, 20));

        // Add labels to panel
        clockPanel.add(clockLabel);
        clockPanel.add(dateLabel);
        
        // Create a panel for buttons
        JPanel buttonPanel = new JPanel();
        startButton = new JButton("Start");
        stopButton = new JButton("Stop");
        themeButton = new JButton("Switch Theme");

        // Add buttons to the panel
        buttonPanel.add(startButton);
        buttonPanel.add(stopButton);
        buttonPanel.add(themeButton);
        
        // Set default theme
        updateTheme();

        // Add panels to the frame
        add(clockPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Create a Timer to update the clock every second
        timer = new Timer(1000, e -> updateClock());
        timer.start();
        updateClock(); // Initialize clock with the current time

        // Add Action Listeners for buttons
        startButton.addActionListener(this::startClock);
        stopButton.addActionListener(this::stopClock);
        themeButton.addActionListener(this::switchTheme);

        setVisible(true);
    }

    // Method to update the clock and date
    private void updateClock() {
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, dd MMMM yyyy");
        
        clockLabel.setText(timeFormat.format(new Date()));
        dateLabel.setText(dateFormat.format(new Date()));
    }

    // Start the clock
    private void startClock(ActionEvent e) {
        if (!isRunning) {
            timer.start();
            isRunning = true;
        }
    }

    // Stop the clock
    private void stopClock(ActionEvent e) {
        if (isRunning) {
            timer.stop();
            isRunning = false;
        }
    }

    // Switch between Dark and Light themes
    private void switchTheme(ActionEvent e) {
        isDarkMode = !isDarkMode;
        updateTheme();
    }

    // Apply the selected theme
    private void updateTheme() {
        if (isDarkMode) {
            getContentPane().setBackground(Color.BLACK);
            clockLabel.setForeground(Color.GREEN);
            dateLabel.setForeground(Color.GREEN);
        } else {
            getContentPane().setBackground(Color.WHITE);
            clockLabel.setForeground(Color.BLUE);
            dateLabel.setForeground(Color.BLUE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(DigitalClockSwing::new);
    }
}
