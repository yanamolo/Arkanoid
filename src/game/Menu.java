package game;

import animation.Animation;

/**
 * The interface of the menu.
 *
 * @author Yana Molodetsky
 *
 * @param <T> The object the menu receives.
 */
public interface Menu<T> extends Animation {
    /**
     * Adding new selection to the lists.
     *
     * @param key       The key that is pressed.
     * @param message   The message that should be shown.
     * @param returnVal The returning value.
     */
    void addSelection(String key, String message, T returnVal);

    /**
     * Getter for the status.
     *
     * @return The status.
     */
    T getStatus();

    /**
     * Reseting the menu for the animation loop.
     */
    void reset();

    /**
     * Adding a new sub menu.
     *
     * @param key     The key that is pressed.
     * @param message message The message that should be shown.
     * @param subMenu The sub menu that is shown.
     */
    void addSubMenu(String key, String message, Menu<T> subMenu);
}
