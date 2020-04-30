package levels;

import java.util.List;

import collidable.Block;
import sprite.Sprite;
import sprite.Velocity;

/**
 * This is the interface of all the levels.
 *
 * @author Yana Molodetsky
 *
 */
public interface LevelInformation {
    /**
     * Getter for the number of balls in the level.
     *
     * @return The number of balls.
     */
    int numberOfBalls();

    /**
     * The initial velocity of each ball.
     *
     * @return List of all the velocities.
     */
    List<Velocity> initialBallVelocities();

    /**
     * Getter for the speed of the paddle.
     *
     * @return The speed of the paddle.
     */
    int paddleSpeed();

    /**
     * Getter for the width of the paddle.
     *
     * @return The width of the paddle.
     */
    int paddleWidth();

    /**
     * The level name, which will be displayed at the top of the screen.
     *
     * @return Level name.
     */
    String levelName();

    /**
     * Getter for the background of the level.
     *
     * @return Sprite with the background of the level.
     */
    Sprite getBackground();

    /**
     * The Blocks that make up this level, each block contains its size, color and
     * location.
     *
     * @return The list of the blocks.
     */
    List<Block> blocks();

    /**
     * Number of levels that should be removed before the level is considered to be
     * "cleared".
     *
     * @return Number of blocks to remove.
     */
    int numberOfBlocksToRemove();
}
