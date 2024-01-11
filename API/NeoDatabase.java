import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import big.data.DataSource;

/**
 * This class represents the NeoDatabase object, where the NearEarthObjects
 * seen from the NASA NeoW API are stored in the NeoDatabase. An ArrayList is
 * used to store all the NearEarthObjects.
 *
 * @author Alan George
 * ID: 114484206
 * Recitation #: 03
 */
public class NeoDatabase {
    // Public variables
    public static final String API_KEY = "HcpkwUesG8VtmnQ53EP1XoqCWmzgXjncx1u7aaWO";
    public static final String API_ROOT = "https://api.nasa.gov/neo/rest/v1/neo/browse?";

    // Data Fields (Member Variables)
    public List<NearEarthObject> NEODatabase;

    // Constructor (empty)
    /**
     * This is a constructor for the NeoDatabase object, where this object uses
     * an ArrayList object as a database to store all the NearEarthObjects
     * taken from the NASA NeoW API.
     */
    public NeoDatabase() {
        NEODatabase = new ArrayList<NearEarthObject>();
    }

    // Other Functions
    /**
     * This is a method that builds the corresponding queryURL from the page
     * number to access the correct page in the NASA NeoW API.
     *
     * @param pageNumber
     * Integer value that represents the page number to access NearEarthObjects
     * from the NASA NeoW API.
     *
     * @throws IllegalArgumentException
     * Thrown if the pageNumber is not between 0 and 715.
     */
    public String buildQueryURL (int pageNumber) throws IllegalArgumentException{
        if (pageNumber < 0 || pageNumber > 715 ) {
            throw new IllegalArgumentException();
        }

        String queryURL = API_ROOT + "page=" + pageNumber + "&api_key=" + API_KEY;

        return queryURL;
    }

    /**
     * This is a method that adds all the NearEarthObjects in a page from
     * the NASA NeoW API to the NEODatabase. Usually 20 NearEarthObjects are in
     * a page.
     *
     * @param queryURL
     * String variable that represents the queryURL that access a page from the
     * NASA NeoW API JSON file.
     *
     * @throws IllegalArgumentException
     * Thrown if queryURL is a null string.
     */
    public void addAll (String queryURL) throws IllegalArgumentException{
        if (queryURL == null) {
            throw new IllegalArgumentException();
        }

        // Connecting to web with DataSource
        DataSource ds = DataSource.connectJSON(queryURL);
        ds.load();

        NearEarthObject[] NEOData = ds.fetchArray(
                "NearEarthObject",
                "near_earth_objects/neo_reference_id",
                "near_earth_objects/name",
                "near_earth_objects/absolute_magnitude_h",
                "near_earth_objects/estimated_diameter/kilometers/estimated_diameter_min",
                "near_earth_objects/estimated_diameter/kilometers/estimated_diameter_max",
                "near_earth_objects/is_potentially_hazardous_asteroid",
                "near_earth_objects/close_approach_data/epoch_date_close_approach",
                "near_earth_objects/close_approach_data/miss_distance/kilometers",
                "near_earth_objects/close_approach_data/orbiting_body"
        );

        for (int i = 0; i < NEOData.length; i++) {
            NEODatabase.add(NEOData[i]);
        }
    }

    /**
     * This is a method that sorts the NEODatabase depending on a type of
     * Comparator, where the database can be sorted by a NearEarthObject's
     * referenceID, diameter, approach date, or their miss distance.
     *
     * @param comp
     * Comparator variable that represents the way that the NEODatabase gets
     * sorted.
     *
     * @throws IllegalArgumentException
     * Thrown if queryURL is a null string.
     */
    public void sort (Comparator<NearEarthObject> comp) throws IllegalArgumentException{
        if (comp == null) {
            throw new IllegalArgumentException();
        }

        Collections.sort(NEODatabase, comp);
    }

    /**
     * This is a method that prints the contents of the NEODatabase into a neat
     * table, where each NearEarthObject's id, name, magnitude, diameter,
     * danger threat, approach date, miss distance, and orbital body are shown.
     */
    public void printTable() {
        System.out.println("\n  ID   |           Name            | Mag. | Diameter | Danger | Close Date | Miss Dist | Orbits");
        System.out.println("================================================================================================");

        DateFormat simpleFormat = new SimpleDateFormat("MM-dd-yyyy");

        for (int i = 0; i < NEODatabase.size(); i++) {
            String simpleDate = simpleFormat.format(NEODatabase.get(i).getNEOclosestApproachDate());
            String simpleMagnitude = String.format("%.1f", NEODatabase.get(i).getNEOabsoluteMagnitude());
            String simpleDiameter = String.format("%.3f", NEODatabase.get(i).getNEOaverageDiameter());
            String simpleMissDistance = String.format("%.0f", NEODatabase.get(i).getNEOmissDistance());

            String tableRow = "";

            if (NEODatabase.get(i).getNEOname().length() < 27) {
                tableRow = String.format("%-9s%-28s%-7s%-11s%-9s%-12s%-13s%-2s", NEODatabase.get(i).getNEOreferenceID(), NEODatabase.get(i).getNEOname(),
                        simpleMagnitude, simpleDiameter, NEODatabase.get(i).getNEOisDangerous(),
                        simpleDate, simpleMissDistance, NEODatabase.get(i).getNEOorbitingBody());
            } else {
                tableRow = String.format("%-9s%-28s%-7s%-11s%-9s%-12s%-13s%-2s", NEODatabase.get(i).getNEOreferenceID(), NEODatabase.get(i).getNEOname().substring(0, 26),
                        simpleMagnitude, simpleDiameter, NEODatabase.get(i).getNEOisDangerous(),
                        simpleDate, simpleMissDistance, NEODatabase.get(i).getNEOorbitingBody());
            }

            System.out.println(tableRow);
        }
    }

/*    public void printAll () {
        for (int i = 0; i < NEODatabase.size(); i++) {
            System.out.println(NEODatabase.get(i).toString());
        }
        System.out.println();
    }*/
}