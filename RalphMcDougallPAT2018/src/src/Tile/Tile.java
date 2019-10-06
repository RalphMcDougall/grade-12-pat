/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.Tile;

import java.util.ArrayList;
import java.util.Arrays;
import org.newdawn.slick.Image;
import src.GameObjects.Entity;

/**
 * Keeps track of the stats associated with each tile in the map.
 *
 * All tiles are entities, so Tile extends Entity
 *
 * @author Ralph McDougall 26/4/2018
 */
public class Tile extends Entity {

    // *****************************************************
    // PRIVATE FIELDS
    // *****************************************************
    
    // The ID of the tile type of the tile
    private int tileID;

    // *****************************************************
    // CONSTRUCTOR
    // *****************************************************
    
    /**
     * Instantiate a new tile at the given coordinate with a given ID
     *
     * @param x The x-coordinate of the tile
     * @param y The y-coordinate of the tile
     * @param id The ID of the tile type
     */
    public Tile(double x, double y, int id) {
        // Tiles will always have size = 1 and the sprite can be determined by the id
        super(x, y, 1, new ArrayList<Image>(Arrays.asList(TileTypeHandler.getTileType(id).getSprite())));

        tileID = id;
    }

    // *****************************************************
    // PUBLIC METHODS
    // *****************************************************
    
    /**
     * Update the tile.
     *
     * This does nothing as tiles are static in the game.
     */
    public void update() {
        // Do nothing
    }

    /**
     * Whether or not a character may walk on the tile
     *
     * @return Whether or not it is walkable
     */
    public boolean walkable() {
        return TileTypeHandler.getTileType(tileID).walkable();
    }

}
