package game;

import collidable.Block;
import collidable.HitListener;
import sprite.Ball;

/**
 * This class in charge of removing the balls from the screen.
 *
 * @author Yana Molodetsky
 *
 */
public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter countBall;

    /**
     * Constructor of the class.
     *
     * @param game      The game variable of the balls.
     * @param countBall The number of balls in the game.
     */
    public BallRemover(GameLevel game, Counter countBall) {
        this.game = game;
        this.countBall = countBall;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(game);
        this.countBall.decrease(1);

    }

    /**
     * Getter for the number of balls.
     *
     * @return The number of balls in the game.
     */
    public Counter getNumOfBalls() {
        return this.countBall;
    }

}
