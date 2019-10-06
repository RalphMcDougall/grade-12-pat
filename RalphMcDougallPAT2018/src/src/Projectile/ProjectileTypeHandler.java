/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.Projectile;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import src.Utility.DBBridge;
import src.Main.GameHandler;
import src.Utility.GameLogger;

/**
 * Loads all projectile types from the database and allows them to be 
 * retrieved at any time.
 * 
 * @author Ralph McDougall 16/6/2018
 */
public class ProjectileTypeHandler {
    
    // *****************************************************
    // PRIVATE FIELDS
    // *****************************************************
    
    // The array of all possible projectile types
    private static ProjectileType types[];
    
    // The number of projectile types available
    private static int numProjectileTypes = 0;
    
    // *****************************************************
    // PUBLIC METHODS
    // *****************************************************
    
    /**
     * Load the projectile types from the database
     * 
     * @throws SQLException SQL could not load a projectile type
     */
    public static void load() throws SQLException
    {
        GameLogger.logInfo("Loading projectile types from database...");
        
        // Get the projectile type table from the database and process it
        DBBridge db = GameHandler.getPersistentDB();
        ResultSet rs = db.query("SELECT ProjectileTypeID, ProjectileTypeName, SpriteSheetX, SpriteSheetY, Range FROM tblProjectileTypes");
        ArrayList< ArrayList< String > > res = DBBridge.processResultSet(rs);
        
        // Initialise the array of types
        numProjectileTypes = res.size();
        types = new ProjectileType[numProjectileTypes];
        
        // Fill up the array
        for (int i = 0; i < res.size(); ++i)
        {
            ArrayList< String > row = res.get(i);
            
            int id = Integer.parseInt(row.get(0));
            String description = row.get(1);
            int spriteSheetX = Integer.parseInt(row.get(2));
            int spriteSheetY = Integer.parseInt(row.get(3));
            int range = Integer.parseInt(row.get(4));
            types[i] = new ProjectileType(id,
                        description,
                        spriteSheetX,
                        spriteSheetY,
                        range);
        }
        
        GameLogger.logInfo("Projectile types loaded successfully.");
    }
    
    /**
     * Get a projectile type with a given ID
     * 
     * @param id The ID of the projectile type to get
     * @return The projectile type with the given ID
     */
    public static ProjectileType getProjectileType(int id)
    {
        if (id > numProjectileTypes || id <= 0)
        {
            GameLogger.logError("Invalid projectile type: " + id);
            return null;
        }
        return types[id - 1];
    }
}
