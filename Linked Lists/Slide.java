/**
 * This class represents a slide in a presentation, where a presentation has
 * a title, bullet points, and the duration of a slide in minutes.
 *
 * @author Alan George
 * ID: 114484206
 * Recitation #: 03
 */
public class Slide {
    // Static Variables
    /* A Slide object has a maximum of 5 bullet points in a team. */
    public static final int MAX_BULLETS = 5;

    // Data Fields (Member Variables)
    /* A Slide object has a title, their array of bullet points, and their
    * duration for a slide in minutes. */
    private String title;
    private String[] bullets;
    private double duration;

    // Default Constructor
    /**
     * This is a constructor that creates a new Slide object. It initializes
     * the array of bullet points to an array that is 5 elements long with 5
     * null strings. The title of a slide is also initialized to null, and the
     * duration of a slide is initialized to 0.0 minutes.
     */
    public Slide() {
        this.title = null;
        this.bullets = new String[MAX_BULLETS];
        for (int i = 0; i < MAX_BULLETS; i++) {
            this.bullets[i] = null;
        }
        this.duration = 0.0;
    }

    // Getters
    /**
     * This method returns the title of a Slide.
     *
     * @return
     * The string representing the title of a Slide.
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method returns the duration of a Slide.
     *
     * @return
     * The integer representing the duration of a Slide.
     */
    public double getDuration() {
        return duration;
    }

    /**
     * This method returns the number of valid bullet points in a Slide.
     *
     * @return
     * The integer representing the number of valid bullet points in a Slide.
     */
    public int getNumBullets() {
        int size = 0;

        while (size < this.bullets.length && this.bullets[size] != null) {
            size++;
        }
        return size;
    }

    /**
     * This method returns the actual bullet point given the index for the
     * bullet point array.
     *
     * @param i
     * The index used in the bullet point array for each slide to retrieve the
     * specific bullet point located at index i.
     *
     * @return
     * The string representing the bullet point located in index i.
     *
     * @throws IllegalArgumentException
     * Thrown when index i is not within the range of
     * 1 ≤ i ≤ MAX_BULLETS.
     */
    public String getBullet(int i) throws IllegalArgumentException{
        try {
            if (!((i >= 1 - 1) && (i <= MAX_BULLETS - 1))) {
                throw new IllegalArgumentException();
            }
            return this.bullets[i];
        } catch (IllegalArgumentException x) {
            System.out.println("Bullet is not in valid range! Try again!");
        }
        return null;                                // Is this extra? (Without it leads to an error!)
    }

    // Setters
    /**
     * This method sets the title of a Slide from a string that is taken as a
     * parameter.
     *
     * @param newTitle
     * The string that initializes a Slide's title.
     *
     * @throws IllegalArgumentException
     * Thrown when newTitle is a null String.
     */
    public void setTitle(String newTitle) {
        try {
            if (newTitle == null) {                            // or try "newTitle == null"
                throw new IllegalArgumentException();
            }
            this.title = newTitle;
        } catch (IllegalArgumentException x) {
            System.out.println("New title is not valid! Try again!");
        }
    }

    /**
     * This method sets the duration of a Slide from a double that is taken as
     * a parameter.
     *
     * @param newDuration
     * The double that initializes a Slide's duration.
     *
     * @throws IllegalArgumentException
     * Thrown when newDuration is less than or equal to 0.
     */
    public void setDuration(double newDuration) {
        try {
            if (newDuration <= 0) {
                throw new IllegalArgumentException();
            }
            this.duration = newDuration;
        } catch (IllegalArgumentException x) {
            System.out.println("New duration is not valid! Try again!");
        }
    }

    /**
     * This method sets a new bullet point to a Slide from a string as a
     * parameter for the new bullet point, and an int as a parameter for the
     * position of the new bullet point. This method also removes a bullet only
     * if the new bullet point string was null, and the position was within the
     * valid range of bullet points. (Aims to have no "holes" in the bullet
     * points of a Slide.)
     *
     * @param bullet
     * The string that initializes a Slide's bullet.
     *
     * @param i
     * The int that represents the position of the bullet point.
     *
     * @throws IllegalArgumentException
     * Thrown when i is not within the range of
     * 1 ≤ i ≤ MAX_BULLETS.
     */
    public void setBullet(String bullet, int i) {
//        try {
        if (!((i >= 1 - 1) && (i <= MAX_BULLETS - 1))) {
            throw new IllegalArgumentException();
        }

        // Moving all the bullet points back one index if bullet == null
        // (Removing a bullet point)
        if (bullet == null) {                              // or try "bullet == null"
            for (int j = (i - 1); j < MAX_BULLETS - 1; j++) {
                this.bullets[j] = this.bullets[j + 1];
            }
        }
        else {
            // Setting the bullet at position i
            this.bullets[i] = bullet;
        }

//        } catch (IllegalArgumentException x) {
//            System.out.println("Bullet is not in valid range! Try again!");
//        }
    }

    // Other functions
    /**
     * This method prints the title, duration, and number of bullets of a
     * slide.
     *
     * @return
     * Return a printable representation of a slide's title, duration, and
     * number of bullets as a String.
     */
    public String toString() {
//        return "jeff";
        return (this.getTitle() + "         " + this.getDuration() + "        " + this.getNumBullets());
    }
}