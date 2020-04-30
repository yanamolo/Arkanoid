package levels;

import java.util.Map;

import collidable.Block;

/**
 * The factory of the blocks.
 *
 * @author Yana Molodetsky
 *
 */
public class BlocksFromSymbolsFactory {

    private Map<String, Integer> spacerWidths;
    private Map<String, BlockCreator> blockCreators;

    /**
     * Constructor.
     *
     * @param spacerWidths  The map of the spacers from the file.
     * @param blockCreators The map of the block types from the file.
     */
    public BlocksFromSymbolsFactory(Map<String, Integer> spacerWidths, Map<String, BlockCreator> blockCreators) {
        this.blockCreators = blockCreators;
        this.spacerWidths = spacerWidths;
    }

    /**
     * Returns if the string is a spacer symbol.
     *
     * @param s The string that is checked.
     * @return returns true if 's' is a valid space symbol.
     */
    public boolean isSpaceSymbol(String s) {
        return this.spacerWidths.containsKey(s);
    }

    /**
     * Returns if the string is a block create type.
     *
     * @param s The string that is checked.
     * @return returns true if 's' is a valid block symbol.
     */
    public boolean isBlockSymbol(String s) {
        return this.blockCreators.containsKey(s);
    }

    /**
     * Creating a block and returning it.
     *
     * @param s    The symbol of the block.
     * @param xpos The x staring point of the block.
     * @param ypos The y staring point of the block.
     * @return Return a block according to the definitions associated with symbol s.
     *         The block will be located at position (xpos, ypos).
     */
    public Block getBlock(String s, int xpos, int ypos) {
        return this.blockCreators.get(s).create(xpos, ypos);
    }

    /**
     * Getter for the width of the spacer.
     * @param s The symbol of the spacer.
     * @return Returns the width in pixels associated with the given spacer-symbol.
     */
    public int getSpaceWidth(String s) {
        return this.spacerWidths.get(s);
    }
}
