package game;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The class that reads the high-score table from the file, or creating new
 * file.
 *
 * @author Yana Molodetsky
 *
 */
public class HighScoresTable implements Serializable {
    private static final long serialVersionUID = 5079053688132909090L;
    private int size;
    private List<ScoreInfo> scoreList;

    /**
     * Create an empty high-scores table with the specified size.
     *
     * @param size The size means that the table holds up to size top scores.
     */
    public HighScoresTable(int size) {
        this.size = size;
        this.scoreList = new ArrayList<ScoreInfo>();
    }

    /**
     * Add a high-score.
     *
     * @param score The high-score that should be added.
     */
    public void add(ScoreInfo score) {
        if (this.getRank(score.getScore()) < this.size) {
            this.scoreList.add(score);
        }
    }

    /**
     * Getter for the size of the table.
     *
     * @return Return table size.
     */
    public int size() {
        return this.size;
    }

    /**
     * Getter for the high score list.
     *
     * @return Return the current high scores. The list is sorted such that the
     *         highest scores come first.
     */
    public List<ScoreInfo> getHighScores() {
        Collections.sort(this.scoreList);
        return this.scoreList;
    }

    /**
     * The method calculates and returns the rank of the score.
     *
     * @param score The score that is checked for its ranking.
     * @return return the rank of the current score: where will it be on the list if
     *         added?
     */
    public int getRank(int score) {
        Collections.sort(this.scoreList);
        for (int i = 0; i < 7; i++) {
            if (this.scoreList.get(i).getScore() <= score) {
                return i + 1;
            }
        }
        return this.size() + 1;
    }

    /**
     * Clears the table.
     */
    public void clear() {
        this.scoreList.clear();
    }

    /**
     * Load table data from file.
     *
     * @param filename The file that is used for the loading.
     * @throws IOException If the file does not exists or there is a problem loading
     *                     it.
     */
    public void load(File filename) throws IOException {
        this.clear();
        List<ScoreInfo> list = new ArrayList<ScoreInfo>();
        list = HighScoresTable.loadFromFile(filename).getHighScores();
        this.size = list.size();
        for (int i = 0; i < list.size(); i++) {
            this.scoreList.add(list.get(i));
        }
    }

    /**
     * Save table data to the specified file.
     *
     * @param filename The file that the data is saved into.
     * @throws IOException If faild saving the table into the file.
     */
    public void save(File filename) throws IOException {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(filename));
            objectOutputStream.writeObject(this);
            objectOutputStream.close();
        } catch (Exception e) {
            System.err.println("Failed saving object");
            e.printStackTrace(System.err);
        }
    }

    /**
     * Read a table from file and return it.
     *
     * @param filename The file that is used for the reading.
     * @return The high score table.
     * @throws IOException If there is a problem loading the file.
     */
    public static HighScoresTable loadFromFile(File filename) throws IOException {
        try {
            ObjectInputStream objectInput = new ObjectInputStream(new FileInputStream(filename));
            HighScoresTable scores = (HighScoresTable) objectInput.readObject();
            objectInput.close();
            return scores;
        } catch (Exception e) { // Can't find file to open
            System.err.println("Unable to find file");
            HighScoresTable table = new HighScoresTable(7);
            table.save(filename);
            return null;
        }
    }
}
