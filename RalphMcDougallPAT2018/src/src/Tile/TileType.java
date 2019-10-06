/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.Tile;

import org.newdawn.slick.Image;
import src.GUI.GameScreen;

/**
 * Stores properties of each tile type
 * 
 * @author Ralph McDougall 26/4/2018
 */
public class TileType {
    
    // *****************************************************
    // PRIVATE FIELDS
    // *****************************************************
    
    // Properties of this tile type
    private int tileID;
    private String tileName;
    private boolean walkable;
    private int spriteSheetX;
    private int spriteSheetY;
    
    // *****************************************************
    // CONSTRUCTOR
    // *****************************************************
    
    /**
     * Initialise a new tile type
     * 
     * @param id The ID of the tile type
     * @param name The name associated with the tile type
     * @param ssX The x-coordinate on the sprite sheet for the tile image
     * @param ssY The y-coordinate on the sprite sheet for the tile image
     * @param canWalk Whether or not characters can walk on the tile
     */
    public TileType(int id, String name, int ssX, int ssY, boolean canWalk)
    {
        tileID = id;
        tileName = name;
        spriteSheetX = ssX;
        spriteSheetY = ssY;
        walkable = canWalk;
    }
    
    // *****************************************************
    // PUBLIC METHODS
    // *****************************************************
    
    /**
     * Get the sprite image for tiles with this tile type
     * 
     * @return The tile sprite image
     */
    public Image getSprite()
    {
        return GameScreen.getTileSprite(spriteSheetX, spriteSheetY);
    }
    
    /**
     * Get whether or not characters can walk on tiles with this tile type
     * 
     * @return Whether or not this tile type is walkable
     */
    public boolean walkable()
    {
        return walkable;
    }
}
