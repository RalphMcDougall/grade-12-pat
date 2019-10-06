/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.Enemy;

import src.GameObjects.Character;
import src.Main.Constants.DIRECTION;
import src.GameObjects.EntityHandler;
import src.Main.GameHandler;
import src.GUI.GameScreen;
import src.Main.Constants;
import src.Map.PFNode;
import src.Projectile.ProjectileTypeHandler;
import src.Quest.QuestHandler;
import src.Utility.Tools;
import src.Player.User;
import src.Utility.GameClock;

/**
 * This class handles all enemy mobs.
 * 
 * All enemies are also characters in the game, hence Enemy extends Character.
 * 
 * @author Ralph McDougall 2/5/2018
 */
public class Enemy extends Character {
    // *****************************************************
    // PRIVATE FIELDS
    // *****************************************************
    
    // The description of the enemy type
    private String enemyType = "Enemy";
    
    // Whether or not the player is moving on non-integer coordinates
    private boolean nonIntMove = false;
    private DIRECTION nonIntMoveDirection = DIRECTION.UP;
    
    // *****************************************************
    // CONSTRUCTOR
    // *****************************************************
    
    /**
     * Initialise the enemy with a given location in the world and a
     * given enemy type.
     * 
     * @param x The x-coordinate of the position in the world
     * @param y The y-coordinate of the position in the world
     * @param e The enemy type
     */
    public Enemy(double x, double y, EnemyType e) {
        // Character constructor call
        super(x, y, 1, e.getSprites(), Character.FACTION.ENEMY, 1);
        
        // Set the values of the stats of the enemy
        enemyType = e.getName();
        
        health = e.getHealth();
        damage = e.getDamage();
        fireRate = e.getFireRate();
        speed = e.getMovementSpeed();
        
        projectileTypeID = e.getProjectile();
    }
    
    // *****************************************************
    // PRIVATE METHODS
    // *****************************************************
    
    /**
     * Move the enemy if possible. If the enemy can track the player, it
     * moves towards it, otherwise it moves around randomly to make it look as
     * if it is alive
     */
    private void performMove()
    {
        // Check if the enemy is aligned with an integer coordinate in the world
        if (Tools.isInt(worldX) && Tools.isInt(worldY))
        {
            nonIntMove = false;
        }
        else
        {
            nonIntMove = true;
        }
        
        // Move the enemy in the given direction until it alligns with an
        // integer coordinate. If the enemy alligns with an integer coordinate,
        // find the new direction to move in
        
        if (nonIntMove)
        {
            // The enemy character is currently moving between integer coordinates
            move(nonIntMoveDirection);
        }
        else
        {
            // Get the node that matches the enemy
            PFNode node = GameScreen.getPathFindingGrid().getCorrespondingNode(this);
            
            if (node.getDist() <= ProjectileTypeHandler.getProjectileType(projectileTypeID).getRange()
                && node.isInLine())
            {
                // The enemy can fire at the player since it is in line
                // with the player and the player is within range
                setDirection(node.getDir());
                fireProjectile();
            }
            else if (node.getDist() <= Constants.MAX_PATH_FINDING)
            {
                // The distance to the player is less than the maximum
                // path finding distance
                trackPlayer(node);
            }
            else
            {
                // Otherwise move around randomly
                performRandomMovements();
            }
            
        }
    }
    
    /**
     * Move the enemy towards the player character by looking at the
     * values stored in the node
     * 
     * @param node The node that corresponds to the enemy's position
     */
    private void trackPlayer(PFNode node)
    {
        // Move in the direction that is the shortest path to the player
        setDirection(node.getDir());
        move(node.getDir());
        
        // The direction to keep moving in while on a non-integer coordinate
        nonIntMoveDirection = node.getDir();
    }
    
    /**
     * Move the enemy randomly to make it look like the enemy is alive in
     * the world at all times.
     */
    private void performRandomMovements()
    {
        // Pick a new direction for the movement to happen
        int rand = Tools.randomInt(0, 4);
        DIRECTION dirArr[] = {DIRECTION.UP, DIRECTION.LEFT, DIRECTION.DOWN, DIRECTION.RIGHT};
        
        DIRECTION randomMoveDirection = dirArr[rand];
        
        // Move in the direction
        move(randomMoveDirection);
        setDirection(randomMoveDirection);
        nonIntMoveDirection = randomMoveDirection;
    }
    
    // *****************************************************
    // PUBLIC METHODS
    // *****************************************************
    
    /**
     * Enemy logic. Checks if the enemy is dead and removes it if necessary.
     * Tries to make the enemy move around the world.
     */
    public void update()
    {
        if (isDead())
        {
            // The enemy is dead, so the event must be flagged and the enemy
            // must be removed.
            
            // Log the kill event
            QuestHandler.logEvent("kill", enemyType);
            
            // Increase the number of kills by the user
            User user = GameHandler.getUser();
            user.setNumKills(user.getNumKills() + 1);
            
            // The enemy character is dead so it should be removed
            remove();
            return;
        }
        
        if (phased)
        {
            // Make the character freeze where they are for a few milliseconds
            
            resetSprite();
            
            // Check if sufficient time for the enemy to be phased has passed
            if (GameClock.getMilliTime() - phaseStart > phaseTime)
            {
                phased = false;
            }
            return;
        }
        
        if (moving)
        {
            // Set the current sprite to the next sprite in the sprite list
            // while it's moving
            nextSprite();
        }
        else
        {
            // If it isn't moving, change the sprite back to the stationary sprite
            resetSprite();
        }
        
        // Make the enemy move
        moving = false;
        performMove();
    }
    
    /**
     * Ask the entity handler to remove this enemy from the game world
     */
    public void remove()
    {
        EntityHandler.removeCharacter(this);
    }
    
}
