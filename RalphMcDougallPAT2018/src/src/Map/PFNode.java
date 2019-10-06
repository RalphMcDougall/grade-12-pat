/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.Map;

import src.Main.Constants.DIRECTION;

/**
 * The Path finding grid consists of Path Finding Nodes that store distance 
 * to the player and the direction to move to get to the player as quickly
 * as possible
 * 
 * @author Ralph McDougall 1/7/2018
 */
public class PFNode {
    // *****************************************************
    // PRIVATE FIELDS
    // *****************************************************
    
    // Very large number ("Infinity")
    public static final double INF = 1000000000;
    
    // Initialise the distance as some stupidly large number
    private double dist = INF; 
    
    // The direction to move to get to the player
    private DIRECTION dir = DIRECTION.UP; 
    
    // Whether or not there is a straight line to the player character
    private boolean inLine = false; 
    
    // The coordinates in the grid 
    private int x;
    private int y;
    
    // *****************************************************
    // CONSTRUCTOR
    // *****************************************************
    
    /**
     * Initialise a new node at the given coordinate
     * 
     * @param x The x-coordinate of the node in the grid
     * @param y The y-coordinate of the node in the grid
     */
    public PFNode(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    
    // *****************************************************
    // PUBLIC METHODS
    // *****************************************************
    
    /**
     * Reset the distance from the player, the direction to travel
     * and whether or not the player is in line with the node
     */
    public void reset()
    {
        // Reset the values back to the default values
        dist = INF;
        dir = DIRECTION.UP;
        inLine = false;
    }
    
    /**
     * Get the distance to the player
     * 
     * @return Distance to player
     */
    public double getDist()
    {
        return dist;
    }
    
    /**
     * Set the distance to the player
     * 
     * @param d New distance to the player
     */
    public void setDist(double d)
    {
        dist = d;
    }

    /**
     * Get the direction to the player
     * 
     * @return Direction to player
     */
    public DIRECTION getDir() {
        return dir;
    }

    /**
     * Set the direction to the player
     * 
     * @param dir New direction to player
     */
    public void setDir(DIRECTION dir) {
        this.dir = dir;
    }

    /**
     * Whether or not the node is in line with the player
     * 
     * @return Whether node is in line with player
     */
    public boolean isInLine() {
        return inLine;
    }

    /**
     * Set whether or not the node is in line with the player
     * 
     * @param inLine Set whether the player is in line
     */
    public void setInLine(boolean inLine) {
        this.inLine = inLine;
    }
    
    /**
     * Get the x-coordinate of the node
     * 
     * @return The x-coordinate of the node
     */
    public int getX()
    {
        return x;
    }
    
    /**
     * Get the y-coordinate of the node
     * 
     * @return The y-coordinate of the node
     */
    public int getY()
    {
        return y;
    }
}
