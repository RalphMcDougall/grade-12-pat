/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.Tile;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import src.Utility.DBBridge;
import src.Main.GameHandler;
import src.Utility.GameLogger;

/**
 * Loads all of the tile types and allows them to be retrieved at any time
 * 
 * @author Ralph McDougall 26/4/2018
 */
public class TileTypeHandler {
    
    // *****************************************************
    // PRIVATE FIELDS
    // *****************************************************
    
    // The number of tile types
    private static int numTileTypes;
    
    // The array of all tile types in the game
    private static TileType tileTypes[];
    
    // *****************************************************
    // PUBLIC METHODS
    // *****************************************************
    
    /**
     * Load the tile types from the database
     * 
     * @throws SQLException SQL couldn't load the tile types
     */
    public static void load() throws SQLException
    {
        GameLogger.logInfo("Load tile types from database...");
        
        // Get the database where all of the persistent data is stored
        DBBridge db = GameHandler.getPersistentDB();
        
        // Get the data from the tile table
        ResultSet rs = db.query("SELECT TileID, TileName, SpriteSheetX, SpriteSheetY, Walkable "
                + "FROM tblTileTypes;");
        
        ArrayList< ArrayList< String > > res = DBBridge.processResultSet(rs);
        
        // The number of different tile types
        numTileTypes = res.size();
        
        // Initialise the array of tile types
        tileTypes = new TileType[numTileTypes];
        
        // Fill up the array with the tile types
        for (int i = 0; i < numTileTypes; ++i)
        {
            rs.next();
            int id = Integer.parseInt(res.get(i).get(0));
            String name = res.get(i).get(1);
            int spriteSheetX = Integer.parseInt(res.get(i).get(2));
            int spriteSheetY = Integer.parseInt(res.get(i).get(3));
            boolean canWalk = Boolean.parseBoolean(res.get(i).get(4));
            
            tileTypes[i] = new TileType(id,
                    name,
                    spriteSheetX,
                    spriteSheetY,
                    canWalk );
        }
        
        GameLogger.logInfo("Tile types loaded successfully.");
    }
    
    /**
     * Get the tile type that has a given ID
     * 
     * @param id The ID of the tile type to find
     * @return The TileType with the given ID
     */
    public static TileType getTileType(int id)
    {
        if (id > numTileTypes || id <= 0)
        {
            // The ID of the tile type is higher than the number of tile types
            GameLogger.logError("Unknown tile ID: " + id);
            return null;
        }
        return tileTypes[id - 1];
    }
    
}
