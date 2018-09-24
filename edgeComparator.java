import java.util.Comparator;

/**
 * this is the edgeComparator class which implements Comparator
 */
public class edgeComparator implements Comparator {
    /**
     * this is the compare method
     * @param left, object
     * @param right, object
     * @return the int value
     */
    public int compare(Object left, Object right) {
        Edge l = (Edge)left;
        Edge r = (Edge)right;
        int k = l.compareTo(r);
        return k;
    }
}
