package collidable;

import geometry.Rectangle;
import geometry.Point;
import sprite.Ball;
import sprite.Velocity;

/**
 * This is the collidable interface for the block and the paddle.
 *
 * @author Yana Molodetsky.
 *
 */
public interface Collidable {
    /**
     * This method returns the rectangle that is collided with the ball.
     *
     * @return The collision rectangle.
     */
    Rectangle getCollisionRectangle();

    /**
     * This method changes the direction of the ball when hitting a collidable
     * object, by changing its velocity.
     *
     * @param collisionPoint  The collision point of the ball with the collidable
     *                        object.
     * @param currentVelocity The current velocity of the ball.
     * @param hitter          The ball that does the hit.
     * @return The new velocity.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
