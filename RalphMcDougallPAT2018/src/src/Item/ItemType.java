/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.Item;

import org.newdawn.slick.Image;
import src.GUI.GameScreen;

/**
 * Stores all of the properties of a given item type
 * 
 * @author Ralph McDougall 30/5/2018
 */
public class ItemType {
    
    // *****************************************************
    // PRIVATE FIELDS
    // *****************************************************
    
    // Item type properties
    private int id;
    private String itemName;
    private String itemDescription;
    private int rarity;
    private int spriteSheetX;
    private int spriteSheetY;
    private int healthBoost;
    private int damageBoost;
    private int fireRateBoost;
    private int speedBoost;

    // *****************************************************
    // CONSTRUCTOR
    // *****************************************************
    
    /**
     * Initialise a new item type
     * 
     * @param id The ID of the item type
     * @param itemName The name of the item in the item type
     * @param itemDescription Description of the item (flavour text)
     * @param rarity Rarity level of the item type
     * @param spriteSheetX x-coordinate of the item sprite on the sprite sheet
     * @param spriteSheetY y-coordinate of the item sprite on the sprite sheet
     * @param healthBoost Health boost provided by the item
     * @param damageBoost Damage boost provided by the item
     * @param fireRateBoost Fire rate boost provided by the item
     * @param speedBoost Speed boost provided by the item
     */
    public ItemType(int id, String itemName, String itemDescription, int rarity, int spriteSheetX, int spriteSheetY, int healthBoost, int damageBoost, int fireRateBoost, int speedBoost) {
        this.id = id;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.rarity = rarity;
        this.spriteSheetX = spriteSheetX;
        this.spriteSheetY = spriteSheetY;
        this.healthBoost = healthBoost;
        this.damageBoost = damageBoost;
        this.fireRateBoost = fireRateBoost;
        this.speedBoost = speedBoost;
    }
    
    // *****************************************************
    // PUBLIC METHODS
    // *****************************************************
    
    /**
     * Get the sprite image of the item type from the sprite sheet
     * 
     * @return Item image
     */
    public Image getSprite()
    {
        return GameScreen.getItemSprite(spriteSheetX, spriteSheetY);
    }
    
    /**
     * Get the ID of the item type
     * 
     * @return Item type ID
     */
    public int getID()
    {
        return id;
    }
    
    /**
     * Get the name of the item type
     * 
     * @return Item type name
     */
    public String getItemName()
    {
        return itemName;
    }
    
    /**
     * Get the description (flavour text) of the item type
     * 
     * @return Item description
     */
    public String getItemDescription()
    {
        return itemDescription;
    }
    
    /**
     * Get the health boost provided by the item type upon being picked up
     * 
     * @return Item type health boost
     */
    public int getHealthBoost()
    {
        return healthBoost;
    }
    
    /**
     * Get the damage boost provided by the item type upon being picked up
     * 
     * @return Item type damage boost
     */
    public int getDamageBoost()
    {
        return damageBoost;
    }
    
    /**
     * Get the fire rate boost provided by the item type upon being picked up
     * 
     * @return Item type fire rate boost
     */
    public int getFireRateBoost()
    {
        return fireRateBoost;
    }
    
    /**
     * Get the speed boost provided by the item type upon being picked up
     * 
     * @return Item type speed boost
     */
    public int getSpeedBoost()
    {
        return speedBoost;
    }
    
}
