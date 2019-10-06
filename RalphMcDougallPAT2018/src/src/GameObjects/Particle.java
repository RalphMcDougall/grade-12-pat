/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.GameObjects;

import src.Utility.Tools;
import src.Utility.GameLogger;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import src.Utility.Camera;
import src.Utility.GameClock;
/**
 * This adds visual affects to projectiles to indicate motion and show when
 * characters get hit.
 * 
 * All particles are entities, so Particle extends Entity
 * 
 * @author Ralph McDougall 26/7/2018
 */
public class Particle extends Entity {

    // *****************************************************
    // PRIVATE FIELDS
    // *****************************************************

    // The size of the particle in the world
    private static final double size = 0.125;
    // How far the particles move at each step
    private static final double moveDist = 0.0675;
    
    // The particle may only exist for a certain amount of time
    private static final int lifeSpan = 250; // milliseconds
    private long creationTime = 0;
    
    // The colour of the particle
    private Color color;
    
    // *****************************************************
    // CONSTRUCTOR
    // *****************************************************
    
    /**
     * Instantiate a new particle with a given colour and position
     * 
     * @param x The x-coordinate of the particle
     * @param y The y-coordinate of the particle
     * @param c The colour of the particle
     */
    public Particle(double x, double y, Color c) {
        // Entity constructor call
        super(x - size / 2, y - size / 2, size, null);
        
        color = c;
        
        // Record when the particle was created
        creationTime = GameClock.getMilliTime();
        
        // Add the particle to the entity handler
        EntityHandler.addEntity(this);
    }

    // *****************************************************
    // PRIVATE METHODS
    // *****************************************************
    
    /**
     * Whether or not the particle has existed for its maximum age
     * @return Whether or not it has expired
     */
    private boolean expired()
    {
        return GameClock.getMilliTime() - creationTime > lifeSpan;
    }
    
    /**
     * Move the particle in a random direction
     */
    private void performRandomMove()
    {
        // Pick the direction to move in
        int direction = Tools.randomInt(0, 4);
        
        switch (direction)
        {
            case 0:
                // Move up
                moveWorldY(-1 * moveDist);
                break;
            case 1:
                // Move left
                moveWorldX(-1 * moveDist);
                break;
            case 2:
                // Move down
                moveWorldY(moveDist);
                break;
            case 3:
                // Move right
                moveWorldX(moveDist);
                break;
            default:
                GameLogger.logWarning("Unknown particle movement direction.");
                break;
        }
    }
    
    // *****************************************************
    // PUBLIC METHODS
    // *****************************************************
    
    /**
     * Make sure the particle hasn't expired yet and then move it randomly
     */
    @Override
    public void update() {
        if (expired())
        {
            // Remove the particle from the game
            EntityHandler.removeEntity(this);
            return;
        }
        else
        {
            // Move it randomly
            performRandomMove();
        }
    }
    
    /**
     * Draw the particle onto the screen
     * 
     * @param g The Graphics object to draw the particle onto
     * @param c The Camera object used to check if the particle is on the screen
     */
    @Override
    public void draw(Graphics g, Camera c)
    {
        // Set the colour to draw to the colour of the particle
        g.setColor(color);
        
        // Draw a rectangle on the screen where the particle goes
        g.fillRect( c.getScreenX(worldX), c.getScreenY(worldY), 
                (float) size * 32, (float) size * 32);
    }
}
