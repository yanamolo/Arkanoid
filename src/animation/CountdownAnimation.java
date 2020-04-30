package animation;

import java.awt.Color;

import biuoop.DrawSurface;
import biuoop.Sleeper;
import sprite.SpriteCollection;

/**
 * This class creates an animation that is the count down screen.
 *
 * @author Yana Molodetsky
 *
 */
public class CountdownAnimation implements Animation {
    private long numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;

    /**
     * Constructor.
     *
     * @param numOfSeconds The number of seconds the number should appear on the
     *                     screen.
     * @param countFrom    The number that should start the count down.
     * @param gameScreen   The sprite collection of the current level.
     */
    public CountdownAnimation(long numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.numOfSeconds = numOfSeconds;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        Sleeper sleeper = new Sleeper();
        this.gameScreen.drawAllOn(d); // Drawing the level, so it will be appeard when the count down starts.
        d.setColor(Color.BLACK);
        d.fillCircle(d.getWidth() / 2, d.getHeight() / 2 - 10, 30);
        d.setColor(Color.WHITE);
        d.drawText(d.getWidth() / 2, d.getHeight() / 2, Integer.toString(this.countFrom), 40);
        if (this.countFrom != 3) {
            sleeper.sleepFor(this.numOfSeconds * 500);
        }
        this.countFrom--;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean shouldStop() {
        if (this.countFrom == -1) {
            return true;
        }
        return false;
    }

}
