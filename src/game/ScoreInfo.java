package game;

import java.io.Serializable;
/**
 * The class that creates the scores for the table.
 * @author Yana Molodetsky
 *
 */
public class ScoreInfo implements Comparable<ScoreInfo>, Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private int score;

    /**
     * Constructor.
     * @param name The name of the player.
     * @param score His score.
     */
    public ScoreInfo(String name, int score) {
        this.name = name;
        this.score = score;
    }

    /**
     * Getter for the name.
     * @return The name of the player.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Getter for the score.
     * @return The score of the player.
     */
    public int getScore() {
        return this.score;
    }

    /**
     * Comparing the scores.
     * @param ob The score that is compared to the current score.
     * @return The integer of the place of the score.
     */
    @Override
    public int compareTo(ScoreInfo ob) {
        int compare = ob.getScore();
        return compare - this.score;
    }

    /**
     * Making a string of the current score info.
     * @return The string.
     */
    @Override
    public String toString() {
        return this.name + "     " + Integer.toString(this.score);
    }
}
