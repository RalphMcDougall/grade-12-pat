/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.GameObjects;

import src.Utility.GameLogger;
import java.util.ArrayList;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import src.Utility.Camera;
import src.Main.Constants;
import src.GUI.GameScreen;
import src.Utility.GameClock;
import src.Utility.Tools;

/**
 * Any object that exists in the game world is an Entity
 *
 * @author Ralph McDougall 24/4/2018
 */
public abstract class Entity {

    // *****************************************************
    // PROTECTED FIELDS
    // *****************************************************
    
    // The direction that the entity is facing in the world
    protected Constants.DIRECTION direction = Constants.DIRECTION.UP;

    // x and y coordinate of the entity in the game world
    protected double worldX;
    protected double worldY;

    // The size of the square entity
    protected double worldSize;

    // *****************************************************
    // PRIVATE FIELDS
    // *****************************************************
    
    // The list of all sprites used by this entity
    private ArrayList<Image> sprites;
    // The index of the sprite currently being displayed
    private int currentSpriteIndex = 0;

    // The last time the sprite was change and how long to wait before changing sprites
    private long lastSpriteChange = 0;
    private final int MAX_SPRITE_CHANGE_WAIT = 100;

    // The last time the entity moved in the world and how long to wait between moves
    private long lastMove = 0;
    private final int MIN_WAIT_BETWEEN_MOVES = 20; // In milliseconds

    // *****************************************************
    // CONSTRUCTOR
    // *****************************************************
    
    /**
     * Instantiate a new entity in the game world
     * 
     * @param x The world x-coordinate of the entity
     * @param y The world y-coordinate of the entity
     * @param size The world size of the entity
     * @param spriteList The list of sprites to be used by the enemy for animation
     */
    public Entity(double x, double y, double size, ArrayList<Image> spriteList) {
        worldX = x;
        worldY = y;
        worldSize = size;

        sprites = spriteList;
    }
    
    // *****************************************************
    // PRIVATE METHODS
    // *****************************************************
    
    /**
     * Get the image to be displayed for the entity after finding the 
     * appropriate sprite in the sprite list and applying a rotation to
     * account for the direction that is being faced
     * 
     * @return The image of the Entity to be displayed
     */
    private Image getDisplayImage() {
        // Get the rotation angle
        int rotateAngle = 0;
        switch (direction) {
            case UP:
                rotateAngle = 0;
                break;
            case LEFT:
                rotateAngle = 270;
                break;
            case DOWN:
                rotateAngle = 180;
                break;
            case RIGHT:
                rotateAngle = 90;
                break;
            default:
                // Unknown direction
                GameLogger.logError("Invalid entity direction.");
                break;
        }
        
        // Get the current sprite from the list
        Image img = sprites.get(currentSpriteIndex).copy();
        // Rotate the image to the desired angle
        img.rotate(rotateAngle);

        return img;
    }
    
    /**
     * Check whether or not the entity is overlapping a tile that is not walkable
     * 
     * @return Whether or not the entity is allowed to be in this location
     */
    private boolean positionValid() {
        for (double y = worldY; y <= worldY + worldSize - 1.0 / Constants.TILE_SCREEN_SIZE; y += 1.0 / Constants.TILE_SCREEN_SIZE) {
            for (double x = worldX; x <= worldX + worldSize - 1.0 / Constants.TILE_SCREEN_SIZE; x += 1.0 / Constants.TILE_SCREEN_SIZE) {
                // Check each pixel that the entity covers to see if that is a
                // walkable tile in the world
                if (!GameScreen.getMap().getTileWalkable((int) x, (int) y)) {
                    return false; // An invalid tile was found
                }
            }
        }

        // No invalid tiles were found
        return true;
    }
    
    /**
     * Whether or not sufficient time has passed for the entity to move again
     * 
     * @return Whether or not the entity is allowed to move again
     */
    private boolean canMove() {
        
        return GameClock.getMilliTime() - lastMove >= MIN_WAIT_BETWEEN_MOVES
                || GameClock.getMilliTime() - lastMove == 0; // This allows moving in multiple directions at once
    }
    
    // *****************************************************
    // PROTECTED METHODS
    // *****************************************************
    
