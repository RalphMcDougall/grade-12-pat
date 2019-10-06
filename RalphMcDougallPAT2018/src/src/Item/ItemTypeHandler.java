/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.Item;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import src.Utility.DBBridge;
import src.Main.GameHandler;
import src.Utility.GameLogger;
import src.Utility.Tools;

/**
 * Loads all item types from the database so that they can be retrieved
 * at any point later on.
 * 
 * @author Ralph McDougall 20/5/2018
 */
public class ItemTypeHandler {
    
    // *****************************************************
    // PRIVATE FIELDS
    // *****************************************************
    
    // The number of item types
    private static int numItemTypes;
    
    private static final double RARITY_BASE = 3;
    
    // The list of item types with items from each rarity stored in the same sub-list
    private static ArrayList< ArrayList<ItemType> > itemTypes;
    
    // *****************************************************
    // PUBLIC METHODS
    // *****************************************************
    
    /**
     * Load the item types from the database
     * 
     * @throws SQLException SQL could not load the item types from the database 
     */
    public static void load() throws SQLException
    {
        GameLogger.logInfo("Loading item types from database...");
        
        // Initialise the list of item types
        itemTypes = new ArrayList<>();
        
        // Get the database bridge
        DBBridge db = GameHandler.getPersistentDB();
        
        // Get the item table
        ResultSet rs = db.query("SELECT ItemID, ItemName, ItemDescription, Rarity, SpriteSheetX, SpriteSheetY, HealthBoost, DamageBoost, FireRateBoost, SpeedBoost FROM tblItems;");
        
        ArrayList< ArrayList< String > > res = DBBridge.processResultSet(rs);
        
        numItemTypes = res.size();
        
        for (int i = 0; i < numItemTypes; ++i)
        {
            // Populate the list of item types
            
            ArrayList<String> item = res.get(i);
            
            int ID = Integer.parseInt(item.get(0));
            String name = item.get(1);
            String description = item.get(2);
            int rarity = Integer.parseInt(item.get(3));
            int spriteSheetX = Integer.parseInt(item.get(4));
            int spriteSheetY = Integer.parseInt(item.get(5));
            int healthBoost = Integer.parseInt(item.get(6));
            int damageBoost = Integer.parseInt(item.get(7));
            int fireRateBoost = Integer.parseInt(item.get(8));
            int speedBoost = Integer.parseInt(item.get(9));
            
            while (itemTypes.size() < rarity)
            {
                // Add more lists to make sure all rarity levels are covered
                ArrayList<ItemType> list = new ArrayList<>();
                itemTypes.add(list);
            }
            
            itemTypes.get(rarity - 1).add(new ItemType(ID,
                    name,
                    description,
                    rarity,
                    spriteSheetX,
                    spriteSheetY,
                    healthBoost,
                    damageBoost,
                    fireRateBoost,
                    speedBoost));
        }
        
        GameLogger.logInfo("Item types loaded successfully.");
        
    }
    
    /**
     * Get the ItemType with a given ID
     * 
     * @param id The ID of the ItemType to find
     * @return ItemType with the given ID
     */
    public static ItemType getItemType(int id)
    {
        ItemType item = null;
        
        for (ArrayList<ItemType> list : itemTypes)
        {
            for (ItemType i : list)
            {
                // Search through all items and find the one with the given ID
                if (i.getID() == id)
                {
                    item = i;
                    break;
                }
            }
            if (item != null)
            {
                // Item found
                break;
            }
        }
        
        return item;
    }
    
    /**
     * Get the ID of a random ID, with less rare items appearing more often
     * 
     * @return Random item ID
     */
    public static int getRandomItemID()
    {
        // The rarity of the rarest item
        int maxRarity = itemTypes.size();
        
        // Find a random number from 1 to RARITY_BASE ^ maxRarity, the rarity of the
        // chosen item is maxRarity - log(randomNumber) / log(RARITY_BASE)
        // Pick a random item with that rarity
        
        // Pick the rarity of the item
        int powBase = (int) Math.pow(RARITY_BASE, maxRarity);
        int randInt = Tools.randomInt(1, powBase);
        int rarity = maxRarity - (int) (Math.log(randInt) / Math.log(RARITY_BASE));
        
        // Pick a random item with that rarity
        int listPos = Tools.randomInt(0, itemTypes.get(rarity - 1).size());
        
        // Get the item with that rarity and that index
        return itemTypes.get(rarity - 1).get(listPos).getID();
    }
}
