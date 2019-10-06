/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.Main;

/**
 * This class stores all global constants that should be used throughout the
 * program
 *
 * @author Ralph McDougall 7/8/2018
 */
public class Constants {

    // *****************************************************
    // GLOBAL GAME CONSTANTS
    // *****************************************************
    
    // Directions that all classes should refer to to standardise direction
    public static enum DIRECTION {
        UP, DOWN, LEFT, RIGHT
    };

    // The width/height of each tile segment
    public static final int TILE_SEGMENT_WIDTH = 5;

    // The number of tile segments wide and high the map is
    public static final int MAP_WIDTH = 20;
    public static final int MAP_HEIGHT = 20;

    // The size of each tile on the screen in pixels
    public static final int TILE_SCREEN_SIZE = 32;

    // The window caption
    public static final String CAPTION = "DUNGEON SWARM";

    // The dimensions of the screen where the game takes place
    public static final int SCREEN_WIDTH = 800;
    public static final int SCREEN_HEIGHT = 600;
    
    // The factor to multiply the movement speed by to get the move distance
    public static final double SPEED_FACTOR = 1 / 32.0;
    
    // The maximum distance to do path-finding
    public static final int MAX_PATH_FINDING = 40;
}
