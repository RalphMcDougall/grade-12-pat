/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.Item;

import java.util.ArrayList;
import java.util.Arrays;
import org.newdawn.slick.Image;
import src.GameObjects.Entity;

/**
 * An item that the player can pick up in the map
 * 
 * All items are entities, hence Item extends Entity
 * 
 * @author Ralph McDougall 30/5/2018
 */
public class Item extends Entity {
    // *****************************************************
    // PRIVATE FIELDS
    // *****************************************************

    // The ID of the item type
    private ItemType itemType;
    
    // *****************************************************
    // CONSTRUCTOR
    // *****************************************************
    
    /**
     * Initialise a new item
     * 
     * @param worldX The x-coordinate of the location in the world where the item is placed
     * @param worldY The y-coordinate of the location in the world where the item is placed
     * @param id The ID of the item type of the item
     */
    public Item(double worldX, double worldY, int id) {
        // Entity constructor call
        super(worldX, worldY, 1, new ArrayList<Image>(Arrays.asList(ItemTypeHandler.getItemType(id).getSprite() ) ));
        
        itemType = ItemTypeHandler.getItemType(id);
    }
    
    // *****************************************************
    // PUBLIC METHODS
    // *****************************************************
    
    /**
     * Item update logic goes here.
     * 
     * This is blank as items are static.
     */
    public void update()
    {
        // Do nothing
    }
    
    /**
     * Get the health boost provided by the item upon being picked up
     * 
     * @return The health boost 
     */
    public int getHealthBoost()
    {
        return itemType.getHealthBoost();
    }
    
    /**
     * Get the damage boost provided by the item upon being picked up
     * 
     * @return The damage boost 
     */
    public int getDamageBoost()
    {
        return itemType.getDamageBoost();
    }
    
    /**
     * Get the fire rate boost provided by the item upon being picked up
     * 
     * @return The fire rate boost 
     */
    public int getFireRateBoost()
    {
        return itemType.getFireRateBoost();
    }
    
    /**
     * Get the speed boost provided by the item upon being picked up
     * 
     * @return The speed boost 
     */
    public int getSpeedBoost()
    {
        return itemType.getSpeedBoost();
    }
    
    /**
     * Get the name of the item
     * 
     * @return Item name 
     */
    public String getName()
    {
        return itemType.getItemName();
    }
    
    /**
     * Get the item description flavour text
     * 
     * @return The item description
     */
    public String getDescription()
    {
        return itemType.getItemDescription();
    }
    
}
