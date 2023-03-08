/**
 * A class that manages a list of Media objects. Provides methods to load media from a CSV file,
 * add media to the list, find media by title, rent media by ID, and write media to a CSV file.
 */
import java.io.*;
import java.util.*;

public class Manager {
    // Variable to store a list of Media objects
    private List<Media> mediaList;

    // Constructor that inits an empty medialist
    public Manager() {
        mediaList = new ArrayList<>();
    }

    // Getter
    public List<Media> getMediaList() {
        return mediaList;
    }
    // Setter
    public void setMediaList(List<Media> mediaList) {
        this.mediaList = mediaList;
    }

    // Load media from a CSV file
    public void loadMedia(File file) throws FileNotFoundException, InputMismatchException {
        // Check that the file type is CSV
        String filename = file.getName();
        String extension = filename.substring(filename.lastIndexOf(".") + 1);
        if (!extension.equalsIgnoreCase("csv")) {
            throw new IllegalArgumentException("Invalid file type. Please select a CSV file.");
        }

        // Use a Sanner to read from the CSV file
        Scanner scanner = new Scanner(file);
        scanner.useDelimiter(",|\n");

        // Loop through each line in the file
        while (scanner.hasNext()) {
            // Parse the line and create a new Media object based on the type
            String type = scanner.next().trim();
            String title = scanner.next().trim();
            int year;
            if (scanner.hasNextInt()) {
                year = scanner.nextInt();
            } else {
                scanner.next();
                year = 0;
            }
            double size = scanner.nextDouble();
            String rentalFeeString = scanner.next();
            double rentalFee;
            try {
                rentalFee = Double.parseDouble(rentalFeeString);
            } catch (NumberFormatException e) {
                rentalFee = Integer.parseInt(rentalFeeString);
            }

            // Create a new Media object based on the type
            switch (type.toLowerCase()) {
                case "ebook" -> {
                    int totalChapters = (int) size;
                    EBook ebook = new EBook(title, year, totalChapters, rentalFee);
                    addMedia(ebook);
                }
                case "moviedvd" -> {
                    MovieDVD moviedvd = new MovieDVD(title, year, size, rentalFee);
                    addMedia(moviedvd);
                }
                case "musiccd" -> {
                    MusicCD musiccd = new MusicCD(title, year, size, rentalFee);
                    addMedia(musiccd);
                }
                default -> throw new IllegalArgumentException("Invalid media type: " + type);
            }
        }
    }

    // Add a Media object to the mediaList
    public void addMedia(Media media) {
        mediaList.add(media);
    }

    // Find Media objects with titles containing a given string
    public List<Media> findMedia(String title) {
        List<Media> foundMedia = new ArrayList<>();

        for (Media media : mediaList) {
            if (media.getTitle().toLowerCase().contains(title)) {
                foundMedia.add(media);
            }
        }

        return foundMedia;
    }

    // Rent a Media object with the given ID
    public double rentMedia(int id) throws MediaNotFoundException, MediaUnavailableException {
        for (Media media : mediaList) {
            if (media.getId() == id) {
                if (media.isAvailable()) {
                    media.setAvailable(false);
                    return media.getRentalFee();
                } else {
                    throw new MediaUnavailableException("This media is not available for rental");
                }
            }
        }

        throw new MediaNotFoundException("Media with ID " + id + " not found");
    }

    // Write the mediaList to a CSV file
    public void writeMedia(File file) throws IOException {
        if (!file.getName().endsWith(".csv")) {
            throw new IllegalArgumentException("Invalid file type. Please select a CSV file.");
        }

        try (PrintWriter writer = new PrintWriter(file)) {
            writer.println("Type,Title,Year,Size,Rental Fee");

            // Loop through each Media object and write its data to a new line in the file
            for (Media media : mediaList) {
                String type = "";
                String title = media.getTitle();
                int year = media.getYear();
                double size;
                double rentalFee = media.getRentalFee();

                // Set the type and size values based on the type of Media object
                switch (media.getClass().getSimpleName()) {
                    case "MovieDVD" -> {
                        type = "MovieDVD";
                        size = ((MovieDVD) media).getSizeMb();
                    }
                    case "MusicCD" -> {
                        type = "MusicCD";
                        size = ((MusicCD) media).getSizeMb();
                    }
                    case "EBook" -> {
                        type = "EBook";
                        size = ((EBook) media).getTotalChapters();
                    }
                    default ->
                            throw new IllegalArgumentException("Invalid media type: " + media.getClass().getSimpleName());
                }

                // Use a PrintWriter to write to the CSV file
                writer.println(type + "," + title + "," + year + "," + size + "," + rentalFee);
            }
        }
    }
}