    /**
     * Move the current sprite being displayed to the next sprite in the animation.
     * If the animation cycle has completed, go back to the first sprite.
     */
    protected void nextSprite() {
        if (GameClock.getMilliTime() - lastSpriteChange > MAX_SPRITE_CHANGE_WAIT) {
            // Sufficient time has passed for the sprite to be changed.
            lastSpriteChange = GameClock.getMilliTime();
            currentSpriteIndex++;
            currentSpriteIndex %= sprites.size();
        }
    }

    /**
     * Reset the current sprite back to the default sprite
     */
    protected void resetSprite() {
        // Set the sprite back to the default sprite
        currentSpriteIndex = 0;
    }
    
    // *****************************************************
    // PUBLIC METHODS
    // *****************************************************
    
    
    /**
     * A method that all methods must have and implement themselves.
     * This is the logic that affects the state of the entity in the world.
     */
    public abstract void update();

    /**
     * Get the x-coordinate of the entity in the world
     * 
     * @return The x-coordinate of the entity
     */
    public double getX() {
        return worldX;
    }

    /**
     * Get the y-coordinate of the entity in the world
     * 
     * @return The y-coordinate of the entity
     */
    public double getY() {
        return worldY;
    }

    /**
     * Get the size of the entity in the world
     * 
     * @return The entity size
     */
    public double getSize() {
        return worldSize;
    }

    /**
     * Draw the entity onto the screen
     * 
     * @param g The Graphics object to draw the entity onto
     * @param c The Camera object to determine the location of the entity on the screen
     */
    public void draw(Graphics g, Camera c) {
        g.drawImage(getDisplayImage(), c.getScreenX(worldX), c.getScreenY(worldY));
    }

    /**
     * Move the entity along the x-axis by a given distance
     * 
     * @param diff The difference in position that the entity should move
     */
    public void moveWorldX(double diff) {
        
        if (canMove()) {
            // The entity is allowed to move
            
            // The offset to move the entity by in each step
            double off = Constants.SPEED_FACTOR * Math.signum(diff);
            // Number of movements to perform
            double numSteps = Math.abs(diff / Constants.SPEED_FACTOR);
            
            int distMoved;
            for (distMoved = 0; distMoved < numSteps; ++distMoved)
            {
                worldX += off;
                if (!positionValid())
                {
                    // A bad position was encountered so move back and stop trying to move
                    // Moving in this way stops entities from moving through walls
                    worldX -= off;
                    break;
                }
            }
            
            // Account for any floating point erros
            worldX = Tools.fixPrecision(worldX);

            // If the entity didn't move at all, don't bother updating the time they moved
            if (distMoved > 0)
            {
                // Record when this move took place
                lastMove = GameClock.getMilliTime();
            }
        }

    }

    /**
     * Move the entity along the y-axis by a given distance
     * 
     * @param diff The difference in position that the entity should move
     */
    public void moveWorldY(double diff) {
        
        if (canMove()) {
            // The entity is allowed to move
            
            // The offset to move the entity by in each step
            double off = Constants.SPEED_FACTOR * Math.signum(diff);
            // Number of movements to perform
            double numSteps = Math.abs(diff / Constants.SPEED_FACTOR);
            
            int distMoved;
            for (distMoved = 0; distMoved < numSteps; ++distMoved)
            {
                worldY += off;
                if (!positionValid())
                {
                    // A bad position was encountered so move back and stop trying to move
                    // Moving in this way stops entities from moving through walls
                    worldY -= off;
                    break;
                }
            }
            
            // Account for any floating point erros
            worldX = Tools.fixPrecision(worldX);

            // If the entity didn't move at all, don't bother updating the time they moved
            if (distMoved > 0)
            {
                // Record when this move took place
                lastMove = GameClock.getMilliTime();
            }
        }

    }
    
    /**
     * Set the current direction that the entity is facing
     * 
     * @param d The direction to face
     */
    public void setDirection(Constants.DIRECTION d) {
        direction = d;
    }
    
    /**
     * Check if this entity collides with another, given, entity
     * 
     * @param e The entity to check for collision
     * @return Whether or not the entities collide
     */
    public boolean collides(Entity e) {
        // Whether or not they collide on the x-axis
        boolean xCollide = (e.getX() + e.getSize() >= this.getX()) && (e.getX() < this.getX() + this.getSize());
        // Whether or not they collide on the y-axis
        boolean yCollide = (e.getY() + e.getSize() >= this.getY()) && (e.getY() < this.getY() + this.getSize());

        // They collide if and only if they intersect on the x- and y-axes
        return xCollide && yCollide;
    }
}
