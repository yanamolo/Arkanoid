package sprite;

import biuoop.DrawSurface;
import java.util.List;
import java.util.ArrayList;

/**
 * This class creates the sprite collection of the game.
 *
 * @author Yana Molodetsky
 *
 */
public class SpriteCollection {
    private List<Sprite> sp;

    /**
     * Constructor of the sprite collection. Creates new list.
     */
    public SpriteCollection() {
        this.sp = new ArrayList<Sprite>();
    }

    /**
     * This method adds new sprite to the list.
     *
     * @param s The new sprite.
     */
    public void addSprite(Sprite s) {
        this.sp.add(s);
    }

    /**
     * This method activates the method timePassed for all the sprites.
     */
    public void notifyAllTimePassed() {
        List<Sprite> sprite = new ArrayList<Sprite>(this.sp);
        for (Sprite s : sprite) {
            s.timePassed();
        }
    }
    /**
     * Getter for the list of sprites.
     * @return The list of sprites.
     */
    public List<Sprite> getSpriteList() {
        return this.sp;
    }

    /**
     * This method activates the method of drawOn for all the sprites in the list.
     *
     * @param d The drawing surface.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : sp) {
            s.drawOn(d);
        }
    }
}
