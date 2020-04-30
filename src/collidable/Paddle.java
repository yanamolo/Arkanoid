package collidable;

import geometry.Point;
import geometry.Rectangle;
import game.GameLevel;
import sprite.Ball;
import sprite.Velocity;
import sprite.Sprite;
import java.awt.Color;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;

/**
 * This method creates the paddle for the game.
 *
 * @author Yana Molodetsky
 *
 */
public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private Rectangle padd;
    private int paddleSpeed;

    /**
     * Constructor of the paddle.
     *
     * @param padd        The rectangle that the paddle is created from.
     * @param gui         The graphic interface.
     * @param paddleSpeed The speed of the paddle.
     */
    public Paddle(Rectangle padd, GUI gui, int paddleSpeed) {
        this.keyboard = gui.getKeyboardSensor();
        this.padd = padd;
        this.paddleSpeed = paddleSpeed;
    }

    /**
     * This method checks if the x value of the rectangle is not passing the borders
     * of the game, and if not moves the paddle to the left, by creating a new
     * rectangle, with an x value decreased by the speed of the paddle.
     */
    public void moveLeft() {
        if (this.padd.getUpperLeftPoint().getX() >= 25) {
            this.padd = new Rectangle(this.padd.getUpperLeftPoint().getX() - this.paddleSpeed,
                    this.padd.getUpperLeftPoint().getY(), this.padd.getWidth(), this.padd.getHeight());

        }
    }

    /**
     * This method checks if the x value of the rectangle is not passing the borders
     * of the game, and if not, moves the paddle to the right, by creating a new
     * rectangle with an x value increased by the speed of the paddle.
     */
    public void moveRight() {
        if (this.padd.getUpperLeftPoint().getX() + this.padd.getWidth() <= 775) {
            this.padd = new Rectangle(this.padd.getUpperLeftPoint().getX() + this.paddleSpeed,
                    this.padd.getUpperLeftPoint().getY(), this.padd.getWidth(), this.padd.getHeight());
        }
    }

    /**
     * This method checks if a key pressed, and moves the paddle accordingly.
     */
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight();
        }
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft();
        }
    }

    /**
     * This method draws the paddle on the screen.
     *
     * @param d The drawing surface.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.orange);
        d.fillRectangle((int) this.padd.getUpperLeftPoint().getX(), (int) this.padd.getUpperLeftPoint().getY(),
                (int) this.padd.getWidth(), (int) this.padd.getHeight());
        d.setColor(Color.BLACK);
        d.drawRectangle((int) this.padd.getUpperLeftPoint().getX(), (int) this.padd.getUpperLeftPoint().getY(),
                (int) this.padd.getWidth(), (int) this.padd.getHeight());
    }

    /**
     * This method returns the rectangle of the paddle, that collides with the ball.
     *
     * @return The collision rectangle.
     */
    public Rectangle getCollisionRectangle() {
        return this.padd;
    }

    /**
     * This method checks in which part of the paddle the collision occurred, and
     * changes the speed of the ball according to the part of the paddle. The
     * speed is calculated using the Pythagorean theorem.
     *
     * @param collisionPoint  The collision point with the ball.
     * @param currentVelocity The current velocity of the ball.
     * @param hitter The ball that does the hit.
     * @return The new velocity changed to the new speed.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double dis = this.padd.getWidth() / 5;
        double newDx = currentVelocity.getDx() * currentVelocity.getDx();
        double newDy = currentVelocity.getDy() * currentVelocity.getDy();
        if (collisionPoint.distance(this.padd.getUpperLeftPoint()) <= dis) {
            currentVelocity = Velocity.fromAngleAndSpeed(300, Math.sqrt(newDx + newDy));
        } else if (collisionPoint.distance(this.padd.getUpperLeftPoint()) <= dis * 2) {
            currentVelocity = Velocity.fromAngleAndSpeed(330, Math.sqrt(newDx + newDy));
        } else if (collisionPoint.distance(this.padd.getUpperLeftPoint()) <= dis * 3) {
            currentVelocity = Velocity.fromAngleAndSpeed(-currentVelocity.getDx(), Math.sqrt(newDx + newDy));
        } else if (collisionPoint.distance(this.padd.getUpperLeftPoint()) <= dis * 4) {
            currentVelocity = Velocity.fromAngleAndSpeed(30, Math.sqrt(newDx + newDy));
        } else if (collisionPoint.distance(this.padd.getUpperLeftPoint()) <= dis * 5) {
            currentVelocity = Velocity.fromAngleAndSpeed(60, Math.sqrt(newDx + newDy));
        }
        return currentVelocity;
    }

    /**
     * This method adds the paddle to the sprite collection and to the collidable
     * list.
     *
     * @param g The game variable.
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprites(this);
    }

    /**
     * This method removes the paddle from the game.
     *
     * @param g The game variable that the paddle should be removed from.
     */
    public void removeFromGame(GameLevel g) {
        g.removeCollidable(this);
        g.removeSprite(this);
    }
}
