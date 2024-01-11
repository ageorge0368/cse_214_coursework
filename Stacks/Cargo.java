/**
 * This class represents a container that holds cargo that gets loaded onto a
 * ship. The cargo can either be FRAGILE, MODERATE, or STURDY in terms of
 * strength, and contain the name of the cargo, and weight.
 *
 * @author Alan George
 * ID: 114484206
 * Recitation #: 03
 */
public class Cargo {
    // Data Fields (Member Variables)
    private String name;
    private double weight;
    private CargoStrength strength;

    // Default Constructor
    /**
     * This is a constructor that creates a new Cargo object. It initializes
     * the name, weight, and strength of a piece of cargo as arguements into
     * the constructor.
     *
     * @param initName
     * String variable that represents the name of the cargo.
     *
     * @param initWeight
     * Double variable that represents the weight of the cargo.
     *
     * @param initStrength
     * Enum that represents the strength of the cargo.
     *
     * @throws IllegalArgumentException
     * Thrown if initName is equal to null, or if initWeight is less than or
     * equal to 0.
     */
    public Cargo(String initName, double initWeight, CargoStrength initStrength) throws IllegalArgumentException {
        if (initName == null) {
            throw new IllegalArgumentException();
        }
        if (initWeight <= 0) {
            throw new IllegalArgumentException();
        }

        this.name = initName;
        this.weight = initWeight;
        this.strength = initStrength;
    }

    // No-Arg Constructor
    /**
     * This is a no-argument constructor that creates a new Cargo object. It
     * initializes the name as "", the weight as 0.0, and the strength of a
     * piece of cargo as STURDY.
     */
    public Cargo () {
        this.name = "";
        this.weight = 0.0;
        this.strength = CargoStrength.STURDY;
    }

    // Getters
    /**
     * This method returns the name of a Cargo object.
     *
     * @return
     * The string representing the name of a Cargo object.
     */
    public String getName() {
        return name;
    }

    /**
     * This method returns the weight of a Cargo object.
     *
     * @return
     * The double representing the weight of a Cargo object.
     */
    public double getWeight() {
        return weight;
    }

    /**
     * This method returns the strength of a Cargo object.
     *
     * @return
     * The enum representing the strength of a Cargo object.
     */
    public CargoStrength getStrength() {
        return strength;
    }
}