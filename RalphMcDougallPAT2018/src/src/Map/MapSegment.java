/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.Map;

import src.Main.Constants;
import src.Tile.TileTypeHandler;
import src.Utility.GameLogger;

/**
 * Stores the tile IDs of the tiles in the map tile segment.
 * 
 * @author Ralph McDougall 14/6/2018
 */
public class MapSegment {
    
    // *****************************************************
    // PRIVATE FIELDS
    // *****************************************************
    
    // Array of the IDs of the tile types at each location in the tile segment
    private int[][] tileIDs;
    
    // Whether or not this tile segment is a walkable segment
    private boolean walkable = true;
    
    // *****************************************************
    // CONSTRUCTOR
    // *****************************************************
    
    /**
     * Initialise a new map tile segment
     * 
     * @param ids The tile type IDs of the tiles in the tile segment
     */
    public MapSegment(int[][] ids)
    {
        tileIDs = new int[Constants.TILE_SEGMENT_WIDTH][Constants.TILE_SEGMENT_WIDTH];
        tileIDs = ids;
        
        determineWalkable();
    }
    
    // *****************************************************
    // PRIVATE METHODS
    // *****************************************************
    
    /**
     * Determine whether or not this map tile segment should be labeled as
     * walkable. If more than half of the tiles are walkable, the tile 
     * segment is walkable.
     */
    private void determineWalkable()
    {
        int walkableCount = 0;
        // Count the number of walkable tiles in this map tile segment
        for (int y = 0; y < Constants.TILE_SEGMENT_WIDTH; ++y)
        {
            for (int x = 0; x < Constants.TILE_SEGMENT_WIDTH; ++x)
            {
                if ( TileTypeHandler.getTileType(tileIDs[y][x]).walkable() )
                {
                    ++walkableCount;
                }
            }
        }
        walkable = walkableCount > (Constants.TILE_SEGMENT_WIDTH * Constants.TILE_SEGMENT_WIDTH) / 2;
    }
    
    // *****************************************************
    // PUBLIC METHODS
    // *****************************************************
    
    /**
     * Get whether or not the map tile segment is walkable
     * 
     * @return Whether or not the map tile segment is walkable
     */
    public boolean getWalkable()
    {
        return walkable;
    }
    
    /**
     * Get the tile ID of the tile at a given coordinate in the map tile segment
     * 
     * @param x The x-coordinate of the tile type to find
     * @param y The y-coordinate of the tile type to find
     * @return The tile type of the tile at that coordinate
     */
    public int getTileID(int x, int y)
    {
        if (x < 0 || y < 0)
        {
            GameLogger.logError("Invalid tile coordinate: " + x + " " + y);
            return 0;
        }
        if (x >= Constants.TILE_SEGMENT_WIDTH || y >= Constants.TILE_SEGMENT_WIDTH)
        {
            GameLogger.logError("Invalid tile coordinate: " + x + " " + y);
            return 0;
        }
        
        return tileIDs[y][x];
    }
    
}
