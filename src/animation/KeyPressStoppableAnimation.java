package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * The class that wraps all the stoppable animations.
 *
 * @author Yana Molodetsky
 *
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor sensor;
    private String key;
    private Animation animation;
    private boolean isAlreadyPressed;

    /**
     * Constructor.
     *
     * @param sensor    The keyboard.
     * @param key       The pressed key.
     * @param animation The current animation.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.animation = animation;
        this.key = key;
        this.sensor = sensor;
        this.isAlreadyPressed = true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        animation.doOneFrame(d);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean shouldStop() {
        if (this.sensor.isPressed(key) && !(this.isAlreadyPressed)) {
           return true;
        } else if (this.sensor.isPressed(key) && this.isAlreadyPressed) {
            this.isAlreadyPressed = false;
        }
        return false;
    }

}
