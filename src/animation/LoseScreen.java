package animation;

import java.awt.Color;

import biuoop.DrawSurface;
import game.Counter;

/**
 * This class creates the loosing screen animation.
 *
 * @author Yana Molodetsky
 *
 */
public class LoseScreen implements Animation {
    private Counter score;

    /**
     * Constructor.
     *
     * @param score The current score of the game.
     */
    public LoseScreen(Counter score) {
        this.score = score;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.pink.brighter());
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.BLACK);
        d.drawText(20, d.getHeight() / 2, "Game over. Your score is: " + this.score.getValue(), 32);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean shouldStop() {
        return false;
    }

}
