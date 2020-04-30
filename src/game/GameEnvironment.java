package game;
import geometry.Line;
import geometry.Point;
import collidable.Collidable;
import collidable.CollisionInfo;
import java.util.List;
import java.util.ArrayList;

/**
 * This class creates the game environment that holds the collidable objects.
 *
 * @author Yana Molodetsky
 *
 */
public class GameEnvironment {
    private List<Collidable> coll;

    /**
     * Constructor of the Game Environment.
     */
    public GameEnvironment() {
        this.coll = new ArrayList<Collidable>();
    }

    /**
     * Adding the new collidable object to the list of existing objects.
     *
     * @param c The collidable object.
     */
    public void addCollidable(Collidable c) {
        this.coll.add(c);
    }
    /**
     * Getter for the collidable's list.
     * @return The collidable's list.
     */
    public List<Collidable> getCollList() {
        return this.coll;
    }

    /**
     * This method returns the closest collision to the given line - the collision
     * point and the collision object.
     *
     * @param trajectory The given line for the check.
     * @return The closest collision informathion.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        Point temp;
        if (this.coll.size() >= 1) {
            CollisionInfo min = null;
            // Creating temporary minimum point.
            Point minInter = trajectory.end();
            for (int i = 0; i < this.coll.size(); i++) {
                temp = trajectory.closestIntersectionToStartOfLine(this.coll.get(i).getCollisionRectangle());
                if (temp != null) {
                    // Checking if the current collision point is closer to the trajectory than the
                    // minimum point.
                    if (temp.distance(trajectory.start()) < minInter.distance(trajectory.start())) {
                        minInter = temp;
                        min = new CollisionInfo(minInter, this.coll.get(i));
                    }
                }
            }
            return min;
        }
        return null;
    }
}
