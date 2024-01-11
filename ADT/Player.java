/**
 * This class represents a player in a team, where a player has a name,
 * number of hits, and the number of errors.
 *
 * @author Alan George
 * ID: 114484206
 * Recitation #: 03
 */
public class Player implements Cloneable {

    // Data Fields (Member Variables)
    /* A Player object has a name, their number of hits, and their number of
    * errors. */
    private String name;
    private int numHits, numErrors;

    // Default Constructor
    /**
     * This is a constructor that creates a new Player object. It initializes
     * the name of the player to a empty string and the number of hits and
     * number of errors to 0.
     */
    public Player () {
        this.name = "";
        this.numHits = 0;
        this.numErrors = 0;
    }

    // Getters
    /**
     * This method returns the name of a Player
     *
     * @return
     * The string representing the name of a Player
     */
    public String getName() {
        return name;
    }

    /**
     * This method returns the number of hits of a Player
     *
     * @return
     * The integer representing the number of hits of a Player
     */
    public int getNumHits() {
        return numHits;
    }

    /**
     * This method returns the number of errors of a Player
     *
     * @return
     * The integer representing the number of errors of a Player
     */
    public int getNumErrors() {
        return numErrors;
    }

    // Setters
    /**
     * This method sets the name of a Player from a string that is taken as a
     * parameter.
     *
     * @param name
     * The string that initializes a Player's name
     *
     * @return
     * Does not return anything, but initializes the name of a Player to the
     * string taken as a parameter to the function.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method sets the number of hits of a Player from an integer that is
     * taken as a parameter.
     *
     * @param numHits
     * The integer that initializes a Player's number of hits.
     *
     * @return
     * Does not return anything, but initializes the number of hits of a Player
     * to the integer taken as a parameter to the function.
     *
     * @throws IllegalArgumentException
     * Thrown when the number of hits is negative.
     */
    public void setNumHits(int numHits) throws IllegalArgumentException {
        try {
            if(numHits < 0) {
                throw new IllegalArgumentException();
            }
            this.numHits = numHits;
        }
        catch (Exception x) {
            System.out.println("Invalid Argument!");
        }
    }

    /**
     * This method sets the number of errors of a Player from an integer that
     * is taken as a parameter.
     *
     * @param numErrors
     * The integer that initializes a Player's number of errors.
     *
     * @return
     * Does not return anything, but initializes the number of errors of a
     * Player to the integer taken as a parameter to the function.
     *
     * @throws IllegalArgumentException
     * Thrown when the number of hits is negative.
     */
    public void setNumErrors(int numErrors) throws IllegalArgumentException{
        try {
            if (numErrors < 0) {
                throw new IllegalArgumentException();
            }
            this.numErrors = numErrors;
        }
        catch (Exception x) {
            System.out.println("Invalid Argument!");
        }
    }

    // Other Functions
    /**
     * This method sets the number of errors of a Player from an integer that
     * is taken as a parameter.
     *
     * @return
     * Return a printable representation of a Player's name, number of hits,
     * and number of errors as a String
     */
    public String toString() {
        return (this.name + " - " + this.numHits + " hits, " + this.numErrors
                + " errors\n");
    }
}