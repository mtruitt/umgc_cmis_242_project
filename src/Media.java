/**
 * Media class is an abstract class representing a generic type of media.
 * It includes instance variables for a media object, such as ID, title, year, rental fee, and availability.
 * It also includes abstract methods for calculating rental fee.
 */
public abstract class Media {
    // Declare and initialize a static integer variable to keep track of the next available ID
    private static int nextId = 1;

    // Declare instance variables for a media object
    private int id;
    private String title;
    private int year;
    private final double rentalFee;
    private boolean available;

    /**
     * Constructor for a media object with specified title, year, and rental fee.
     *
     * @param title     The title of the media object.
     * @param year      The year the media object was released.
     * @param rentalFee The rental fee for the media object.
     */
    public Media(String title, int year, double rentalFee) {
        // Set the media object's title, year, and rentalFee to the corresponding parameters
        this.title = title;
        this.year = year;
        this.rentalFee = rentalFee;

        // Set the media object's ID to the next available ID and increment the static variable
        this.id = getNextId();

        // Set the media object's available status to true
        this.available = true;
    }

    // Private method to get the next available ID
    private static int getNextId() {
        return nextId++;
    }

    /**
     * Returns the ID of the media object.
     *
     * @return The ID of the media object.
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the title of the media object.
     *
     * @return The title of the media object.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the media object.
     *
     * @param title The title of the media object.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Returns the year the media object was released.
     *
     * @return The year the media object was released.
     */
    public int getYear() {
        return year;
    }

    /**
     * Sets the year the media object was released.
     *
     * @param year The year the media object was released.
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * Returns the rental fee for the media object.
     *
     * @return The rental fee for the media object.
     */
    public double getRentalFee() {
        return rentalFee;
    }

    /**
     * Returns the availability of the media object.
     *
     * @return The availability of the media object.
     */
    public boolean isAvailable() {
        return available;
    }

    /**
     * Sets the availability of the media object.
     *
     * @param available The availability of the media object.
     */
    public void setAvailable(boolean available) {
        this.available = available;
    }

    /**
     * Abstract method to calculate rental fee for a media object.
     *
     * @param days The number of days the media object is rented for.
     * @return The rental fee for the media object.
     */
    public abstract double calculateRentalFee(int days);

    /**
     * Returns a string representation of the Media object.
     *
     * @return A string representation of the Media object.
     */
    @Override
    public String toString() {
        return "ID: " + id + "\nTitle: " + title + "\nYear: " + year + "\nRental Fee: $" + rentalFee + "\n";
    }
}

