/**
 * EBook class represents a type of media that is an eBook, which extends from Media class.
 * It includes a totalChapters property, which represents the total number of chapters in the eBook.
 */
public class EBook extends Media {
    private int totalChapters;

    /**
     * Constructor for creating an EBook object with specified title, year, total chapters, and rental fee.
     * @param title The title of the eBook.
     * @param year The year the eBook was released.
     * @param totalChapters The total number of chapters in the eBook.
     * @param rentalFee The rental fee for the eBook.
     */
    public EBook(String title, int year, int totalChapters, double rentalFee) {
        super(title, year, rentalFee);
        this.totalChapters = totalChapters;
    }

    /**
     * Returns the total number of chapters in the eBook.
     * @return The total number of chapters in the eBook.
     */
    public int getTotalChapters() {
        return totalChapters;
    }

    /**
     * Sets the total number of chapters in the eBook.
     * @param totalChapters The total number of chapters in the eBook.
     */
    public void setTotalChapters(int totalChapters) {
        this.totalChapters = totalChapters;
    }

    /**
     * Calculates the rental fee for the eBook based on the number of days rented.
     * @param days The number of days the eBook is rented for.
     * @return The rental fee for the eBook.
     */
    @Override
    public double calculateRentalFee(int days) {
        return days * getRentalFee() * (1 + 0.15 * Math.min(5, Math.max(0, days - 2)));
    }

    /**
     * Returns a string representation of the EBook object.
     * @return A string representation of the EBook object.
     */
    @Override
    public String toString() {
        return super.toString() + "Total Chapters: " + totalChapters + "\n";
    }
}
