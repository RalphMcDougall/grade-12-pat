/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.Utility;

import java.util.ArrayList;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * This class loads the sprite sheet and allows you to get the image at a given
 * position on the sprite sheet
 *
 * @author Ralph McDougall 24/4/2018
 */
public class SpriteSheet {
    // *****************************************************
    // PRIVATE FIELDS
    // *****************************************************

    // The sprite sheet image
    private Image sheet;

    // The strings of the image path
    private final String path = "resources/";
    private String fileName = "";
    private final String extension = ".png";

    // The size of each segment that the sprite sheet gets splits up into
    private int imageSize;

    // Each sprite in the sprite sheet grid
    private ArrayList< ArrayList<Image>> images;

    // *****************************************************
    // CONSTRUCTORS
    // *****************************************************
    
    /**
     * The constructor for the sprite sheet
     *
     * @param sheetName The file name of the sprite sheet
     * @param splitSize The size of each sub-image of the sprite sheet
     * @throws SlickException
     */
    public SpriteSheet(String sheetName, int splitSize) throws SlickException {
        GameLogger.logInfo("Initialising sprite sheet: " + sheetName);
        fileName = sheetName;

        GameLogger.logInfo("Loading image.");
        sheet = new Image(path + fileName + extension);
        images = new ArrayList< ArrayList< Image>>();

        imageSize = splitSize;

        splitSpriteSheet();

        GameLogger.logInfo("Sprite sheet initialised successfully.");
    }

    // *****************************************************
    // PRIVATE METHODS
    // *****************************************************
    
    /**
     * Split the sprite sheet image up into the constituent sub-images
     */
    private void splitSpriteSheet() {
        GameLogger.logInfo("Starting to split sprite sheet.");
        for (int y = 0; y < sheet.getHeight() / imageSize; ++y) {
            // A new row of the sprite sheet
            ArrayList<Image> row = new ArrayList<Image>();
            for (int x = 0; x < sheet.getWidth() / imageSize; ++x) {
                // Add the corresponding sub-image to the row
                Image subImg = sheet.getSubImage(x * imageSize, y * imageSize,
                        imageSize, imageSize);
                row.add(subImg);
            }
            // Add the row to the sprite sheet
            images.add(row);
        }
        GameLogger.logInfo("Sprite sheet splitting completed.");
    }

    // *****************************************************
    // PUBLIC METHODS
    // *****************************************************
    
    /**
     * Get the sub-image corresponding to the location (x, y) on the grid
     *
     * @param x The x-value of the image on the sprite sheet
     * @param y The y-value of the image on the sprite sheet
     * @return
     */
    public Image getImage(int x, int y) {
        return images.get(y).get(x);
    }
}
