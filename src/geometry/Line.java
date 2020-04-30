package geometry;

import java.awt.Color;
import java.util.List;

/**
 * This class generates new line and its qualities.
 *
 * @author Yana Molodetsky
 *
 */
public class Line {
    private Point start;
    private Point end;
    private double m;
    private double b;
    private Color color;

    /**
     * Creates new line.
     *
     * @param start This is the starting point of the line.
     * @param end   This is the ending point of the line.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
        this.m = (this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX());
        this.b = this.start.getY() - (this.m * this.start.getX());
        this.color = Color.BLACK;
    }

    /**
     * Creates new line.
     *
     * @param x1 The x value of the starting point of the line.
     * @param y1 The y value of the starting point of the line.
     * @param x2 The x value of the ending point.
     * @param y2 The y value of the ending point.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
        this.m = (this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX());
        this.b = this.start.getY() - (this.m * this.start.getX());
    }

    /**
     * This method calculates the length of a line.
     *
     * @return The length of the current line.
     */
    public double length() {
        double distance;
        distance = this.start.distance(this.end);
        return distance;
    }

    /**
     * The method calculates the middle point of a line.
     *
     * @return The center point of a line.
     */
    public Point middle() {
        Point mid;
        double midX;
        double midY;
        midX = (this.start.getX() + this.end.getX()) / 2;
        midY = (this.start.getY() + this.end.getY()) / 2;
        mid = new Point(midX, midY);
        return mid;
    }

    /**
     * Getter method that returns the starting point of a line.
     *
     * @return The starting point.
     */
    public Point start() {
        return this.start;
    }

    /**
     * Getter method that returns the ending point of a line.
     *
     * @return The ending point.
     */
    public Point end() {
        return this.end;
    }

    /**
     * The method that checks if two lines intersect.
     *
     * @param other The line that is checked if intersects with the current line.
     * @return True if lines intersect, False otherwise.
     */
    public boolean isIntersecting(Line other) {
        Point inter = this.intersectionWith(other);
        /*
         * If the point returned is null, that means that the two lines are not
         * intersecting.
         */
        if (inter == null) {
            return false;
        }
        return true;
    }

    /**
     * This method calculates the point of intersection between two lines, and then
     * checks if the point found is on both lines. If so, the function will return
     * the found point.
     *
     * @param other The second line for the intersection check.
     * @return The point of intersection, and null if there is no such point.
     */
    public Point intersectionWith(Line other) {
        double m2;
        double b2;
        double x;
        double y;
        Point p;
        // Calculating the slopes of both lines
        m2 = (other.start().getY() - other.end().getY()) / (other.start().getX() - other.end().getX());
        // Calculating the y-intercept
        b2 = other.start().getY() - (m2 * other.start().getX());
        /*
         * Checking special cases. If the two lines are aligned with the axes, or if
         * only one of them is. If both lines aligned with the axes, the method returns
         * null - there is no intersection point. Else, an intersection point created.
         */
        if (this.start.getX() == this.end.getX() && other.start().getX() == other.end().getX()) {
            return null;
        } else if (this.start.getX() != this.end.getX() && other.start().getX() == other.end().getX()) {
            x = other.start().getX();
            y = (this.m * x) + this.b;
        } else if (this.start.getX() == this.end.getX() && other.start().getX() != other.end().getX()) {
            x = this.start.getX();
            y = (m2 * x) + b2;
        } else if (this.start.getY() == this.end.getY() && other.start().getY() == other.end().getY()) {
            return null;
        } else if (this.start.getY() == this.end.getY() && other.start().getY() != other.end().getY()) {
            y = this.start.getY();
            x = (y - b2) / m2;
        } else if (this.start.getY() != this.end.getY() && other.start().getY() == other.end().getY()) {
            y = other.start().getY();
            x = (y - this.b) / this.m;
        } else {
            x = (b2 - this.b) / (this.m - m2);
            y = (this.m * x) + this.b;
        }
        p = new Point(x, y);
        /*
         * Checking if the point is on both lines - if the x and y values are both
         * between the x and y of the starting point and ending point of both lines.
         */
        if (((x <= this.start.getX() && x >= this.end.getX()) || (x >= this.start.getX() && x <= this.end.getX()))) {
            if ((x <= other.start().getX() && x >= other.end().getX())
                    || (x >= other.start().getX() && x <= other.end().getX())) {
                if ((y <= this.start.getY() && y >= this.end.getY())
                        || (y >= this.start.getY() && y <= this.end.getY())) {
                    if ((y <= other.start().getY() && y >= other.end().getY())
                            || (y >= other.start().getY() && y <= other.end().getY())) {
                        return p;
                    }
                }
            }
        }
        return null;
    }

    /**
     * This method checks if both lines are the same.
     *
     * @param other The second line that is checked with the current line.
     * @return True if two lines are the same, False otherwise.
     */
    public boolean equals(Line other) {
        if ((this.end == other.end) || (this.end == other.start) || (this.start == other.start)
                || (this.start == this.end)) {
            return true;
        }
        return false;
    }

    /**
     * The method finds the closest intersection point of the rectangle to the start
     * of the line.
     *
     * @param rect The rectangle that is checked with the line.
     * @return The closest intersection point. If there is no intersection point,
     *         return null.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        double minDis;
        Point minPoint;
        Line l = new Line(this.start, this.end);
        List<Point> points = rect.intersectionPoints(l);
        if (points.size() != 0) {
            // Saving temporary minimum distance and point.
            minPoint = points.get(0);
            minDis = this.start.distance(minPoint);
            for (int i = 1; i < points.size(); i++) {
                if (this.start.distance(points.get(i)) < minDis) {
                    minDis = this.start.distance(points.get(i));
                    minPoint = points.get(i);
                }
            }
            return minPoint;
        }
        return null;
    }

    /**
     * Setter for the color.
     *
     * @param c The color that should be set.
     */
    public void setColor(Color c) {
        this.color = c;
    }

    /**
     * Getter for the color.
     *
     * @return The color of the line.
     */
    public Color getColor() {
        return this.color;
    }
}
