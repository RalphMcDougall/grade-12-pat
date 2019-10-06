/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.Projectile;

import org.newdawn.slick.Image;
import src.GUI.GameScreen;

/**
 * Stores all properties relating to projectile types.
 * 
 * @author Ralph McDougall 16/6/2018
 */
public class ProjectileType {
    
    // *****************************************************
    // PRIVATE FIELDS
    // *****************************************************
    
    // The properties of the projectile type
    private int id;
    private String description;
    private int spriteSheetX;
    private int spriteSheetY;
    private int range;
    
    // *****************************************************
    // CONSTRUCTOR
    // *****************************************************
    
    /**
     * Initialise a new projectile type.
     * 
     * @param projID The ID of the projectile type
     * @param projDescription A description of this projectile type
     * @param projSpriteSheetX The x-coordinate on the sprite sheet for this projectile type
     * @param projSpriteSheetY The y-coordinate on the sprite sheet for this projectile type
     * @param projRange The range that this projectile type can travel
     */
    public ProjectileType(int projID, String projDescription, int projSpriteSheetX, int projSpriteSheetY, int projRange)
    {
        id = projID;
        description = projDescription;
        spriteSheetX = projSpriteSheetX;
        spriteSheetY = projSpriteSheetY;
        range = projRange;
    }
    
    // *****************************************************
    // PUBLIC METHODS
    // *****************************************************
    
    /**
     * Get the image to display when this projectile type is being used
     * 
     * @return The image relating to this projectile type
     */
    public Image getSprite()
    {
        return GameScreen.getProjectileSprite(spriteSheetX, spriteSheetY);
    }
    
    /**
     * Get the range that this projectile type can travel
     * 
     * @return The projectile type range
     */
    public int getRange()
    {
        return range;
    }
    
}
