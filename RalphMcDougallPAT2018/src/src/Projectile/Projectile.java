/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.Projectile;

import java.util.ArrayList;
import java.util.Arrays;
import org.newdawn.slick.Image;
import src.GameObjects.Character;
import src.GameObjects.Entity;
import src.GameObjects.EntityHandler;
import src.Utility.GameLogger;
import src.GameObjects.Particle;
import org.newdawn.slick.Color;
import src.Main.Constants.DIRECTION;
import src.GUI.GameScreen;
import src.Main.Constants;
import src.Utility.GameClock;
import src.Utility.Tools;

/**
 * A projectile moves along the x- or y-axis and does damage to characters.
 * 
 * All projectiles are entities so Projectile extends Entity
 * 
 * @author Ralph McDougall 16/6/2018
 */
public class Projectile extends Entity {

    // *****************************************************
    // PRIVATE FIELDS
    // *****************************************************
    
    // The stats of the projectile
    private int damage;
    private int range;
    
    // The distance that the projectile has moved and how far it moves every step
    private double distanceMoved = 0;
    private double distancePerMove = 0;
    
    // The speed that the projectile moves at
    private final int SPEED = 16;
    
    // The faction of the person that fired the projectile
    private Character.FACTION faction;
    
    // The time that this projectile was created
    private long startTime = 0;
    
    // *****************************************************
    // CONSTRUCTOR
    // *****************************************************
    
    /**
     * Initialise a new projectile in the world
     * 
     * @param x The x-coordinate of the projectile
     * @param y The y-coordinate of the projectile
     * @param id The ID of the projectile type
     * @param direction The direction that the projectile travels in
     * @param dmg The damage that the projectile deals to characters
     * @param projFaction The faction of the character that fired this projectile
     */
    public Projectile(double x, double y, int id, DIRECTION direction, int dmg, Character.FACTION projFaction)
    {
        // Entity constructor call
        super(x, y, 1, new ArrayList<Image>(Arrays.asList(ProjectileTypeHandler.getProjectileType(id).getSprite())));
        damage = dmg;
        range = ProjectileTypeHandler.getProjectileType(id).getRange();
        setDirection(direction);
        findDistancePerMove();
        
        faction = projFaction;
        startTime = GameClock.getMilliTime();
    }
    
    // *****************************************************
    // PRIVATE METHODS
    // *****************************************************

    /**
     * Move the projectile in the direction it is supposed to move
     */
    private void moveProjectile()
    {
        double preX = worldX;
        double preY = worldY;
        
        switch (direction)
        {
            case UP:
                moveWorldY(-1 * distancePerMove);
                break;
            case LEFT:
                moveWorldX(-1 * distancePerMove);
                break;
            case DOWN:
                moveWorldY(distancePerMove);
                break;
            case RIGHT:
                moveWorldX(distancePerMove);
                break;
            default:
                GameLogger.logWarning("Invalid projectile direction found!");
                break;
        }
        
        // Check if the projectile actually moved
        if (worldX != preX || worldY != preY)
        {
            // The projectile has moved so increase the total distance travelled
            distanceMoved += distancePerMove;
        }
    }
    
    /**
     * Get the distance to move the projectile
     * 
     * This is based on the speed stat.
     */
    private void findDistancePerMove()
    {
        distancePerMove = SPEED * Constants.SPEED_FACTOR;
    }
    
    /**
     * Whether or not it is time to remove the projectile from the game
     * 
     * @return Whether or not the projectile needs to be removed
     */
    private boolean toRemove()
    {
        boolean toRemove = false;
        
        if (GameClock.getMilliTime() - startTime > 5000)
        {
            // The projectile has existed for far too long, so remove it
            toRemove = true;
        }
        else if (distanceMoved > range)
        {
            // The projectile has exceded its range, so remove it
            toRemove = true;
        }
        else if (nextMoveBlocked())
        {
            // It is about to move into an unwalkable block
            toRemove = true;
        }
        else if (collidesRelevantCharacter())
        {
            // It collides with a character, so deal damage and remove it
            toRemove = true;
        }
        
        return toRemove;
    }
    
    /**
     * Check whether or not the next move that the projectile makes will leave
     * it in an unwalkable tile.
     * 
     * @return Whether or not the next move is blocked
     */
    private boolean nextMoveBlocked()
    {
        double xOff = 0;
        double yOff = 0;
        
        // Get the x and y offset to the top-left corner of the projectile
        // once it has moved. This is based off of the direction it is
        // travelling in
        switch (direction)
        {
            case UP:
                yOff = -distancePerMove;
                break;
            case DOWN:
                yOff = distancePerMove + worldSize;
                break;
            case LEFT:
                xOff = -distancePerMove;
                break;
            case RIGHT:
                xOff = distancePerMove + worldSize;
                break;
            default:
                GameLogger.logWarning("Unknown projectile direction.");
                break;
        }
        
        // Check the map whether or not the appropriate tile is walkable
        return !GameScreen.getMap().getTileWalkable((int) (worldX + xOff), (int) (worldY + yOff) );
        
    }
    
    /**
     * Check if the projectile intersects a character in the game.
     * If the character is of an opposing faction, deal damage to the character
     * and the projectile should be removed.
     * 
     * @return Whether or not the projectile intersects a character
     */
    private boolean collidesRelevantCharacter()
    {
        boolean result = false;
        
        for (int i = 0; i < EntityHandler.getNumCharacters(); ++i)
        {
            Character c = EntityHandler.getCharacter(i);
            
            if (faction == Character.FACTION.ENEMY && c.getFaction() != Character.FACTION.FRIENDLY)
            {
                // Not a valid collision
                continue;
            }
            if (faction == Character.FACTION.FRIENDLY && c.getFaction() != Character.FACTION.ENEMY)
            {
                // Not a valid collision
                continue;
            }
            
            if (this.collides(c))
            {
                // It collides with a character of an opposing faction
                result = true;
                
                // Deal damage to the character and phase it
                c.dealDamage(damage);
                c.phase();
            }
        }
        
        // Return whether or not the projectile collided with a character
        return result;
    }
    
    /**
     * Ask the entity handler to remove this projectile from the game
     */
    private void removeProjectile()
    {
        EntityHandler.removeEntity(this);
    }
    
    // *****************************************************
    // PUBLIC METHODS
    // *****************************************************
    
    /**
     * Update the projectile.
     * 
     * Move the projectile forward in the appropriate direction
     * 
     * The colour of the particles should be randomised from the list of
     * colours given.
     * 
     * Remove the projectile if necessary.
     */
    public void update()
    {
        moveProjectile();
        
        Color[] colors = {Color.red, Color.yellow, Color.orange, Color.white};
        
        Color color = colors[Tools.randomInt(0, colors.length)];
        
        // Put a new particle at the location of the projectile
        Particle p = new Particle(worldX + worldSize / 2, worldY + worldSize / 2, color);
        
        if (toRemove())
        {
            removeProjectile();
        }
    }
}
