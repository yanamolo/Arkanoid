package collidable;

/**
 * This class is an interface of the hit notifying objects.
 *
 * @author Yana Molodetsky
 *
 */
public interface HitNotifier {
    /**
     * This method adds a listener to the list.
     *
     * @param hl The listener that should be added.
     */
    void addHitListener(HitListener hl);

    /**
     * This method removes a listener from the list.
     *
     * @param hl The listener that should be removed.
     */
    void removeHitListener(HitListener hl);

}
