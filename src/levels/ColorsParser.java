package levels;

import java.awt.Color;
/**
 * This class creates a color from a string.
 * @author Yana Molodetsky
 *
 */
public class ColorsParser {
    /**
     * The class creates a color from a string.
     * @param s The string.
     * @return The new color.
     */
    public static java.awt.Color colorFromString(String s) {
        Color color;
        if (s.contains("RGB")) {
            String rgb = s.substring(4, s.length() - 1);
            String[] rgbSplit = rgb.split(",");
            color = new Color(Integer.parseInt(rgbSplit[0]), Integer.parseInt(rgbSplit[1]),
                    Integer.parseInt(rgbSplit[2]));
        } else {
            color = ColorsParser.findColor(s);
        }
        return color;
    }

    /**
     * This method turns the string to a new color.
     * @param s The string of the color.
     * @return The new color.
     */
    public static Color findColor(String s) {
        Color color = null;
        switch (s.toLowerCase()) {
        case "cyan":
            color = Color.CYAN;
            break;
        case "blue":
            color = Color.BLUE;
            break;
        case "black":
            color = Color.BLACK;
            break;
        case "darkgray":
            color = Color.DARK_GRAY;
            break;
        case "gray":
            color = Color.GRAY;
            break;
        case "green":
            color = Color.GREEN;
            break;
        case "yellow":
            color = Color.YELLOW;
            break;
        case "white":
            color = Color.WHITE;
            break;
        case "magneta":
            color = Color.MAGENTA;
            break;
        case "orange":
            color = Color.ORANGE;
            break;
        case "pink":
            color = Color.PINK;
            break;
        case "red":
            color = Color.RED;
            break;
        case "lightgray":
            color = Color.LIGHT_GRAY;
            break;
        default:
            color = Color.BLACK;
            break;
        }
        return color;
    }
}
