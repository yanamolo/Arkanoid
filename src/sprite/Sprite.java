package sprite;

import biuoop.DrawSurface;

/**
 * This is the sprite intetface for the block, ball and paddle.
 *
 * @author Yana Molodetsky
 *
 */
public interface Sprite {
    /**
     * The method draws the sprite objects on the screen.
     *
     * @param d The drawing surface for the objects.
     */
    void drawOn(DrawSurface d);

    /**
     * This method activates the sprites, by moving them and changing them on the
     * screen.
     */
    void timePassed();
}
