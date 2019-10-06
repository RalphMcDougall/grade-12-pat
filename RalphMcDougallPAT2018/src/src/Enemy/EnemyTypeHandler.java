/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.Enemy;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import src.Utility.DBBridge;
import src.Main.GameHandler;
import src.Utility.GameLogger;
import src.Utility.Tools;

/**
 * Stores all enemy types so that they can be retrieved when a new enemy is instantiated
 * 
 * @author Ralph McDougall 22/7/2018
 */
public class EnemyTypeHandler {
    // *****************************************************
    // PRIVATE FIELDS
    // *****************************************************
    
    // List of all enemy types
    private static ArrayList<EnemyType> types;
    
    // *****************************************************
    // PUBLIC METHODS
    // *****************************************************
    
    /**
     * Load the enemy types from the database
     * 
     * @throws SQLException SQL couldn't load the enemy types from the database
     */
    public static void load() throws SQLException
    {
        GameLogger.logInfo("Loading enemy types from database...");
        
        types = new ArrayList<>();
        
        // Get the enemy types from the database
        DBBridge db = GameHandler.getPersistentDB();
        ResultSet rs = db.query("SELECT EnemyName, SpriteSheetX, SpriteSheetY, NumSprites, EnemyHealth, EnemyMovementSpeed, EnemyDamage, EnemyProjectileType, EnemyFireRate FROM tblEnemyTypes");
    
        ArrayList< ArrayList< String > > res = DBBridge.processResultSet(rs);
        
        // Get the properties of each enemy type
        for (int i = 0; i < res.size(); ++i)
        {
            ArrayList< String > row = res.get(i);
            String name = row.get(0);
            int spriteSheetX = Integer.parseInt(row.get(1));
            int spriteSheetY = Integer.parseInt(row.get(2));
            int numSprites = Integer.parseInt(row.get(3));
            int health = Integer.parseInt(row.get(4));
            int speed = Integer.parseInt(row.get(5));
            int damage = Integer.parseInt(row.get(6));
            int projectileTypeID = Integer.parseInt(row.get(7));
            int fireRate = Integer.parseInt(row.get(8));
            types.add(new EnemyType(name,
                                    spriteSheetX,
                                    spriteSheetY,
                                    numSprites,
                                    health,
                                    speed,
                                    damage,
                                    projectileTypeID,
                                    fireRate) );
        }
        
        GameLogger.logInfo("Enemy types loaded successfully.");
    }
    
    /**
     * Get a random enemy type from the list of enemy types
     * 
     * @return Random enemy type
     */
    public static EnemyType getRandomType()
    {
        // Get a random type
        int ind = Tools.randomInt(0, types.size());
        EnemyType e = types.get(ind);
        
        return e;
    }
    
    
}
