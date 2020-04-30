package geometry;

/**
 * This class creates new point. A point is an object that has x and y values.
 *
 * @author Yana Molodetsky
 *
 */
public class Point {
    private double x;
    private double y;

    /**
     * Create new point.
     *
     * @param x This is the x value of the new point.
     * @param y This is the y value of the new point.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * This method measures the distance between two points.
     *
     * @param other This is the point that is used to measure the distance.
     * @return The number that represent the distance.
     */
    public double distance(Point other) {
        double tempX;
        double tempY;
        double sum;
        double sqrRoot;
        tempX = (other.x - this.x) * (other.x - this.x);
        tempY = (other.y - this.y) * (other.y - this.y);
        sum = tempY + tempX;
        sqrRoot = Math.sqrt(sum);
        return sqrRoot;
    }

    /**
     * This method checks if two points are equal.
     *
     * @param other The point that is checked with the current point.
     * @return True if the points equal, False otherwise.
     */
    public boolean equals(Point other) {
        if ((this.y == other.y) && (this.x == other.x)) {
            return true;
        }
        return false;
    }

    /**
     * Getter method that returns the x value of a point.
     *
     * @return The x value.
     */
    public double getX() {
        return this.x;
    }

     /**
     * Getter method that returns the y value of a point.
     *
     * @return The y value.
     */
    public double getY() {
        return this.y;
    }
}