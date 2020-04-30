package collidable;

import sprite.Ball;

/**
 * This class is the interface of all the listeners.
 *
 * @author Yana Molodetsky
 *
 */
public interface HitListener {
    /**
     * The method changes the objects after a hit occurred.
     *
     * @param beingHit The block that is being hit.
     * @param hitter   The ball that did the hit.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
