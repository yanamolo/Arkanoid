package sprite;

import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import biuoop.DrawSurface;
import game.GameLevel;
/**
 * This class creates the sprite for the image.
 * @author Yana Molodetsky
 *
 */
public class BackgroundImage implements Sprite {
    private Image image;
    private int startX;
    private int startY;

    /**
     * Constructor.
     * @param nameOfFile The path for the image.
     * @param startX The start x for the drawing.
     * @param startY The start y for the drawing.
     */
    public BackgroundImage(String nameOfFile, int startX, int startY) {
        this.startX = startX;
        this.startY = startY;
        InputStream im = ClassLoader.getSystemClassLoader().getResourceAsStream(nameOfFile);
        try {
            this.image = ImageIO.read(im);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.drawImage(this.startX, this.startY, this.image);
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
