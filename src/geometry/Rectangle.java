package geometry;

import java.util.List;

import java.util.ArrayList;

/**
 * This class creates the rectangle, using an upper left point, width and
 * height.
 *
 * @author Yana Molodetsky
 *
 */
public class Rectangle {
    private Point uppLeft;
    private double width;
    private double height;
    private Line uppBond;
    private Line leftBond;
    private Line rightBond;
    private Line downBond;

    /**
     * Constructor of the rectangle.
     *
     * @param x      The x value of the upper left point.
     * @param y      The y value of the upper left point.
     * @param width  The width of the rectangle.
     * @param height The height of the rectangle.
     */
    public Rectangle(double x, double y, double width, double height) {
        Point upperLeft = new Point(x, y);
        this.height = height;
        this.width = width;
        this.uppLeft = upperLeft;
        // Calculating the sides of the rectangle.
        this.uppBond = new Line(upperLeft.getX(), upperLeft.getY(), upperLeft.getX() + width, upperLeft.getY());
        this.leftBond = new Line(upperLeft.getX(), upperLeft.getY(), upperLeft.getX(), upperLeft.getY() + height);
        this.downBond = new Line(upperLeft.getX(), upperLeft.getY() + height, upperLeft.getX() + width,
                upperLeft.getY() + height);
        this.rightBond = new Line(upperLeft.getX() + width, upperLeft.getY() + height, upperLeft.getX() + width,
                upperLeft.getY());
    }

    /**
     * This method finds intersection points with a given line and the rectangle.
     * The method checks intersection with the line and each of the sides of the
     * rectangle, and returns list of intersection points.
     *
     * @param line The given line for the intersection check.
     * @return The list of intersection points.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        List<Point> interPoints = new ArrayList<Point>();
        if (this.uppBond.isIntersecting(line)) {
            Point p1 = this.uppBond.intersectionWith(line);
            interPoints.add(p1);
        }
        if (this.leftBond.isIntersecting(line)) {
            Point p2 = this.leftBond.intersectionWith(line);
            interPoints.add(p2);
        }
        if (this.downBond.isIntersecting(line)) {
            Point p3 = this.downBond.intersectionWith(line);
            interPoints.add(p3);
        }
        if (this.rightBond.isIntersecting(line)) {
            Point p4 = this.rightBond.intersectionWith(line);
            interPoints.add(p4);
        }
        return interPoints;
    }

    /**
     * Getter method for the width of the rectangle.
     *
     * @return The width of the rectangle.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Getter method for the height of the rectangle.
     *
     * @return The height of the rectangle.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Getter method for the upper left point of the rectangle.
     *
     * @return The upper left point.
     */
    public Point getUpperLeftPoint() {
        return this.uppLeft;
    }

    /**
     * Getter method for the upper side of the rectangle.
     *
     * @return The upper side line.
     */
    public Line getUppBond() {
        return this.uppBond;
    }

    /**
     * Getter method for the down side of the rectangle.
     *
     * @return The down side line.
     */
    public Line getDownBond() {
        return this.downBond;
    }

    /**
     * Getter method for the left side of the rectangle.
     *
     * @return The left side line.
     */
    public Line getLeftBond() {
        return this.leftBond;
    }

    /**
     * Getter method for the right side of the rectangle.
     *
     * @return The right side line.
     */
    public Line getRightBond() {
        return this.rightBond;
    }
}
