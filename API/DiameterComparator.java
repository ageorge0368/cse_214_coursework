/**
 * This class represents a DiameterComparator object, where two
 * NearEarthObjects can be compared by their average diameters. If the left
 * NearEarthObject has a diameter that is greater than the right NearEarthObject's
 * diameter, then 1 gets returned. If less, then -1 is returned. If equal, then 0 is
 * returned.
 *
 * @author Alan George
 * ID: 114484206
 * Recitation #: 03
 */
public class DiameterComparator implements java.util.Comparator<NearEarthObject> {
    //    @Override

    /**
     * This method compares two NearEarthObjects by their average diameters, if
     * they are equal then 0 is returned, if the left NearEarthObject has a
     * larger average diameter than the right NearEarthObject, then 1 is
     * returned. If less than, then -1 is returned.
     *
     * @param left
     * Left NearEarthObject being compared.
     *
     * @param right
     * Right NearEarthObject being compared.
     *
     * @return
     * Returns -1, 0, 1 for the conditions explained above.
     */
    public int compare(NearEarthObject left, NearEarthObject right) {
        if (left.getNEOaverageDiameter() == right.getNEOaverageDiameter()) {
            return 0;
        } else if (left.getNEOaverageDiameter() > right.getNEOaverageDiameter()) {
            return 1;
        } else {
            return -1;
        }
    }
}
