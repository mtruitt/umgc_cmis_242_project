/**
 * Mark Truitt
 * CMIS 242
 *
 */
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.List;

/**
 * MediaRentalSystem is a GUI class that implements a media rental system application.
 */
public class MediaRentalSystem {
    private final Manager manager;
    private JTextField findField;
    private JTextArea displayArea;
    private JTextField rentField;

    /**
     * Constructor for creating a MediaRentalSystem object.
     */
    public MediaRentalSystem() {
        this.manager = new Manager();
    }

    /**
     * The main method that creates and runs a MediaRentalSystem object.
     *
     * @param args Command-line arguments, which are not used in this program.
     */
    public static void main(String[] args) {
        MediaRentalSystem system = new MediaRentalSystem();
        system.run();
    }

    /**
     * The run method that creates and displays the GUI for the media rental system application.
     */
    public void run() {
        JFrame frame = new JFrame("Media Rental System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem loadItem = new JMenuItem("Load Media");
        JMenuItem exitItem = new JMenuItem("Exit");
        fileMenu.add(loadItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);
        menuBar.add(fileMenu);
        frame.setJMenuBar(menuBar);
        frame.setLocationRelativeTo(null); // center the frame on the monitor

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JPanel findPanel = new JPanel();
        findPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel findLabel = new JLabel("Find Media by Title:");
        findField = new JTextField(20);
        JButton findButton = new JButton("Find");
        findPanel.add(findLabel);
        findPanel.add(findField);
        findPanel.add(findButton);

        JPanel rentPanel = new JPanel();
        rentPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel rentLabel = new JLabel("Rent Media by ID:");
        rentField = new JTextField(10);
        JButton rentButton = new JButton("Rent");
        rentPanel.add(rentLabel);
        rentPanel.add(rentField);
        rentPanel.add(rentButton);

        displayArea = new JTextArea(20, 40);
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);

        mainPanel.add(findPanel);
        mainPanel.add(rentPanel);
        mainPanel.add(scrollPane);
        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);

        loadItem.addActionListener(new LoadListener());
        exitItem.addActionListener(new ExitListener());
        findButton.addActionListener(new FindListener());
        rentButton.addActionListener(new RentListener());

        frame.setSize(500, 500);
        frame.setVisible(true);
    }

    /**
     * A private class that implements the ActionListener interface to handle the "Load Media" menu item.
     */
    class LoadListener implements ActionListener {
        /**
         * A method that displays a file chooser dialog and loads media from a selected CSV file.
         *
         * @param event The action event that triggers the method.
         */
        public void actionPerformed(ActionEvent event) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new FileNameExtensionFilter("CSV files", "csv"));
            int result = fileChooser.showOpenDialog(null);

            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                String fileName = selectedFile.getName();
                String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
                if (!extension.equalsIgnoreCase("csv")) {
                    JOptionPane.showMessageDialog(null, "Invalid file type. Please select a CSV file.");
                    return;
                }
                try {
                    manager.loadMedia(selectedFile);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                displayArea.setText("Media loaded from file \"" + fileName + "\".");
            }
        }
    }

    /**
     * A static nested class that implements the ActionListener interface to handle the "Exit" menu item.
     */
    static class ExitListener implements ActionListener {
        /**
         * A method that terminates the application when the "Exit" menu item is clicked.
         * @param event The action event that triggers the method.
         */
        public void actionPerformed(ActionEvent event) {
            System.exit(0);
        }
    }

    /**
     * A private class that implements the ActionListener interface to handle the "Find" button.
     */
    class FindListener implements ActionListener {
        /**
         * A method that searches for media by title and displays the results in the display area.
         *
         * @param event The action event that triggers the method.
         */
        public void actionPerformed(ActionEvent event) {
            String title = findField.getText().toLowerCase();
            List<Media> foundMedia = manager.findMedia(title);

            if (!foundMedia.isEmpty()) {
                StringBuilder sb = new StringBuilder();
                for (Media media : foundMedia) {
                    sb.append("Type: ").append(media.getClass().getSimpleName()).append("\n");
                    sb.append("ID: ").append(media.getId()).append("\n");
                    sb.append("Title: ").append(media.getTitle()).append("\n");
                    sb.append("Year: ").append(media.getYear()).append("\n");
                    // Append the size (if the media is a DVD or CD) or the number of chapters (if the media is an EBook)
                    switch (media.getClass().getSimpleName()) {
                        case "MovieDVD" -> sb.append("Size: ").append(((MovieDVD) media).getSizeMb()).append(" MB\n");
                        case "MusicCD" -> sb.append("Size: ").append(((MusicCD) media).getSizeMb()).append(" MB\n");
                        case "EBook" -> sb.append("Chapters: ").append(((EBook) media).getTotalChapters()).append("\n");
                        default -> {
                        }
                    }
                    sb.append("Rental Fee: $").append(String.format("%.2f", media.getRentalFee())).append("\n");
                    sb.append("Status: ").append(media.isAvailable() ? "Available" : "Not Available").append("\n");
                    sb.append("\n");
                }
                displayArea.setText(sb.toString());

                // Set the display area to the top
                displayArea.setCaretPosition(0); // Set caret position to the beginning of the text area
            } else {
                displayArea.setText("No media found for title \"" + title + "\".");
            }
        }
    }

    /**
     * A private class that implements the ActionListener interface to handle the "Rent" button.
     */
    class RentListener implements ActionListener {
        /**
         * A method that rents a media by ID and displays the rental fee in the display area.
         *
         * @param event The action event that triggers the method.
         */
        public void actionPerformed(ActionEvent event) {
            String idString = rentField.getText();
            int id = Integer.parseInt(idString);

            try {
                double rentalFee = manager.rentMedia(id);
                displayArea.setText("Media with ID " + id + " has been rented.\nRental fee: $" + String.format("%.2f", rentalFee));
            } catch (MediaNotFoundException | MediaUnavailableException e) {
                displayArea.setText(e.getMessage());
            }
        }
    }
}