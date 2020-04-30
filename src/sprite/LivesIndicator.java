package sprite;

import java.awt.Color;

import biuoop.DrawSurface;
import game.Counter;
import game.GameLevel;

/**
 * This class displays the amount of lives in the game.
 *
 * @author Yana Molodetsky
 *
 */
public class LivesIndicator implements Sprite {
    private Counter lives;

    /**
     * Constructor of the lives in the game.
     *
     * @param lives The lives counter in the game.
     */
    public LivesIndicator(Counter lives) {
        this.lives = lives;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(300, 15, "Lives: " + this.lives.getValue(), 15);

    }

    /**
     * This method adds this sprite to the sprite collection.
     *
     * @param g The game variable.
     */
    public void addToGame(GameLevel g) {
        g.addSprites(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void timePassed() {

    }

}
