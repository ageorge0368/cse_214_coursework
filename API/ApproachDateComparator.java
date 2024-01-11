/**
 * This class represents a ApproachDateComparator object, where two
 * NearEarthObjects can be compared by their approach dates. If the left
 * NearEarthObject has an approach date that is after the right
 * NearEarthObject's approach date, then 1 gets returned. If before, then -1 is
 * returned. If equal, then 0 is returned.
 *
 * @author Alan George
 * ID: 114484206
 * Recitation #: 03
 */
public class ApproachDateComparator implements java.util.Comparator<NearEarthObject>{
//    @Override

    /**
     * This method compares two NearEarthObjects by their approach dates, if
     * they are equal then 0 is returned, if the left NearEarthObject has an
     * approach date that is after the right NearEarthObject's approach date,
     * then 1 is returned. If before, then -1 is returned.
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
        // Same Date
        if (left.getNEOclosestApproachDate().compareTo(right.getNEOclosestApproachDate()) == 0) {
            return 0;
        }

        // Left date is after right date
        else if (left.getNEOclosestApproachDate().compareTo(right.getNEOclosestApproachDate()) > 0) {
            return 1;
        }

        // Left date is before right date
        else {
            return -1;
        }
    }
}
