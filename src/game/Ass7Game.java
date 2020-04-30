package game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;
import animation.AnimationRunner;
import animation.MenuAnimation;
import levels.LevelSpecificationReader;
import levels.GameFlow;
import levels.LevelInformation;

/**
 * The class that runs the game.
 *
 * @author Yana Molodetsky
 *
 */
public class Ass7Game {

    /**
     * The main method of the program, that creates the game.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        List<String> keySub = new ArrayList<String>();
        List<String> messageSub = new ArrayList<String>();
        List<String> pathSub = new ArrayList<String>();
        AnimationRunner ar = new AnimationRunner();
        biuoop.KeyboardSensor keyboard = ar.getGUI().getKeyboardSensor();
        LevelSpecificationReader lev = new LevelSpecificationReader();
        Menu<Task<Void>> menu = new MenuAnimation<Task<Void>>("Arkanoid", keyboard, ar);
        // Creating three lists of string from the level set file.
        readFromFileSets(keySub, messageSub, pathSub, args);
        Menu<Task<Void>> subMenu = new MenuAnimation<Task<Void>>("Select levels", keyboard, ar);
        GameFlow game = new GameFlow(ar, keyboard);
        // Adding the selections for the sub menu.
        for (int i = 0; i < keySub.size(); i++) {
            String p = pathSub.get(i).trim();
            subMenu.addSelection(keySub.get(i), messageSub.get(i), new Task<Void>() {

                @Override
                public Void run() {
                    List<LevelInformation> levels = new ArrayList<LevelInformation>();
                    InputStream res = ClassLoader.getSystemClassLoader().getResourceAsStream(p);
                    BufferedReader buff = new BufferedReader(new InputStreamReader(res));
                    levels = lev.fromReader(buff);
                    try {
                        game.runLevels(levels);
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    return null;
                }

            });
        }
        // Adding the selections for the main menu.
        menu.addSelection("h", "High Scores", new Task<Void>() {

            @Override
            public Void run() {
                game.runTable();
                return null;
            }

        });
        menu.addSelection("q", "Quit", new Task<Void>() {

            @Override
            public Void run() {
                System.exit(0);
                return null;
            }

        });
        menu.addSubMenu("s", "Start game", subMenu);
        // Running the menu animation.
        while (true) {
            ar.run(menu);
            Task<Void> task = menu.getStatus();
            task.run();
            menu.reset();
        }
    }

    /**
     * This method reads from the level-set file, and places the lines in lists.
     *
     * @param keySub     The list of the key selection.
     * @param messageSub The list of the messages.
     * @param pathSub    The list of the paths of each selection.
     * @param args       The command line arguments.
     */
    public static void readFromFileSets(List<String> keySub, List<String> messageSub, List<String> pathSub,
            String[] args) {
        InputStream res = null;
        // Checking if the path to the file is given to us in command-line arguments.
        if (args.length == 0) {
            res = ClassLoader.getSystemClassLoader().getResourceAsStream("level_sets.txt");
        } else {
            res = ClassLoader.getSystemClassLoader().getResourceAsStream(args[0]);
        }
        LineNumberReader read = new LineNumberReader(new InputStreamReader(res));
        String line;
        try {
            while ((line = read.readLine()) != null) {
                int numLine = read.getLineNumber();
                if ((numLine % 2) == 0) {
                    pathSub.add(line);
                } else {
                    String[] temp = line.split(":");
                    keySub.add(temp[0]);
                    messageSub.add(temp[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
