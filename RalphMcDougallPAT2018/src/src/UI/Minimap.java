/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.UI;

import java.util.ArrayList;
import java.util.HashMap;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import src.Main.Constants;
import src.Map.Map;
import src.Utility.GameLogger;

/**
 * The minimap that appears on top right of the screen where the player can see
 * where they've already been and items that were found
 *
 * @author Ralph McDougall 11/8/2018
 */
public class Minimap {
    
    // *****************************************************
    // PRIVATE FIELDS
    // *****************************************************
    
    // The types of tile segments
    private static enum GRID_SEGMENT_TYPES {
        UNKNOWN, WALKABLE, WALL, ITEM, QUEST, EXIT
    };
    
    // The color to draw where the player is on the map
    private final Color PLAYER_HIGHLIGHT_COLOUR = Color.green;
    
    // The size, in pixels, of each block for a tile segment on the minimap
    private final int MINIMAP_BLOCK_SIZE = 5;
    
    // Where the top-left corner of the minimap should appear on the screen
    private final int Y_OFFSET = 0;
    private final int X_OFFSET = Constants.SCREEN_WIDTH - Constants.MAP_WIDTH * MINIMAP_BLOCK_SIZE;

    // The colour to make each tile segment type on the minimap
    private HashMap<GRID_SEGMENT_TYPES, Color> SEGMENT_TYPE_COLOURS;
    
    // The grid that stores what tile segment type each discovered segment is
    private GRID_SEGMENT_TYPES grid[][];

    // The current coordinates of the player
    private int playerX = 0;
    private int playerY = 0;
    
    // THe map to base the minimap off of
    private Map baseMap;
    
    // *****************************************************
    // CONSTRUCTOR
    // *****************************************************
    
    /**
     * Initialise a new minimap to be based on a given map
     * 
     * @param map The map to base the minimap off of
     */
    public Minimap(Map map) {
        GameLogger.logInfo("Creating new minimap.");
        
        // Initialise the fields
        grid = new GRID_SEGMENT_TYPES[Constants.MAP_HEIGHT][Constants.MAP_WIDTH];
        baseMap = map;
        
        initialiseSegmentTypeColours();

        fillGrid();
        findPOIs();
        GameLogger.logInfo("Minimap created successfully.");
    }
    
    // *****************************************************
    // PRIVATE METHODS
    // *****************************************************
    
    /**
     * Set the colours that each tile segment type should be shown on the minimap
     */
    private void initialiseSegmentTypeColours() {
        GameLogger.logInfo("Setting up grid segment type colours.");
        SEGMENT_TYPE_COLOURS = new HashMap<>();

        SEGMENT_TYPE_COLOURS.put(GRID_SEGMENT_TYPES.UNKNOWN, Color.gray);
        SEGMENT_TYPE_COLOURS.put(GRID_SEGMENT_TYPES.WALKABLE, Color.white);
        SEGMENT_TYPE_COLOURS.put(GRID_SEGMENT_TYPES.WALL, Color.black);
        SEGMENT_TYPE_COLOURS.put(GRID_SEGMENT_TYPES.ITEM, Color.yellow);
        SEGMENT_TYPE_COLOURS.put(GRID_SEGMENT_TYPES.QUEST, Color.blue);
        SEGMENT_TYPE_COLOURS.put(GRID_SEGMENT_TYPES.EXIT, Color.red);
    }

    /**
     * Make all of the tile segments in the grid unknown types as they have
     * not been explored yet.
     */
    private void fillGrid() {
        GameLogger.logInfo("Filling grid with unknown types.");

        for (int y = 0; y < Constants.MAP_HEIGHT; ++y) {
            for (int x = 0; x < Constants.MAP_WIDTH; ++x) {
                grid[y][x] = GRID_SEGMENT_TYPES.UNKNOWN;
            }
        }
    }
    
