package collidable;
import geometry.Point;

/**
 * This class creates the collision information - the collision object and the
 * collision point.
 *
 * @author Yana Molodetsky
 *
 */
public class CollisionInfo {
    private Point collPoint;
    private Collidable object;

    /**
     * Constructor of the collision information.
     *
     * @param collpoint The collision point.
     * @param object    The collision object.
     */
    public CollisionInfo(Point collpoint, Collidable object) {
        this.collPoint = collpoint;
        this.object = object;
    }

    /**
     * Getter of the collision point.
     *
     * @return The collision point.
     */
    public Point collisionPoint() {
        return this.collPoint;
    }

    /**
     * Getter of the collision object.
     *
     * @return The collision object.
     */
    public Collidable collisionObject() {
        return this.object;
    }
}
