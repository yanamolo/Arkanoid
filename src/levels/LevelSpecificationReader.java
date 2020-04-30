package levels;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import collidable.Block;
import sprite.Velocity;

import java.io.Reader;

/**
 * Reading the information about the levels from the file.
 *
 * @author Yana Molodetsky
 *
 */
public class LevelSpecificationReader {

    /**
     * The method that read the file.
     *
     * @param reader The file reader.
     * @return List of levels.
     */
    public List<LevelInformation> fromReader(Reader reader) {
        List<LevelInformation> listLevel = new ArrayList<LevelInformation>();
        BufferedReader br = new BufferedReader(reader);
        String[] tempString = null;
        BlocksFromSymbolsFactory bl = null;
        try {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.equals("START_LEVEL")) {
                    // Creating new level type and setting its values.
                    LevelCreateType lv = new LevelCreateType();
                    while (!line.equals("END_LEVEL")) {
                        line = br.readLine();
                        tempString = line.split(":");
                        if (line.startsWith("level_name")) {
                            lv.setLevelName(tempString[1].trim());
                        }
                        if (line.startsWith("ball_velocities")) {
                            List<Velocity> velocity = new ArrayList<>();
                            String[] velocities = tempString[1].split(" ");
                            for (int i = 0; i < velocities.length; i++) {
                                String[] temp = velocities[i].split(",");
                                Velocity v = Velocity.fromAngleAndSpeed(Double.parseDouble(temp[0].trim()),
                                        Double.parseDouble(temp[1]));
                                velocity.add(v);
                            }
                            lv.setVelocities(velocity);
                        }
                        if (line.startsWith("background")) {
                            if (tempString[1].contains("color")) {
                                Color color = ColorsParser
                                        .colorFromString(tempString[1].substring(6, tempString[1].length() - 1).trim());
                                lv.setBackColor(color);
                            } else if (tempString[1].contains("image")) {
                                lv.setBackImage(tempString[1].substring(6, tempString[1].length() - 1).trim());
                            }
                        }
                        if (line.startsWith("paddle_speed")) {
                            lv.setPaddleSpeed(Integer.parseInt(tempString[1].trim()));
                        }
                        if (line.startsWith("paddle_width")) {
                            lv.setPaddleWidth(Integer.parseInt(tempString[1].trim()));
                        }
                        if (line.startsWith("block_definitions")) {
                            InputStream re = ClassLoader.getSystemClassLoader()
                                    .getResourceAsStream(tempString[1].trim());
                            BufferedReader buff = new BufferedReader(new InputStreamReader(re));
                            bl = BlocksDefinitionReader.fromReader(buff);
                        }
                        if (line.startsWith("blocks_start_x")) {
                            lv.setStartX(Integer.parseInt(tempString[1].trim()));
                        }
                        if (line.startsWith("blocks_start_y")) {
                            lv.setStartY(Integer.parseInt(tempString[1].trim()));
                        }
                        if (line.startsWith("row_height")) {
                            lv.setRowHeight(Integer.parseInt(tempString[1].trim()));
                        }
                        if (line.startsWith("num_blocks")) {
                            lv.setNumOfBlocks((Integer.parseInt(tempString[1].trim())));
                        }
                        if (line.startsWith("START_BLOCKS")) {
                            List<Block> list = readBlocksFromFile(lv, br, bl);
                            lv.setBlockList(list);
                        }
                    }
                    if (!lv.isEmpty()) {
                        listLevel.add(lv);
                    } else {
                        throw new Exception("Missing details");
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return listLevel;
    }

    /**
     * Reading the blocks from the file.
     *
     * @param lv The level creating type.
     * @param br The buffer reader.
     * @param bl The blocks factory.
     * @return The block list.
     */
    public List<Block> readBlocksFromFile(LevelCreateType lv, BufferedReader br, BlocksFromSymbolsFactory bl) {
        String sub;
        String line;
        try {
            line = br.readLine();
            List<Block> blocks = new ArrayList<>();
            Block b;
            int x = lv.getStartX();
            int y = lv.getStartY();
            while (!line.equals("END_BLOCKS")) {
                for (int i = 0; i < line.length(); i++) {
                    sub = line.substring(i, i + 1).trim();
                    if (bl.isSpaceSymbol(sub)) {
                        x = x + bl.getSpaceWidth(sub);
                    } else if (bl.isBlockSymbol(sub)) { // blockSymbol
                        b = bl.getBlock(sub, x, y);
                        blocks.add(b);
                        x = (int) (x + b.getCollisionRectangle().getWidth());
                    }
                }
                y = y + lv.getRowHeight();
                x = lv.getStartX();
                line = br.readLine();
            }
            return blocks;
        } catch (IOException e1) {
            e1.printStackTrace();
            return null;
        }
    }
}
