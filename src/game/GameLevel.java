package game;

import geometry.Rectangle;
import levels.LevelInformation;
import collidable.Block;
import collidable.Collidable;
import collidable.Paddle;
import sprite.Ball;
import sprite.LivesIndicator;
import sprite.NameIndicator;
import sprite.ScoreIndicator;
import sprite.Sprite;
import sprite.SpriteCollection;
import animation.Animation;
import animation.AnimationRunner;
import animation.CountdownAnimation;
import animation.KeyPressStoppableAnimation;
import animation.PauseScreen;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * This class creates the game - initializing it and running it.
 *
 * @author Yana Molodetsky
 *
 */
public class GameLevel implements Animation {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private AnimationRunner runner;
    private boolean running;
    private BlockRemover lsBlock;
    private BallRemover lsBall;
    private ScoreTrackingListener scoreTrack;
    private Counter numOfLives;
    private biuoop.KeyboardSensor keyboard;
    private LevelInformation level;

    /**
     * Constructor.
     * @param level The current level that is running.
     * @param keyboard The keyboard parameter.
     * @param runner The current animation of the game.
     * @param numOfLives Number of lives available in the game.
     * @param score The score of the game.
     */
    public GameLevel(LevelInformation level, biuoop.KeyboardSensor keyboard, AnimationRunner runner, Counter numOfLives,
            ScoreTrackingListener score) {
        this.runner = runner;
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.scoreTrack = score;
        this.numOfLives = numOfLives;
        this.keyboard = keyboard;
        this.level = level;
    }

    /**
     * This method adds new collidable object to the list.
     *
     * @param c The collidable object.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * This method adds new sprite to the list.
     *
     * @param s The sprite object.
     */
    public void addSprites(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * This method removes the collidable object from the game.
     *
     * @param c The collidable that should be removed from the list.
     */
    public void removeCollidable(Collidable c) {
        if (this.environment.getCollList().contains(c)) {
            this.environment.getCollList().remove(c);
        }
    }

    /**
     * This method removes the sprite object from the game.
     *
     * @param s The sprite object that should be removed.
     */
    public void removeSprite(Sprite s) {
        if (this.sprites.getSpriteList().contains(s)) {
            this.sprites.getSpriteList().remove(s);
        }
    }

    /**
     * Returns the current score in the game.
     *
     * @return The current score.
     */
    public Counter getCurrentScore() {
        return this.scoreTrack.getScore();
    }

    /**
     * This method initializing the game. Creates the ball, blocks and the paddle.
     */
    public void initialize() {
        Counter numOfBlocks = new Counter(0);
        Counter numOfBalls = new Counter(this.level.numberOfBalls());
        this.lsBlock = new BlockRemover(this, numOfBlocks);
        this.lsBall = new BallRemover(this, numOfBalls);
        this.sprites.addSprite(this.level.getBackground());
        NameIndicator name = new NameIndicator(this.level.levelName());
        name.addToGame(this);
        ScoreIndicator score = new ScoreIndicator(this);
        score.addToGame();
        // The blocks that at the edges of the screen.
        Rectangle leftRec = new Rectangle(0, 20, 20, 580);
        Rectangle downRec = new Rectangle(0, 600, 800, 20);
        Rectangle rightRec = new Rectangle(780, 20, 20, 580);
        Rectangle uppRec = new Rectangle(0, 20, 800, 30);
        Map<Integer, Color> mapForBorder = new TreeMap<>();
        mapForBorder.put(0, Color.DARK_GRAY);
        Block b1 = new Block(leftRec, Color.BLACK, 0, mapForBorder, null);
        b1.addToGame(this);
        Block downBlock = new Block(downRec, Color.BLACK, 0, mapForBorder, null);
        downBlock.addToGame(this);
        Block b3 = new Block(rightRec, Color.BLACK, 0, mapForBorder, null);
        b3.addToGame(this);
        Block b4 = new Block(uppRec, Color.BLACK, 0, mapForBorder, null);
        b4.addToGame(this);
        downBlock.addHitListener(this.lsBall);
        // Creating the blocks for the game.
        for (Block c : this.level.blocks()) {
            c.addToGame(this);
            this.lsBlock.getNumBlocks().increase(1);
            c.addHitListener(this.lsBlock);
            c.addHitListener(scoreTrack);
        }
    }

    /**
     * This method runs the game. It draws the figures on the screen, and moving
     * them accordingly.
     */
    public void playOneTurn() {
        int moveX = 0;
        List<Ball> balls = new ArrayList<Ball>();
        for (int i = 0; i < this.level.numberOfBalls(); i++) {
            Ball ball = new Ball(400, 540, 5, java.awt.Color.WHITE, this.environment);
            balls.add(ball);
            moveX = moveX + 50;
        }
        // Creating paddle and balls.
        Rectangle padd = new Rectangle(400 - (this.level.paddleWidth() / 2), 550, this.level.paddleWidth(), 20);
        Paddle paddle = new Paddle(padd, this.runner.getGUI(), this.level.paddleSpeed());
        paddle.addToGame(this);
        this.running = false;
        for (int i = 0; i < this.level.numberOfBalls(); i++) {
            balls.get(i).setVelocity(this.level.initialBallVelocities().get(i));
            balls.get(i).addToGame(this);
        }
        LivesIndicator lives = new LivesIndicator(this.numOfLives);
        lives.addToGame(this);
        this.runner.run(new CountdownAnimation(2, 3, this.sprites));
        this.runner.run(this);
        // If the animation of the game stops - removing the balls and paddle.
        if (this.running) {
            for (Ball b : balls) {
                b.removeFromGame(this);
            }
            paddle.removeFromGame(this);
            this.lsBall.getNumOfBalls().increase(this.level.numberOfBalls());
            for (Block c : this.level.blocks()) {
                c.setBackPoints();
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public boolean shouldStop() {
        if (this.lsBlock.getNumBlocks().getValue() == 0) {
            this.running = true;
            this.scoreTrack.getScore().increase(100);
        } else if (this.lsBall.getNumOfBalls().getValue() == 0) {
            this.running = true;
            this.numOfLives.decrease(1);
        }
        return this.running;
    }

    /**
     * {@inheritDoc}
     */
    public void doOneFrame(DrawSurface d) {
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        if (this.keyboard.isPressed("p")) {
            Animation a = new PauseScreen();
            Animation ak = new KeyPressStoppableAnimation(this.keyboard, KeyboardSensor.SPACE_KEY, a);
            this.runner.run(ak);
        }
    }

    /**
     * Getter for the number of lives.
     * @return Number of lives.
     */
    public int getNumOfLives() {
        return this.numOfLives.getValue();
    }

    /**
     * Getter for the current number of blocks.
     * @return Number of blocks.
     */
    public int getNumOfBlocks() {
        return this.lsBlock.getNumBlocks().getValue();
    }
}
