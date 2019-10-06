/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.GameObjects;

import src.Projectile.Projectile;
import java.util.ArrayList;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import src.GUI.GameScreen;
import src.Main.Constants;
import src.Utility.GameClock;
import src.Utility.GameLogger;

/**
 * This stores the stats of each character entity and handles the movement of
 * all characters and determines if they need to be removed from the game.
 * 
 * All characters are entities so Character is a child class of Entity
 *
 * @author Ralph McDougall 29/4/2018
 */
public abstract class Character extends Entity {

    // *****************************************************
    // PUBLIC FIELDS
    // *****************************************************

    // Whether the character is an enemy of the user, or if it's friendly
    public static enum FACTION {
        FRIENDLY, ENEMY, NPC
    };
    
    // *****************************************************
    // PROTECTED FIELDS
    // *****************************************************
    
    // The stats of the character
    protected int health = 1;
    protected int damage = 0;
    protected int fireRate = 0;
    protected int speed = 0;

    // Whether or not the character is phased and so, can't move
    protected boolean phased = false;
    
    // The maximum time a character may be phased
    protected final int phaseTime = 200;
    protected long phaseStart = 0;

    // Whether or not the charcter is moving at this time
    protected boolean moving = false;

    // Stores the last time that the character fired a projectile
    protected long lastFire = 0;
    
    // The ID of the projectile that this character fires
    protected int projectileTypeID = 1;
    
    // *****************************************************
    // PRIVATE FIELDS
    // *****************************************************
    
    // If the character is friendly to the player or an enemy
    private FACTION faction;


    // *****************************************************
    // CONSTRUCTOR
    // *****************************************************
    
    /**
     * Instantiate a new Character that is also an Entity
     * 
     * @param x The world x-coordinate of the character
     * @param y The world y-coordinate of the character
     * @param size The world size of the character
     * @param spriteList The sprites that the character uses
     * @param characterFaction Which faction the character belongs to
     * @param projTypeID What type of projectile the entity fires
     */
    public Character(double x, double y, double size, ArrayList<Image> spriteList, FACTION characterFaction, int projTypeID) {
        // Call the Entity constructor
        super(x, y, size, spriteList);
        
        faction = characterFaction;
        projectileTypeID = projTypeID;

        // Add the character to the handler that can track all entities
        EntityHandler.addCharacter(this);
    }
    
    // *****************************************************
    // PRIVATE METHODS
    // *****************************************************
    
    /**
     * Get the distance that the character may move every time it moves.
     * This is based off of the speed stat.
     * 
     * @return The distance the character may move.
     */
    private double getMoveDistance() {
        return speed * Constants.SPEED_FACTOR;
    }
    
    /**
     * Whether or not the character may fire a new projectile
     * 
     * @return Whether or not the character may fire
     */
    private boolean canFire() {
        return (GameClock.getMilliTime() - lastFire) >= timeBetweenFires();
    }
    
    /**
     * Calculate the minimum time between each fire of a projectile.
     * This is calculated using the fire rate stat.
     * 
     * @return The time (in milliseconds) between projectile firing
     */
    private int timeBetweenFires() {
        return (int) (1000.0 / fireRate * (1000.0 / GameScreen.getUPS()));
    }
    
    
    // *****************************************************
    // PUBLIC METHODS
    // *****************************************************
    
    /**
     * Make the character move in a given direction
     * 
     * @param dir The direction to move in
     */
    public void move(Constants.DIRECTION dir) {
        // Check each direction and determine which function to call
        switch (dir) {
            case UP:
                moveUp();
                break;
            case LEFT:
                moveLeft();
                break;
            case DOWN:
                moveDown();
                break;
            case RIGHT:
                moveRight();
                break;
            default:
                GameLogger.logWarning("Unknown movement direction for character: " + this);
                break;
        }
    }

    /**
     * Move the character up in the world
     */
    public void moveUp() {
        moveWorldY(-1 * getMoveDistance());
        moving = true;
    }

    /**
     * Move the character down in the world
     */
    public void moveDown() {
        moveWorldY(1 * getMoveDistance());
        moving = true;
    }

    /**
     * Move the character left in the world
     */
    public void moveLeft() {
        moveWorldX(-1 * getMoveDistance());
        moving = true;
    }

    /**
     * Move the character right in the world
     */
    public void moveRight() {
        moveWorldX(1 * getMoveDistance());
        moving = true;
    }

    /**
     * Fire a projectile if possible in the direction that the character is facing
     */
    public void fireProjectile() {
        
        if (!canFire()) {
            // A projectile cannot be fired
            return;
        }

        // Update the last time a projectile was fired
        lastFire = GameClock.getMilliTime();
        
        // Create a new projectile with the correct type at the player's location
        Projectile p = new Projectile(worldX, worldY, projectileTypeID, direction, damage, faction);
        EntityHandler.addEntity(p);
    }

    /**
     * Whether or not the character has lost all of their health and is dead
     * 
     * @return Whether or not the character is dead
     */
    public boolean isDead() {
        return health <= 0;
    }

    /**
     * Deal damage to the character 
     * 
     * @param dmg The amount of damage to deal
     */
    public void dealDamage(int dmg) {
        health -= dmg;
        
        // Add red particles at the character location to show that they have been hurt
        for (int i = 0; i < 40; ++i) {
            Particle p = new Particle(worldX + worldSize / 2, worldY + worldSize / 2, Color.red);
        }
    }
    
    /**
     * Get the faction that the character belongs to
     * 
     * @return The character's faction
     */
    public FACTION getFaction() {
        return faction;
    }

    /**
     * Phase the character so that they cannot move for some given amount of time
     */
    public void phase() {
        phased = true;
        phaseStart = GameClock.getMilliTime();
    }
    
    /**
     * Get the damage that this character deals in every hit
     * 
     * @return Character damage
     */
    public int getDamage()
    {
        return damage;
    }
    
    /**
     * Get the health of this character
     * 
     * @return Character health
     */
    public int getHealth()
    {
        return health;
    }
    
    /**
     * Get the fire rate of this character
     * 
     * @return Character fire rate
     */
    public int getFireRate()
    {
        return fireRate;
    }
    
    /**
     * Get the speed at which this character moves
     * 
     * @return Character speed
     */
    public int getSpeed()
    {
        return speed;
    }
}
