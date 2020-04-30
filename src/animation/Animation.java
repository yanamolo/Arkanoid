package animation;

import biuoop.DrawSurface;

/**
 * The interface of all the animations in the program.
 *
 * @author Yana Molodetsky
 *
 */

public interface Animation {
    /**
     * This method draws the objects on the screen depending on the animation that
     * is running.
     *
     * @param d The draw surface.
     */
    void doOneFrame(DrawSurface d);

    /**
     * This method checks if the animation should stop.
     *
     * @return True or False to stop the animation, or to continue it.
     */
    boolean shouldStop();
}
