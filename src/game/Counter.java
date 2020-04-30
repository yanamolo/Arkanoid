package game;

/**
 * This class is the counter of objects in the game.
 *
 * @author Yana Molodetsky
 *
 */
public class Counter {
    private int num;

    /**
     * Constructor of the game.
     *
     * @param numBlock The number of objects at the beginning.
     */
    public Counter(int numBlock) {
        this.num = numBlock;
    }

    /**
     * Increasing the current number in the counter.
     *
     * @param number The number that is added to the current count.
     */
    public void increase(int number) {
        this.num = this.num + number;
    }

    /**
     * Decreasing the current number in the counter.
     *
     * @param number The number that is decreased from the current count.
     */
    public void decrease(int number) {
        this.num = this.num - number;
    }

    /**
     * Getter for the current count.
     *
     * @return The current count.
     */
    public int getValue() {
        return this.num;
    }
}
