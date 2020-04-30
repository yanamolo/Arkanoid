package animation;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import game.Menu;
import sprite.BackgroundImage;

/**
 * The menu animation of the game.
 *
 * @author Yana Molodetsky
 *
 * @param <T> The object that should run.
 */
public class MenuAnimation<T> implements Menu<T> {
    private String title;
    private List<T> returnVal;
    private List<String> itemNames;
    private List<String> itemKeys;
    private KeyboardSensor sensor;
    private boolean stop;
    private T status;
    private BackgroundImage backImage;
    private List<Boolean> isSub;
    private List<Menu<T>> subList;
    private AnimationRunner run;

    /**
     * Constructor.
     *
     * @param title          The title that should be presented.
     * @param keyboardSensor The keyboard.
     * @param run            The animation runner that will run the program.
     */
    public MenuAnimation(String title, KeyboardSensor keyboardSensor, AnimationRunner run) {
        this.run = run;
        this.sensor = keyboardSensor;
        this.title = title;
        this.stop = false;
        this.returnVal = new ArrayList<T>();
        this.itemKeys = new ArrayList<String>();
        this.itemNames = new ArrayList<String>();
        this.status = null;
        this.backImage = new BackgroundImage("background_images/pink.jpg", 0, 0);
        this.isSub = new ArrayList<>();
        this.subList = new ArrayList<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        int moveY = 0;
        this.backImage.drawOn(d);
        d.setColor(Color.PINK.darker());
        d.drawText(290, 80, this.title, 40);
        for (int i = 0; i < this.returnVal.size(); i++) {
            d.setColor(Color.BLACK);
            String toPrint = "(" + this.itemKeys.get(i) + ") " + this.itemNames.get(i);
            d.drawText(40, 200 + moveY, toPrint, 32);
            moveY = moveY + 50;
        }
        for (int i = 0; i < this.itemKeys.size(); i++) {
            if (this.sensor.isPressed(this.itemKeys.get(i))) {
                if (this.isSub.get(i)) {
                    this.run.run(this.subList.get(i));
                    this.status = this.subList.get(i).getStatus();
                    this.subList.get(i).reset();
                } else {
                    this.status = this.returnVal.get(i);
                }
                this.stop = true;
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addSelection(String key, String message, T returnV) {
        this.itemKeys.add(key);
        this.itemNames.add(message);
        this.returnVal.add(returnV);
        this.isSub.add(false);
        this.subList.add(null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T getStatus() {
        return this.status;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void reset() {
        this.status = null;
        this.stop = false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addSubMenu(String key, String message, Menu<T> subMenu) {
        this.returnVal.add(null);
        this.itemKeys.add(key);
        this.itemNames.add(message);
        this.isSub.add(true);
        this.subList.add(subMenu);
    }
}
