package sprite;

import java.awt.Color;

import biuoop.DrawSurface;
import game.GameLevel;

/**
 * This class displays the name of the level.
 *
 * @author Yana Molodetsky
 *
 */
public class NameIndicator implements Sprite {
    private String levelName;

    /**
     * Constructor.
     *
     * @param levelName The name of the level.
     */
    public NameIndicator(String levelName) {
        this.levelName = levelName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.WHITE);
        d.fillRectangle(0, 0, 800, 20);
        d.setColor(Color.BLACK);
        d.drawText(550, 15, "Level Name: " + this.levelName, 15);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void timePassed() {

    }

    /**
     * Adding the sprite to the game.
     *
     * @param g The game parameter.
     */
    public void addToGame(GameLevel g) {
        g.addSprites(this);
    }

}
