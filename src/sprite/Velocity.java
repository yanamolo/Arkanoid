package sprite;
import geometry.Point;

/**
 * The class of the velocity of the balls, and its qualities.
 *
 * @author Yana Molodetsky
 *
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * Constructor of the velocity.
     *
     * @param dx The angel value.
     * @param dy The speed value.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Method that applies the change of dy and dx to the velocity so the ball will
     * move.
     *
     * @param p The point that her values added to the current velocity point.
     * @return The new velocity point with updated dx dy values.
     */
    public Point applyToPoint(Point p) {
        double newX = p.getX() + this.dx;
        double newY = p.getY() + this.dy;
        Point newP = new Point(newX, newY);
        return newP;
    }

    /**
     * Getter of the dx.
     *
     * @return The dx of the velocity.
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * Getter of the dy.
     *
     * @return The dy of the velocity.
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * Method that apply the angle and speed to the current velocity.
     *
     * @param angle The angle in which the ball moves.
     * @param speed The speed of the ball.
     * @return The new velocity using the speed and angle values.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        angle = Math.toRadians(angle);
        double dx = speed * Math.sin(angle);
        double dy = -speed * Math.cos(angle);
        return new Velocity(dx, dy);
    }
}
