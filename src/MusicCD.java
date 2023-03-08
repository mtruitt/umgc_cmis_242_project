/**
 * MusicCD class represents a type of media that is a music CD, which extends from Media class.
 * It includes a size property, which represents the size of the music CD in MB.
 */
public class MusicCD extends Media {
    private double size;

    /**
     * Constructor for creating a MusicCD object with specified title, year, size, and rental fee.
     * @param title The title of the music CD.
     * @param year The year the music CD was released.
     * @param size The size of the music CD in MB.
     * @param rentalFee The rental fee for the music CD.
     */
    public MusicCD(String title, int year, double size, double rentalFee) {
        super(title, year, rentalFee);
        this.size = size;
    }

    /**
     * Returns the size of the music CD in MB.
     * @return The size of the music CD in MB.
     */
    public double getSizeMb() {
        return size;
    }

    /**
     * Sets the size of the music CD in MB.
     * @param size The size of the music CD in MB.
     */
    public void setSizeMb(int size) {
        this.size = size;
    }

    /**
     * Calculates the rental fee for the music CD based on the number of days rented.
     * @param days The number of days the music CD is rented for.
     * @return The rental fee for the music CD.
     */
    @Override
    public double calculateRentalFee(int days) {
        return days * getRentalFee() * (1 + 0.2 * Math.min(3, Math.max(0, days - 2)));
    }

    /**
     * Returns a string representation of the MusicCD object.
     * @return A string representation of the MusicCD object.
     */
    @Override
    public String toString() {
        return super.toString() + "Size: " + size + " MB\n";
    }
}
