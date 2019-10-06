/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.Utility;

import src.GameObjects.Character;
import src.Main.Constants;

/**
 *
 * This class helps to determine what entities need to be displayed on screen
 * the screen by keeping track of the borders of the screen
 *
 * @author Ralph McDougall 26/4/2018
 */
public class Camera {
    // *****************************************************
    // PRIVATE FIELDS
    // *****************************************************

    // The coordinate of the top-left corner of the camera
    private double worldX;
    private double worldY;

    // The world dimensions of the camera
    private double worldWidth;
    private double worldHeight;

    // *****************************************************
    // CONSTRUCTOR
    // *****************************************************
    
    /**
     * Initialise a new camera object to fit the dimensions of the screen
     * 
     * @param c The character that the camera should focus on
     */
    public Camera(Character c)
    {
        worldWidth = Constants.SCREEN_WIDTH / Constants.MAP_WIDTH + 1;
        worldHeight = Constants.SCREEN_HEIGHT / Constants.MAP_HEIGHT + 1;
        
        // Set the given character at the centre of the screen
        reallign(c);
    }
    
    /**
     * Initialise a new camera object that can determine which objects
     * in the game should be drawn onto the screen
     * 
     * @param c The character that the camera should focus on
     * @param wWidth The world width of the camera view
     * @param wHeight  The world height of the camera view
     */
    public Camera(Character c, double wWidth, double wHeight) {
        worldWidth = wWidth;
        worldHeight = wHeight;

        // Set the given character at the centre of the screen
        reallign(c);
    }

    // *****************************************************
    // PUBLIC METHODS
    // *****************************************************
    
    /**
     * Get the x-coordinate of the top-left corner of the camera
     * 
     * @return The x-coordinate of the top-left corner of the camera
     */
    public double getWorldX() {
        return worldX;
    }

    /**
     * Get the y-coordinate of the top-left corner of the camera
     * 
     * @return The y-coordinate of the top-left corner of the camera
     */
    public double getWorldY() {
        return worldY;
    }

    /**
     * Get the width of the camera view
     * 
     * @return The width of the camera view
     */
    public double getWorldWidth() {
        return worldWidth;
    }

    /**
     * Get the height of the camera view
     * 
     * @return The height of the camera view
     */
    public double getWorldHeight() {
        // Return the height of the camera
        return worldHeight;
    }
    
    /**
     * Reallign the camera view to center on the given character
     * 
     * @param c The given character to focus on
     */
    public void reallign(Character c) {
        worldX = c.getX() + 0.5 * c.getSize() - worldWidth / 2;
        worldY = c.getY() + 0.5 * c.getSize() - worldHeight / 2;
    }

    /**
     * Whether or not a given coordinate appears on the camera view
     * 
     * @param x The x-coordinate of the coordinate to check
     * @param y The y-coordinate of the coordinate to check
     * @return Whether or not the coordinate appears in the camera view
     */
    public boolean onScreen(int x, int y) {
        if (x >= worldX && x <= (worldX + worldWidth)) {
            // The coordinate lies within the vertical borders
            if (y >= worldY && y <= (worldY + worldHeight)) {
                // The coordinate lies within the horizontal borders
                // The coordinate is on the screen
                return true;
            }
        }
        // There is not evidence that the coordinate is on the screen
        return false;
    }
    
    /**
     * Convert a world x-coordinate into a screen coordinate using the camera's
     * current size and offset
     * 
     * @param x The world x-coordinate to convert
     * @return The screen x-coordinate
     */
    public float getScreenX(double x)
    {
        return (float) (x - worldX) * Constants.TILE_SCREEN_SIZE;
    }
    
    /**
     * Convert a world y-coordinate into a screen coordinate using the camera's
     * current size and offset
     * 
     * @param y The world y-coordinate to convert
     * @return The screen y-coordinate
     */
    public float getScreenY(double y)
    {
        return (float) (y - worldY) * Constants.TILE_SCREEN_SIZE;
    }

}
