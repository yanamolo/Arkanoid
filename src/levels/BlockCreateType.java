package levels;

import java.awt.Color;
import java.util.Map;

import collidable.Block;
import geometry.Rectangle;
/**
 * The class creates a type of block creator.
 * @author Yana Molodetsky
 *
 */
public class BlockCreateType implements BlockCreator {
    private int width;
    private int height;
    private Color stroke;
    private int hitPoint;
    private Map<Integer, Color> listColor;
    private Map<Integer, String> listImage;
    private boolean[] isUsed;

    /**
     * Constructor.
     */
    public BlockCreateType() {
        this.isUsed = new boolean[5];
        for (int i = 0; i < isUsed.length; i++) {
            this.isUsed[i] = false;
        }
    }

    /**
     * Setter for the width.
     * @param wi The parameter.
     */
    public void setWidth(int wi) {
        this.width = wi;
        this.isUsed[0] = true;
    }

    /**
     * Setter for the height.
     * @param hei The parameter.
     */
    public void setHeight(int hei) {
        this.height = hei;
        this.isUsed[1] = true;
    }

    /**
     * Setter for the stroke.
     * @param stro The parameter.
     */
    public void setStroke(Color stro) {
        this.stroke = stro;
        this.isUsed[2] = true;
    }

    /**
     * Setter for hitting points.
     * @param hitP The parameter.
     */
    public void setHitPoint(int hitP) {
        this.hitPoint = hitP;
        this.isUsed[3] = true;
    }

    /**
     * Setter for the color list.
     * @param listC The parameter.
     */
    public void setListColor(Map<Integer, Color> listC) {
        this.listColor = listC;
        this.isUsed[4] = true;
    }

    /**
     * Setter for the image list.
     * @param listIm The parameter.
     */
    public void setListImage(Map<Integer, String> listIm) {
        this.listImage = listIm;
        this.isUsed[4] = true;
    }

    /**
     * Checking if all the parameters has been added.
     * @return True if all parameters added, false otherwise.
     */
    public boolean doesFull() {
        for (int i = 0; i < this.isUsed.length; i++) {
            if (!this.isUsed[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Block create(int xpos, int ypos) {
        Rectangle rect = new Rectangle((double) xpos, (double) ypos, this.width, this.height);
        Block bl = new Block(rect, this.stroke, this.hitPoint, this.listColor, this.listImage);
        return bl;
    }
}
