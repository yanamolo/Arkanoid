package collidable;

import geometry.Point;
import geometry.Rectangle;
import game.GameLevel;
import sprite.BackgroundColor;
import sprite.BackgroundImage;
import sprite.Ball;
import sprite.Sprite;
import sprite.Velocity;

import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * This Class Creates new block. A block holds a rectangle, color and number on
 * the block.
 *
 * @author Yana Molodetsky
 *
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Color stroke;
    private Rectangle rect;
    private int num;
    private int originalNum;
    private List<HitListener> hitListeners;
    private Map<Integer, Color> listColor;
    private Map<Integer, String> listImage;
    private BackgroundColor backColor;
    private BackgroundImage backImage;

    /**
     * Constructor of a new block.
     *
     * @param rect      The rectangle that the block created from.
     * @param stroke    The color of the frame of the block.
     * @param numPoints The number of hit points of the block.
     * @param listColor The list of colors of the block.
     * @param listImage The list of images of the block.
     */

    public Block(Rectangle rect, Color stroke, int numPoints, Map<Integer, Color> listColor,
            Map<Integer, String> listImage) {
        this.rect = rect;
        this.num = numPoints;
        this.stroke = stroke;
        this.listColor = listColor;
        this.listImage = listImage;
        this.hitListeners = new ArrayList<HitListener>();
        this.backColor = null;
        this.backImage = null;
        this.originalNum = numPoints;
        // Filling the block with the first color/image.
        if (this.listColor != null && this.listColor.containsKey(this.num)) {
            this.backColor = new BackgroundColor(this.listColor.get(this.num),
                    (int) this.rect.getUpperLeftPoint().getX(), (int) this.rect.getUpperLeftPoint().getY(),
                    (int) this.rect.getWidth(), (int) this.rect.getHeight());
        } else if (this.listImage != null && this.listImage.containsKey(this.num)) {
            this.backImage = new BackgroundImage(this.listImage.get(this.num),
                    (int) this.rect.getUpperLeftPoint().getX(), (int) this.rect.getUpperLeftPoint().getY());
        }
    }

    /**
     * Getter method that returns the rectangle.
     *
     * @return The rectangle that the block holds.
     */
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    /**
     * This method changes the direction of a ball when there is a hit.
     *
     * @param collisionPoint  The point of the collision.
     * @param currentVelocity The current velocity of the ball.
     * @param hitter The ball that does the hit.
     * @return The new velocity after the change of the direction.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        // If the collision occur at the upper bond of the rectangle.
        if (collisionPoint.getX() == this.rect.getUpperLeftPoint().getX()) {
            currentVelocity = new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        }
        // If the collision occur at the left bond of the rectangle.
        if (collisionPoint.getY() == this.rect.getUpperLeftPoint().getY()) {
            currentVelocity = new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        }
        // If the collision occur at the right bond of the rectangle.
        if (collisionPoint.getX() == this.rect.getRightBond().start().getX()) {
            currentVelocity = new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        }
        // If the collision occur at the down bond of the rectangle.
        if (collisionPoint.getY() == this.rect.getDownBond().start().getY()) {
            currentVelocity = new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        }
        this.num--;
        // Changing the color/image of the block.
        if (this.listColor != null && this.listColor.containsKey(this.num)) {
            this.backColor = new BackgroundColor(this.listColor.get(this.num),
                    (int) this.rect.getUpperLeftPoint().getX(), (int) this.rect.getUpperLeftPoint().getY(),
                    (int) this.rect.getWidth(), (int) this.rect.getHeight());
        } else if (this.listImage != null && this.listImage.containsKey(this.num)) {
            this.backImage = new BackgroundImage(this.listImage.get(this.num),
                    (int) this.rect.getUpperLeftPoint().getX(), (int) this.rect.getUpperLeftPoint().getY());
        }
        this.notifyHit(hitter);
        return currentVelocity;
    }

    /**
     * This method draws the rectangle on the screen.
     *
     * @param surface The drawing surface.
     */
    public void drawOn(DrawSurface surface) {
        if (this.backColor != null) {
            this.backColor.drawOn(surface);
        } else if (this.backImage != null) {
            this.backImage.drawOn(surface);
        }
        surface.setColor(this.stroke);
        surface.drawRectangle((int) this.rect.getUpperLeftPoint().getX(), (int) this.rect.getUpperLeftPoint().getY(),
                (int) this.rect.getWidth(), (int) this.rect.getHeight());
    }

    /**
     * The times passed method of the block.
     */
    public void timePassed() {

    }

    /**
     * This method adds the block to the list of collidable objects and the sprite.
     *
     * @param g The game variable.
     */
    public void addToGame(GameLevel g) {
        g.addSprites(this);
        g.addCollidable(this);
    }

    /**
     * The method removes the block from the game.
     *
     * @param game The game variable that the block is deleted from.
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeHitListener(HitListener hl) {
        if (this.hitListeners.contains(hl)) {
            this.hitListeners.remove(hl);
        }
    }

    /**
     * A getter method for the number on the block (number of hits left on the
     * block).
     *
     * @return The number of hitting points on the block.
     */
    public int getHitPoints() {
        return this.num;
    }

    /**
     * The method notifies all the listeners in the list that a hit occurred.
     *
     * @param hitter The ball that made the hit.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * Setting back the hitting points.
     */
    public void setBackPoints() {
        this.num = this.originalNum;
    }
}
