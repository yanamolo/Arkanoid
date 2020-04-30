package game;

import collidable.Block;
import collidable.HitListener;
import sprite.Ball;

/**
 * This class changes the score of the game.
 *
 * @author Yana Molodetsky
 *
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * Constructor of the class.
     *
     * @param scoreCounter the score counter of the game.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        // If the block is removed - 15 points
        if (beingHit.getHitPoints() == 0) {
            this.currentScore.increase(15);
        } else {
            this.currentScore.increase(5);
        }
    }

    /**
     * Getter of the score of the game.
     *
     * @return The current score.
     */
    public Counter getScore() {
        return this.currentScore;
    }

}
