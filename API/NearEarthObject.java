import java.util.Date;
/**
 * This class represents the NearEarthObject object, where a NearEarthObject
 * represents an asteroid from the NASA NeoW API. An asteroid's information
 * includes their referenceID, name, absolute magnitude, average diameter, if
 * the asteroid is dangerous or not, its closest approach date, its miss
 * distance, and its orbiting body.
 *
 * @author Alan George
 * ID: 114484206
 * Recitation #: 03
 */
public class NearEarthObject {
    // Data Fields (Member Variables)
    private int NEOreferenceID;
    private String NEOname;
    private double NEOabsoluteMagnitude;
    private double NEOaverageDiameter;
    private boolean NEOisDangerous;
    private Date NEOclosestApproachDate;
    private double NEOmissDistance;
    private String NEOorbitingBody;

    // Constructor
    /**
     * This is the constructor for the NearEarthObject class, where the
     * constructor takes in parameters for the id, name, magnitude, minimum and
     * maximum diameters, boolean value categorizing if the asteroid is
     * dangerous or not, closest approach date time stamp, its miss distance,
     * and its orbiting body. With these parameters, their corresponding values
     * are set for a new NearEarthObject object.
     */
    public NearEarthObject(int referenceID, String name, double absoluteMagnitude, double minDiameter, double maxDiameter, boolean isDangerous, long closestDateTimestamp, double missDistance, String orbitingBody) {
        NEOreferenceID = referenceID;
        NEOname = name;
        NEOabsoluteMagnitude = absoluteMagnitude;

        NEOaverageDiameter = ((minDiameter + maxDiameter) / 2);
        NEOisDangerous = isDangerous;
        NEOclosestApproachDate = new Date(closestDateTimestamp);

        NEOmissDistance = missDistance;
        NEOorbitingBody = orbitingBody;
    }

    // Getters
    /**
     * This is a method that returns the id of a NearEarthObject.
     *
     * @return
     * Returns the id of a NearEarthObject.
     */
    public int getNEOreferenceID() {
        return NEOreferenceID;
    }

    /**
     * This is a method that returns the name of a NearEarthObject.
     *
     * @return
     * Returns the name of a NearEarthObject.
     */
    public String getNEOname() {
        return NEOname;
    }

    /**
     * This is a method that returns the absolute magnitude of a
     * NearEarthObject.
     *
     * @return
     * Returns the absolute magnitude of a NearEarthObject.
     */
    public double getNEOabsoluteMagnitude() {
        return NEOabsoluteMagnitude;
    }

    /**
     * This is a method that returns the average diameter of a NearEarthObject.
     *
     * @return
     * Returns the average diameter of a NearEarthObject.
     */
    public double getNEOaverageDiameter() {
        return NEOaverageDiameter;
    }

    /**
     * This is a method that returns the boolean value categorizing if a
     * NearEarthObject is dangerous or not.
     *
     * @return
     * Returns the boolean categorization if a NearEarthObject is dangerous or
     * not.
     */
    public boolean getNEOisDangerous() {
        return NEOisDangerous;
    }

    /**
     * This is a method that returns the closest approach date of a
     * NearEarthObject.
     *
     * @return
     * Returns the closest approach date of a NearEarthObject.
     */
    public Date getNEOclosestApproachDate() {
        return NEOclosestApproachDate;
    }

    /**
     * This is a method that returns the miss distance of a NearEarthObject.
     *
     * @return
     * Returns the miss distance of a NearEarthObject.
     */
    public double getNEOmissDistance() {
        return NEOmissDistance;
    }

    /**
     * This is a method that returns the orbiting body of a NearEarthObject.
     *
     * @return
     * Returns the orbiting body of a NearEarthObject.
     */
    public String getNEOorbitingBody() {
        return NEOorbitingBody;
    }

    // Setters
    /**
     * This is a method that sets the id of a NearEarthObject.
     *
     * @param referenceID
     * Integer value that represents the new Id value for a NearEarthObject.
     */
    public void setNEOreferenceID(int referenceID) {
        this.NEOreferenceID = referenceID;
    }

    /**
     * This is a method that sets the name of a NearEarthObject.
     *
     * @param name
     * String value that represents the new name of a NearEarthObject.
     */
    public void setNEOname(String name) {
        this.NEOname = name;
    }

    /**
     * This is a method that sets the absolute magnitude of a NearEarthObject.
     *
     * @param absoluteMagnitude
     * Double value that represents the new absolute magnitude of a
     * NearEarthObject.
     */
    public void setNEOabsoluteMagnitude(double absoluteMagnitude) {
        this.NEOabsoluteMagnitude = absoluteMagnitude;
    }

    /**
     * This is a method that sets the average diameter of a NearEarthObject.
     *
     * @param averageDiameter
     * Double value that represents the new avergae diameter of a
     * NearEarthObject.
     */
    // Maybe the average diameter needs to be calculated here with a min and max
    // diameter
    public void setNEOaverageDiameter(double averageDiameter) {
        this.NEOaverageDiameter = averageDiameter;
    }

    /**
     * This is a method that sets the boolean value of whether a
     * NearEarthObject is dangerous or not.
     *
     * @param isDangerous
     * Boolean value that represents the new boolean value for a
     * NearEarthObject.
     */
    public void setNEOisDangerous(boolean isDangerous) {
        this.NEOisDangerous = isDangerous;
    }

    /**
     * This is a method that sets the closest approach date of a
     * NearEarthObject.
     *
     * @param closestApproachDate
     * Date value that represents the new closest approach date for a
     * NearEarthObject.
     */
    public void setNEOclosestApproachDate(Date closestApproachDate) {
        this.NEOclosestApproachDate = closestApproachDate;
    }

    /**
     * This is a method that sets the miss distance of a NearEarthObject.
     *
     * @param missDistance
     * Double value that represents the new miss distance for a
     * NearEarthObject.
     */
    public void setNEOmissDistance(double missDistance) {
        this.NEOmissDistance = missDistance;
    }

    /**
     * This is a method that sets the orbiting body of a NearEarthObject.
     *
     * @param orbitingBody
     * String value that represents the new orbiting body for a
     * NearEarthObject.
     */
    public void setNEOorbitingBody(String orbitingBody) {
        this.NEOorbitingBody = orbitingBody;
    }

    // Other Functions
/*//    @Override
    public String toString() {
        return NEOreferenceID + ", " + NEOname + ", " + NEOabsoluteMagnitude + ", "
                + NEOaverageDiameter + ", " + NEOisDangerous + ", " + NEOclosestApproachDate.toString()
                + ", " + NEOmissDistance + ", " + NEOorbitingBody;
    }*/
}
