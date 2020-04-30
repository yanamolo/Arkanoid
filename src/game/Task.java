package game;

/**
 * The interface of the tasks in the game.
 * @author Yana Molodetsky
 *
 * @param <T>
 */
public interface Task<T> {
    /**
     * Running the task.
     * @return the object that is run.
     */
    T run();
}
