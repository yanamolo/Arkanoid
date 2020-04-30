package levels;

import java.io.File;
import java.io.IOException;
import java.util.List;

import animation.Animation;
import animation.AnimationRunner;
import animation.HighScoresAnimation;
import animation.KeyPressStoppableAnimation;
import animation.LoseScreen;
import animation.WinScreen;
import biuoop.DialogManager;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;
import game.Counter;
import game.GameLevel;
import game.HighScoresTable;
import game.ScoreInfo;
import game.ScoreTrackingListener;

/**
 * This class in charge of the flow of the levels in the game.
 *
 * @author Yana Molodetsky
 *
 */
public class GameFlow {
    private AnimationRunner ar;
    private KeyboardSensor ks;
    private HighScoresTable table;
    private File file;

    /**
     * Constructor.
     *
     * @param ar The animation runner, to run the levels.
     * @param ks The keyboard parameter.
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks) {
        this.ar = ar;
        this.ks = ks;
        this.file = new File("highscores.ser");
        try {
            this.table = HighScoresTable.loadFromFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The method runs the levels one after the other.
     *
     * @param levels The list of the levels in the game.
     * @throws IOException If there is a problem with the high scores file.
     */
    public void runLevels(List<LevelInformation> levels) throws IOException {
        ScoreTrackingListener score = new ScoreTrackingListener(new Counter(0));
        Counter numOfLives = new Counter(7);
        GameLevel level = null;
        Sleeper sleep = new Sleeper();
        int i = 0;
        for (i = 0; i < levels.size(); i++) {
            level = new GameLevel(levels.get(i), ks, ar, numOfLives, score);
            level.initialize();
            // Running the level.
            while (level.getNumOfLives() > 0 && level.getNumOfBlocks() > 0) {
                level.playOneTurn();
            }

            if (level.getNumOfLives() == 0) {
                Animation a1 = new LoseScreen(level.getCurrentScore());
                Animation ak1 = new KeyPressStoppableAnimation(ks, KeyboardSensor.SPACE_KEY, a1);
                ar.run(ak1);
                i--;
                break;
            }
        }
        // The player has won the game.
        if (level.getNumOfLives() > 0) {
            Animation a2 = new WinScreen(level.getCurrentScore());
            Animation ak2 = new KeyPressStoppableAnimation(ks, KeyboardSensor.SPACE_KEY, a2);
            ar.run(ak2);
        }
        // Checking if the score is high enough to get into the table.
        if (table.getRank(level.getCurrentScore().getValue()) < 7) {
            DialogManager dialog = ar.getGUI().getDialogManager();
            String name = dialog.showQuestionDialog("Name", "What is your name?", "");
            ScoreInfo scoreOfGame = new ScoreInfo(name, level.getCurrentScore().getValue());
            table.add(scoreOfGame);
            try {
                table.save(file);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        sleep.sleepFor(500);
        runTable();
    }

    /**
     * Running the animation of the table.
     */
    public void runTable() {
        Animation scoreAni = new HighScoresAnimation(table);
        Animation scoreAniKeyboard = new KeyPressStoppableAnimation(ks, KeyboardSensor.SPACE_KEY, scoreAni);
        ar.run(scoreAniKeyboard);
    }
}