    /**
     * Find the Points Of Interest on the map so that they can be revealed on
     * the minimap so that the player knows where they need to work towards
     */
    private void findPOIs() {
        GameLogger.logInfo("Finding Points-of-Interest on map");

        ArrayList<Integer> itemCoord = baseMap.getItemCoord();
        ArrayList<Integer> questCoord = baseMap.getNPCCoord();
        ArrayList<Integer> exitCoord = baseMap.getEndCoord();
        
        // Set the appropriate coordinates on the grid to their corresponding types
        grid[itemCoord.get(1) / Constants.TILE_SEGMENT_WIDTH][itemCoord.get(0) / Constants.TILE_SEGMENT_WIDTH] = GRID_SEGMENT_TYPES.ITEM;
        grid[questCoord.get(1) / Constants.TILE_SEGMENT_WIDTH][questCoord.get(0) / Constants.TILE_SEGMENT_WIDTH] = GRID_SEGMENT_TYPES.QUEST;
        grid[exitCoord.get(1) / Constants.TILE_SEGMENT_WIDTH][exitCoord.get(0) / Constants.TILE_SEGMENT_WIDTH] = GRID_SEGMENT_TYPES.EXIT;
    }
    
    /**
     * Check the types of the tile segments directly surrounding where the player
     * is located. This allows the map to update as the player explores the map
     */
    private void updateMinimap() {
        for (int y = playerY - 1; y <= playerY + 1; ++y) {
            for (int x = playerX - 1; x <= playerX + 1; ++x) {
                // Only check the coordinates directly surrounding the player
                
                if (y < 0 || x < 0) {
                    // This coordinate is off the map
                    continue;
                }
                if (y >= Constants.MAP_HEIGHT || x >= Constants.MAP_WIDTH) {
                    // This coordinte is off the map
                    continue;
                }

                if (grid[y][x] == GRID_SEGMENT_TYPES.UNKNOWN) {
                    // An un-explored tile segment was found so set the type
                    // of segment to the corresponding value on the map
                    if (baseMap.getTileWalkable(x * Constants.TILE_SEGMENT_WIDTH, y * Constants.TILE_SEGMENT_WIDTH)) {
                        // This is a walkable tile segment
                        grid[y][x] = GRID_SEGMENT_TYPES.WALKABLE;
                    } else {
                        // This is a non-walkable tile segment
                        grid[y][x] = GRID_SEGMENT_TYPES.WALL;
                    }
                }
            }
        }
    }
    
    // *****************************************************
    // PUBLIC METHODS
    // *****************************************************
    
    /**
     * Set the current coordinate of the player in the tile segment grid
     * 
     * @param x The x-coordinate of the player in the world
     * @param y The y-coordinate of the player in the world
     */
    public void setPlayerCoords(double x, double y) {
        playerX = (int) x / Constants.TILE_SEGMENT_WIDTH;
        playerY = (int) y / Constants.TILE_SEGMENT_WIDTH;
        
        // Explore the surrounding tiles now that we have a new player coordinate
        updateMinimap();
    }

    /**
     * Draw the minimap onto the screen
     * 
     * @param grphcs The Graphics object to draw the minimap onto
     */
    public void draw(Graphics grphcs) {
        for (int y = 0; y < Constants.MAP_HEIGHT; ++y) {
            for (int x = 0; x < Constants.MAP_WIDTH; ++x) {
                // For each coordinate in the grid, set the colour to the
                // corresponding coordinate of the tile segment type
                
                if (x == playerX && y == playerY) {
                    // The player position gets a special colour
                    grphcs.setColor(PLAYER_HIGHLIGHT_COLOUR);
                } else {
                    grphcs.setColor(SEGMENT_TYPE_COLOURS.get(grid[y][x]));
                }

                grphcs.fillRect(x * MINIMAP_BLOCK_SIZE + X_OFFSET, y * MINIMAP_BLOCK_SIZE + Y_OFFSET, MINIMAP_BLOCK_SIZE, MINIMAP_BLOCK_SIZE);
            }
        }
    }
}
