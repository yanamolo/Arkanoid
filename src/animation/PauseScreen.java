package animation;

import java.awt.Color;

import biuoop.DrawSurface;

/**
 * This class creates the pause screen animation.
 *
 * @author Yana Molodetsky
 *
 */
public class PauseScreen implements Animation {

    /**
     * Constructor.
     */
    public PauseScreen() {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.pink.brighter());
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.BLACK);
        d.drawText(30, d.getHeight() / 2, "paused -- press space to continue", 32);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean shouldStop() {
        return false;
    }

}
