package levels;

import java.awt.Color;
import java.util.List;

import collidable.Block;
import sprite.BackgroundColor;
import sprite.BackgroundImage;
import sprite.Sprite;
import sprite.Velocity;
/**
 * The level type created from the information from the file.
 * @author Yana Molodetsky
 *
 */
public class LevelCreateType implements LevelInformation {
    private int paddleSpeed;
    private int paddleWidth;
    private String levelName;
    private List<Velocity> velocities;
    private int startX;
    private int startY;
    private List<Block> blockList;
    private String backImage;
    private Color backColor;
    private int rowHeight;
    private int numOfBlocks;
    private String imageOrColor;
    private boolean[] isUsed;

    /**
     * Constructor.
     */
    public LevelCreateType() {
        this.isUsed = new boolean[11];
        for (int i = 0; i < isUsed.length; i++) {
            this.isUsed[i] = false;
        }
    }

    /**
     * Setter for the paddle speed.
     * @param paddleS The parameter.
     */
    public void setPaddleSpeed(int paddleS) {
        this.paddleSpeed = paddleS;
        this.isUsed[0] = true;
    }

    /**
     * Setter for the paddle width.
     * @param paddleW The parameter.
     */
    public void setPaddleWidth(int paddleW) {
        this.paddleWidth = paddleW;
        this.isUsed[1] = true;
    }

    /**
     * Setter for the level name.
     * @param levelN The parameter.
     */
    public void setLevelName(String levelN) {
        this.levelName = levelN;
        this.isUsed[3] = true;
    }

    /**
     * Setter for the velocities.
     * @param velocitiesList The list parameter.
     */
    public void setVelocities(List<Velocity> velocitiesList) {
        this.velocities = velocitiesList;
        this.isUsed[4] = true;
    }

    /**
     * Setter for the start x position of the blocks.
     * @param xStart The parameter.
     */
    public void setStartX(int xStart) {
        this.startX = xStart;
        this.isUsed[5] = true;
    }

    /**
     * Setter for the start y position of the blocks.
     * @param yStart The parameter.
     */
    public void setStartY(int yStart) {
        this.startY = yStart;
        this.isUsed[6] = true;
    }

    /**
     * Setter for the block list.
     * @param listBlock The list parameter.
     */
    public void setBlockList(List<Block> listBlock) {
        this.blockList = listBlock;
        this.isUsed[7] = true;
    }

    /**
     * Setter for the background image.
     * @param backIm The parameter.
     */
    public void setBackImage(String backIm) {
        this.imageOrColor = "i";
        this.backImage = backIm;
        this.isUsed[8] = true;
    }

    /**
     * Setter for the background color.
     * @param backC The parameter.
     */
    public void setBackColor(Color backC) {
        this.imageOrColor = "c";
        this.backColor = backC;
        this.isUsed[8] = true;
    }

    /**
     * Setter for the row height.
     * @param rowHei The parameter.
     */
    public void setRowHeight(int rowHei) {
        this.rowHeight = rowHei;
        this.isUsed[2] = true;
    }

    /**
     * Setter for the number of blocks.
     * @param numBlocks The parameter.
     */
    public void setNumOfBlocks(int numBlocks) {
        this.numOfBlocks = numBlocks;
        this.isUsed[9] = true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int numberOfBalls() {
        return this.velocities.size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        return this.velocities;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int paddleSpeed() {
        return this.paddleSpeed;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int paddleWidth() {
        return this.paddleWidth;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String levelName() {
        return this.levelName;
    }

    /***
     * {@inheritDoc}
     */
    @Override
    public Sprite getBackground() {
        if (this.imageOrColor.equals("c")) {
            BackgroundColor back = new BackgroundColor(this.backColor, 0, 0, 800, 600);
            return back;
        } else {
            BackgroundImage back = new BackgroundImage(this.backImage, 0, 0);
            return back;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Block> blocks() {
        return this.blockList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int numberOfBlocksToRemove() {
        return this.numOfBlocks;
    }

    /**
     * Getter for the x staring block point.
     * @return The x starting point.
     */
    public int getStartX() {
        return this.startX;
    }

    /**
     * Getter for the y starting block point.
     * @return The y starting point.
     */
    public int getStartY() {
        return this.startY;
    }

    /**
     * Getter for the row height.
     * @return The row height.
     */
    public int getRowHeight() {
        return this.rowHeight;
    }
    /**
     * Checking if all the parameters has been entered.
     * @return True if all parameters entered, false otherwise.
     */
    public boolean isEmpty() {
        for (int i = 0; i < this.isUsed.length - 1; i++) {
            if (!this.isUsed[i]) {
                return true;
            }
        }
        return false;
    }

}
