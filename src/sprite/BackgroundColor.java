package sprite;

import java.awt.Color;

import biuoop.DrawSurface;
import game.GameLevel;
/**
 * The sprite of the background color.
 * @author Yana Molodetsky
 *
 */
public class BackgroundColor implements Sprite {
    private Color color;
    private int startX;
    private int startY;
    private int width;
    private int height;

    /**
     * Constructor.
     * @param color The color of the background.
     * @param startX The start x of the drawing.
     * @param startY The start y of the drawing.
     * @param width The width of the drawing.
     * @param height The height of the drawing.
     */
    public BackgroundColor(Color color, int startX, int startY, int width, int height) {
        this.color = color;
        this.startX = startX;
        this.startY = startY;
        this.width = width;
        this.height = height;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle(this.startX , this.startY, this.width, this.height);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void timePassed() {

    }
    /**
     * Adding the sprite to the game.
     * @param g The game parameter.
     */
    public void addToGame(GameLevel g) {
        g.addSprites(this);
    }
}
