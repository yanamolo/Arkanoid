package levels;

import collidable.Block;
/**
 * The interface of block creators.
 * @author Yana Molodetsky
 *
 */
public interface BlockCreator {
    /**
     * The method creates new block.
     * @param xpos The x point of the upper left point of the block.
     * @param ypos The y point of the upper left point of the block.
     * @return The new block created.
     */
    Block create(int xpos, int ypos);
}
