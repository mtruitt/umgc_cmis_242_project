/**
 * MovieDVD class represents a type of media that is a movie DVD, which extends from Media class.
 * It includes a size property, which represents the size of the movie DVD in MB.
 */
public class MovieDVD extends Media {
    private double size;

    /**
     * Constructor for creating a MovieDVD object with specified title, year, size, and rental fee.
     * @param title The title of the movie DVD.
     * @param year The year the movie DVD was released.
     * @param size The size of the movie DVD in MB.
     * @param rentalFee The rental fee for the movie DVD.
     */
    public MovieDVD(String title, int year, double size, double rentalFee) {
        super(title, year, rentalFee);
        this.size = size;
    }

    /**
     * Returns the size of the movie DVD in MB.
     * @return The size of the movie DVD in MB.
     */
    public double getSizeMb() {
        return size;
    }

    /**
     * Sets the size of the movie DVD in MB.
     * @param size The size of the movie DVD in MB.
     */
    public void setSizeMb(int size) {
        this.size = size;
    }

    /**
     * Calculates the rental fee for the movie DVD based on the number of days rented.
     * @param days The number of days the movie DVD is rented for.
     * @return The rental fee for the movie DVD.
     */
    @Override
    public double calculateRentalFee(int days) {
        return days * getRentalFee() * (1 + 0.1 * Math.min(2, Math.max(0, days - 3)));
    }

    /**
     * Returns a string representation of the MovieDVD object.
     * @return A string representation of the MovieDVD object.
     */
    @Override
    public String toString() {
        return super.toString() + "Size: " + size + " MB\n";
    }
}
