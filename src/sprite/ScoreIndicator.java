package sprite;

import java.awt.Color;

import biuoop.DrawSurface;
import game.GameLevel;

/**
 * This class displays the score onto the screen.
 *
 * @author Yana Molodetsky
 *
 */
public class ScoreIndicator implements Sprite {
    private GameLevel game;

    /**
     * Constructor of the class.
     *
     * @param game The game variable, that the score will be displayed in.
     */
    public ScoreIndicator(GameLevel game) {
        this.game = game;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(100, 15, "Score: " + this.game.getCurrentScore().getValue(), 15);
    }

    /**
     * This method adds the sprite into the game - into the sprite collection.
     */
    public void addToGame() {
        this.game.addSprites(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void timePassed() {

    }

}
