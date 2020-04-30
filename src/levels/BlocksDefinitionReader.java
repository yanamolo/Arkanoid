package levels;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
/**
 * The class that read the file of the blocks.
 * @author Yana Molodetsky
 *
 */

public class BlocksDefinitionReader {
    /**
     * Reading from the file.
     * @param reader The reader of the file.
     * @return Blocks factory.
     */
    public static BlocksFromSymbolsFactory fromReader(java.io.Reader reader) {
        BufferedReader br = new BufferedReader(reader);
        Map<String, Integer> spacerWidths = new TreeMap<>();
        Map<String, BlockCreator> blockCreators = new TreeMap<>();
        String[] def = null;
        Map<Integer, Color> color;
        Map<Integer, String> image;
        String[] splits;
        String[] intoWords = null;
        String stringToMap = null;
        int numberToMap = 0;
        try {
            String line;
            while ((line = br.readLine()) != null) {
                color = new HashMap<>();
                image = new HashMap<>();
                BlockCreateType blockCreate = new BlockCreateType();
                if (line.startsWith("#")) {
                    continue;
                }
                if (line.startsWith("default")) {
                    def = line.split(" ");
                }
                if (line.startsWith("sdef")) {
                    splits = line.split(" ");
                    for (int i = 0; i < splits.length; i++) {
                        intoWords = splits[i].split(":");
                        if (intoWords[0].equals("symbol")) {
                            stringToMap = intoWords[1];
                        }
                        if (intoWords[0].equals("width")) {
                            numberToMap = Integer.parseInt(intoWords[1]);
                        }
                    }
                    spacerWidths.put(stringToMap, numberToMap);
                }
                if (line.startsWith("bdef")) {
                    splits = line.split(" ");
                    String sym = splits[1].substring(7);
                    // Creating the block type.
                    if (def != null) {
                        updateBlock(def, blockCreate, color, image);
                    }
                    updateBlock(splits, blockCreate, color, image);
                    if (blockCreate.doesFull()) {
                        blockCreators.put(sym, blockCreate);
                    } else {
                        throw new Exception("Missig details");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new BlocksFromSymbolsFactory(spacerWidths, blockCreators);
    }

    /**
     * Updating the information into the block create type.
     * @param sp The string of default definitions or the line in the file.
     * @param block The block creating type.
     * @param color The list of colors.
     * @param image The list of images.
     */
    public static void updateBlock(String[] sp, BlockCreateType block, Map<Integer, Color> color,
            Map<Integer, String> image) {
        for (int i = 0; i < sp.length; i++) {
            String[] tempSp = sp[i].split(":");
            if (tempSp[0].equals("height")) {
                block.setHeight(Integer.parseInt(tempSp[1]));
                continue;
            }
            if (tempSp[0].equals("width")) {
                block.setWidth(Integer.parseInt(tempSp[1]));
                continue;
            }
            if (tempSp[0].equals("hit_points")) {
                block.setHitPoint(Integer.parseInt(tempSp[1]));
                continue;
            }
            if (tempSp[0].equals("stroke")) {
                String colorStroke = tempSp[1].substring(6, tempSp[1].length() - 1);
                block.setStroke(ColorsParser.colorFromString(colorStroke));
                continue;
            }
            if (tempSp[0].contains("fill")) {
                int num;
                String[] splitFill = tempSp[0].split("-");
                if (splitFill.length > 1) {
                    num = Integer.parseInt(splitFill[1]);
                } else {
                    num = 1;
                }
                if (tempSp[1].startsWith("color")) {
                    Color c = ColorsParser.colorFromString(tempSp[1].substring(6, tempSp[1].length() - 1));
                    color.put(num, c);
                } else {
                    String photo = tempSp[1].substring(6, tempSp[1].length() - 1);
                    image.put(num, photo);
                }
                continue;
            }
        }
        block.setListColor(color);
        block.setListImage(image);
    }
}
