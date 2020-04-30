package animation;

import java.awt.Color;

import biuoop.DrawSurface;
import game.HighScoresTable;

/**
 * This class runs the animation of the game.
 *
 * @author Yana Molodetsky
 *
 */
public class HighScoresAnimation implements Animation {
    private HighScoresTable scores;

    /**
     * Constructor.
     *
     * @param scores The high score table.
     */
    public HighScoresAnimation(HighScoresTable scores) {
        this.scores = scores;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        int moveY = 0;
        d.setColor(Color.pink.brighter());
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.BLACK);
        d.drawText(d.getHeight() / 2, 30, "High Scores:", 32);
        for (int i = 0; i < 7; i++) {
            d.drawText(40, 100 + moveY, this.scores.getHighScores().get(i).toString(), 32);
            moveY = moveY + 40;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean shouldStop() {
        return false;
    }

}
