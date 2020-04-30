package sprite;

import geometry.Point;
import geometry.Line;
import collidable.CollisionInfo;
import game.GameLevel;
import game.GameEnvironment;

import java.awt.Color;

import biuoop.DrawSurface;

/**
 * This class creates new ball, and its qualities.
 *
 * @author Yana Molodetsky
 *
 */
public class Ball implements Sprite {
    private Point center;
    private int r;
    private java.awt.Color color;
    private Velocity vel;
    private GameEnvironment game;

    /**
     * Constructor of a new ball.
     *
     * @param center This is the center point of the ball.
     * @param r      This is the radius of a ball.
     * @param color  This is the color of the ball.
     * @param game   This is the game environment of the ball.
     */
    public Ball(Point center, int r, java.awt.Color color, GameEnvironment game) {
        this.center = center;
        this.r = r;
        this.color = color;
        this.game = game;
    }

    /**
     * Constructor of a ball that does not move.
     * @param x The x value of center point.
     * @param y The y value of center point.
     * @param r The radius.
     * @param color The color of the ball.
     */
    public Ball(double x, double y, int r, java.awt.Color color) {
        this.center = new Point(x, y);
        this.r = r;
        this.color = color;
    }

    /**
     * Constructor of a new ball.
     *
     * @param x     The x value of the center point.
     * @param y     The y value of the center point.
     * @param r     The radius of the ball.
     * @param color The color of a ball.
     * @param game  This is the game environment of the ball.
     */
    public Ball(double x, double y, int r, java.awt.Color color, GameEnvironment game) {
        this.center = new Point(x, y);
        this.r = r;
        this.color = color;
        this.game = game;
    }

    /**
     * Getter of the x value of the center point.
     *
     * @return The x value of the center point.
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * Getter of the y value of the center point.
     *
     * @return The y value of the center point.
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * Getter of the radius of the ball.
     *
     * @return The radius value.
     */
    public int getSize() {
        return this.r;
    }

    /**
     * Getter of the color of the ball.
     *
     * @return The color of the ball.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * The method draws the circle.
     *
     * @param surface This is the drawing surface on which the ball will be drawn.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.BLACK);
        surface.drawCircle((int) this.center.getX(), (int) this.center.getY(), this.r);
        surface.setColor(color);
        surface.fillCircle((int) this.center.getX(), (int) this.center.getY(), this.r);
    }

    /**
     * The method sets the velocity for the ball.
     *
     * @param v The velocity for the current ball.
     */
    public void setVelocity(Velocity v) {
        this.vel = v;
    }

    /**
     * The method sets the velocity for the ball.
     *
     * @param dx The angel value for the velocity.
     * @param dy The speed value for the velocity.
     */
    public void setVelocity(double dx, double dy) {
        this.vel = new Velocity(dx, dy);
    }

    /**
     * Getter of velocity.
     *
     * @return The velocity.
     */
    public Velocity getVelocity() {
        return this.vel;
    }

    /**
     * The method moves each ball one steps, and checks if its going to collide with
     * an object.
     */
    public void moveOneStep() {
        Line traj = new Line(this.center, this.vel.applyToPoint(center));
        // Getting the closest collision information
        CollisionInfo collision = this.game.getClosestCollision(traj);
        if (collision != null) {
            // If the ball is getting close to the collision point
            if (this.center.distance(collision.collisionPoint()) <= this.r) {
                Velocity v = collision.collisionObject().hit(this, collision.collisionPoint(), this.vel);
                this.vel = v;
            }
        }
        this.center = this.vel.applyToPoint(this.center);
    }

    /**
     * This method calls the method that moves the ball.
     */
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * This method adds the ball to the sprite collection.
     *
     * @param g The game variable.
     */
    public void addToGame(GameLevel g) {
        g.addSprites(this);
    }

    /**
     * This method removes the ball from the game.
     *
     * @param g The game variable.
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }
}
