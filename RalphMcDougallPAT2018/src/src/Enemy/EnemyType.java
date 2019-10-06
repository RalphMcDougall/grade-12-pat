/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.Enemy;

import java.util.ArrayList;
import org.newdawn.slick.Image;
import src.GUI.GameScreen;


/**
 * This class stores the stats of an enemy type so that it can be retrieved 
 * easily when a new enemy is instantiated
 *
 * @author Ralph McDougall 22/7/2018
 */
public class EnemyType {
    
    // *****************************************************
    // PRIVATE FIELDS
    // *****************************************************
    
    // Enemy type properties
    private String name;
    private int spriteSheetX;
    private int spriteSheetY;
    private int numSprites;
    private int health;
    private int movementSpeed;
    private int damage;
    private int projectile;
    private int fireRate;
    
    private ArrayList<Image> sprites;

    // *****************************************************
    // CONSTRUCTOR
    // *****************************************************
    
    /**
     * Initialise a new enemy type
     * 
     * @param name Name of the enemy type
     * @param spriteSheetX x-coordinate of the first sprite for the enemy type
     * @param spriteSheetY y-coordinate of the first sprite for the enemy type
     * @param numSprites The number of sprites that the enemy type uses
     * @param health The starting health of the enemy type
     * @param movementSpeed Movement speed of the enemy type
     * @param damage Damage of the enemy type
     * @param projectile ID of the projectile type that this enemy type fires
     * @param fireRate The fire rate of the enemy type
     */
    public EnemyType(String name, int spriteSheetX, int spriteSheetY, int numSprites, int health, int movementSpeed, int damage, int projectile, int fireRate) {
        this.name = name;
        this.spriteSheetX = spriteSheetX;
        this.spriteSheetY = spriteSheetY;
        this.numSprites = numSprites;
        this.sprites = new ArrayList<>();
        this.health = health;
        this.movementSpeed = movementSpeed;
        this.damage = damage;
        this.projectile = projectile;
        this.fireRate = fireRate;
    }
    
    // *****************************************************
    // PUBLIC METHODS
    // *****************************************************
    
    /**
     * Get the name of this enemy type
     * 
     * @return Enemy name
     */
    public String getName() {
        return name;
    }

    /**
     * Get the x-coordinate of the first sprite of this enemy type
     * 
     * @return Enemy sprite sheet x-coordinate
     */
    public int getSpriteSheetX() {
        return spriteSheetX;
    }

    /**
     * Get the y-coordinate of the first sprite of this enemy type
     * 
     * @return Enemy sprite sheet y-coordinate
     */
    public int getSpriteSheetY() {
        return spriteSheetY;
    }

    /**
     * Get the number of sprites of this enemy type
     * 
     * @return Enemy number of sprites
     */
    public int getNumSprites() {
        return numSprites;
    }

    /**
     * Get the health of this enemy type
     * 
     * @return Enemy health
     */
    public int getHealth() {
        return health;
    }

    /**
     * Get the speed of this enemy type
     * 
     * @return Enemy speed
     */
    public int getMovementSpeed() {
        return movementSpeed;
    }

    /**
     * Get the damage of this enemy type
     * 
     * @return Enemy damage
     */
    public int getDamage() {
        return damage;
    }

    /**
     * Get the projectile type ID of this enemy type
     * 
     * @return Enemy type ID
     */
    public int getProjectile() {
        return projectile;
    }

    /**
     * Get the fire rate of this enemy type
     * 
     * @return Enemy fire rate
     */
    public int getFireRate() {
        return fireRate;
    }

    /**
     * Get the list of all sprites that this enemy type uses
     * 
     * @return Enemy type sprites
     */
    public ArrayList<Image> getSprites() {
        if (sprites.size() == 0)
        {
            // The sprite list hasn't been initialised yet, so first load the sprites
        
            for (int i = 0; i < numSprites; ++i)
            {
                // Get the sprites of the enemy from the character sprite sheet
                // All sprites are next to each other in a horizontal row starting from (spriteSheetX, spriteSheetY)
                sprites.add(GameScreen.getCharacterSprite(spriteSheetX + i, spriteSheetY));
            }
        }
        
        // Return the list of sprites
        return sprites;
    }
    
    
}
