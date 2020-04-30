package game;

import collidable.Block;
import collidable.HitListener;
import sprite.Ball;

/**
 * This class in charge of removing the blocks from the screen.
 *
 * @author Yana Molodetsky
 *
 */
public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBlocks;

    /**
     * Constructor of the class.
     *
     * @param game            The game variable.
     * @param remainingBlocks The number of blocks in the game.
     */
    public BlockRemover(GameLevel game, Counter remainingBlocks) {
        this.game = game;
        this.remainingBlocks = remainingBlocks;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getHitPoints() == 0) {
            beingHit.removeFromGame(game);
            beingHit.removeHitListener(this);
            this.remainingBlocks.decrease(1);
        }
    }

    /**
     * Getter for the remaining number of blocks.
     *
     * @return The number of blocks in the game.
     */
    public Counter getNumBlocks() {
        return this.remainingBlocks;
    }

}
