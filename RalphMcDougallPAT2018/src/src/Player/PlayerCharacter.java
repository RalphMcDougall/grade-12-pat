/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.Player;

import src.NPC.NPC;
import src.UI.HUDLayer;
import src.GUI.GameScreen;
import src.Item.Item;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JOptionPane;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import src.GameObjects.Character;
import src.Main.Constants.DIRECTION;
import src.Main.GameHandler;
import src.Utility.GameLogger;
import static src.GUI.GameScreen.exit;

/**
 * The player character that the user controls while they play the game.
 * 
 * All player characters are characters, hence PlayerCharacter extends Character
 *
 * @author Ralph McDougall 2/5/2018
 */
public class PlayerCharacter extends Character {
    
    // *****************************************************
    // CONSTRUCTOR
    // *****************************************************
    
    /**
     * Initialise a new player character at the given coordinate
     * 
     * @param x Player's x-coordinate
     * @param y Player's y-coordinate
     */
    public PlayerCharacter(double x, double y) {
        super(x, y, 1,
                // Include the player character sprites
            new ArrayList<Image>(Arrays.asList( GameScreen.getCharacterSprite(0, 0),
                                                GameScreen.getCharacterSprite(1, 0),
                                                GameScreen.getCharacterSprite(2, 0),
                                                GameScreen.getCharacterSprite(3, 0),
                                                GameScreen.getCharacterSprite(4, 0),
                                                GameScreen.getCharacterSprite(5, 0),
                                                GameScreen.getCharacterSprite(6, 0),
                                                GameScreen.getCharacterSprite(7, 0))), 
                        Character.FACTION.FRIENDLY, 1);
        
        // Set the player's stats to the user stats
        User user = GameHandler.getUser();
        health = user.getHealth();
        damage = user.getDamage();
        fireRate = user.getFireRate();
        speed = user.getSpeed();
        
    }
    
    // *****************************************************
    // PRIVATE METHODS
    // *****************************************************
    
    /**
     * Check whether or not the player character touches the end tile
     * 
     * @return Whether or not the player character touches the end
     */
    private boolean touchesEndCoord()
    {
        // Check if it intersects with the end tile
        ArrayList<Integer> endCoord = GameScreen.getMap().getEndCoord();
        
        return touchesTile(endCoord.get(0), endCoord.get(1));
    }
    
    /**
     * Check whether or not the player character touches the item tile
     * 
     * @return Whether or not the player character touches the item
     */
    private boolean touchesItemCoord()
    {
        // Check if it intersects with the item tile
        ArrayList<Integer> itemCoord = GameScreen.getMap().getItemCoord();
        
        return touchesTile(itemCoord.get(0), itemCoord.get(1));
    }
    
    /**
     * Check whether or not the player character touches the NPC tile
     * 
     * @return Whether or not the player character touces the NPC
     */
    private boolean touchesNPCCoord()
    {
        // Check if it intersects with the NPC tile
        ArrayList<Integer> npcCoord = GameScreen.getMap().getNPCCoord();
        
        return touchesTile(npcCoord.get(0), npcCoord.get(1));
    }
    
    /**
     * Whether or not a given coordinate intersects the player character.
     * Check within a given radius so that the player doesn't have to be
     * exactly on the tile.
     * 
     * @param x The x-coordinate of the point to check
     * @param y The y-coordinate of the point to check
     * @return Whether or not the given tile intersects with the player character
     */
    private boolean touchesTile(int x, int y)
    {
        return (worldX > (x - 3) && worldX + worldSize < (x + 4)) && (worldY > (y - 3) && worldY + worldSize < (y + 4));
    }
    
    /**
     * Create a new map and set the player's coordinates to the starting
     * coordinates of the new map
     * 
     * @throws SQLException SQL could not retrieve some of the data from the database 
     */
    private void loadNewMap() throws SQLException
    {
        // Get a new map
        GameScreen.resetMap();
        
        // Move to the start of the map
        worldX = GameScreen.getMap().getPlayerStartX();
        worldY = GameScreen.getMap().getPlayerStartY();
        
        // Make enemies spawn faster to make it more difficult
        GameScreen.increaseDifficulty();
    }
    
    /**
     * Pickup the item that's in the map and increase the stats of the
     * player character
     */
    private void pickupItem()
    {
        HUDLayer hud = GameScreen.getHUDLayer();
        
        if (GameScreen.getMap().itemPickedUp())
        {
            // The item has already been picked up
            hud.changeMessageBoxDisplay("This item has already been picked up.");
            hud.changeMessageBoxVisibility(true);
            hud.lockMessageBoxDisplay(2);
            return;
        }
        // Get the item
        Item item = GameScreen.getMap().getItem();
        
        // Increase the player's stats
        health += item.getHealthBoost();
        damage += item.getDamageBoost();
        fireRate += item.getFireRateBoost();
        speed += item.getSpeedBoost();
        
        GameScreen.getMap().pickUpItem();
        
        // Inform the player of the item they have picked up
        hud.changeMessageBoxDisplay(item.getName() + " : " + item.getDescription());
        hud.changeMessageBoxVisibility(true);
        hud.lockMessageBoxDisplay(4);
    }
    
    /**
     * Get a new quest from the NPC in the map.
     * 
     * @throws SQLException SQL was unable to retrieve data from the database
     */
    private void talkToNPC() throws SQLException
    {
        // Get the NPC
        NPC npc = GameScreen.getMap().getNPC();
        
        // Get a new quest
        npc.speakTo();
        
        // Show the NPC's message
        HUDLayer hud = GameScreen.getHUDLayer(); 
        hud.changeMessageBoxDisplay(npc.getMessage());
        hud.changeMessageBoxVisibility(true);
        hud.lockMessageBoxDisplay(4);
    }
    
    
    // *****************************************************
    // PUBLIC METHODS
    // *****************************************************
    
    /**
     * Player control logic goes here.
     * 
     * Check that the player isn't dead yet and then handle any keyboard inputs.
     */
    public void update()
    {
        // Player logic goes here
        
        if (isDead())
        {
            // No need for negative health
            if (health < 0)
            {
                health = 0;
            }

            // The player has no health left, so the game ends
            
            GameLogger.logInfo("Player character dead.");
            
            // Record that the player has died
            User user = GameHandler.getUser();
            user.setNumDeaths(user.getNumDeaths() + 1);
            
            // Start the process of showing the game over screen
            GameScreen.startGameOver();
            return;
        }
        
        try {
            // Check for keyboard inputs
            handleKeyboardInput(GameScreen.getInput());
        } catch (SQLException ex) {
            GameLogger.logError("SQL Error while handling player inputs!\n" + ex);
            JOptionPane.showMessageDialog(null, "Database error while handling user inputs! See logs for details.");
            
            // Go back to the character main menu
            GameScreen.exit();
        }
        
        // Update the sprites to what they need to be
        if (moving)
        {
            nextSprite();
        }
        else
        {
            resetSprite();
        }
    }
    
    /**
     * Check all of the keyboard inputs and move the player character as
     * necessary and interact with the world objects.
     * 
     * @param input The Input object controlling the user inputs
     * @throws SQLException SQL could not retrieve some data from the database
     */
    public void handleKeyboardInput(Input input) throws SQLException
    {
        if (input == null)
        {
            // Don't check the input if it's null
            GameLogger.logWarning("Input object has null value for player character input.");
            return;
        }
        
        // Check whether the user wishes to exit
        if (input.isKeyPressed(Input.KEY_ESCAPE))
        {
            exit();
        }
        
        // Keys for moving the player character in the world
        if (input.isKeyDown(Input.KEY_W))
        {
            setDirection(DIRECTION.UP);
            moveUp();
        }
        else if (input.isKeyDown(Input.KEY_S))
        {
            setDirection(DIRECTION.DOWN);
            moveDown();
        }
        
        if (input.isKeyDown(Input.KEY_A))
        {
            setDirection(DIRECTION.LEFT);
            moveLeft();
        }
        else if (input.isKeyDown(Input.KEY_D))
        {
            setDirection(DIRECTION.RIGHT);
            moveRight();
        }
        
        // Keys to change the direction beign faced sand fire projectiles
        if (input.isKeyDown(Input.KEY_UP))
        {
            setDirection(DIRECTION.UP);
            fireProjectile();
        }
        else if (input.isKeyDown(Input.KEY_LEFT))
        {
            setDirection(DIRECTION.LEFT);
            fireProjectile();
        }
        else if (input.isKeyDown(Input.KEY_DOWN))
        {
            setDirection(DIRECTION.DOWN);
            fireProjectile();
        }
        else if (input.isKeyDown(Input.KEY_RIGHT))
        {
            setDirection(DIRECTION.RIGHT);
            fireProjectile();
        }
        
        // Check if the player wishes to interact with the world
        if (input.isKeyPressed(Input.KEY_E))
        {
            // The E (interact) key was pressed

            if (touchesEndCoord())
            {
                loadNewMap();
            }
            else if (touchesItemCoord())
            {
                pickupItem();
            }
            else if (touchesNPCCoord())
            {
                talkToNPC();
            }
        }
        
        // Determine whether or not the player is currently trying to move
        moving = input.isKeyDown(Input.KEY_W) || input.isKeyDown(Input.KEY_A) || input.isKeyDown(Input.KEY_S) || input.isKeyDown(Input.KEY_D);
    }
    
    /**
     * Check whether or not the player character touches one of the 3
     * points of interest in the map
     * 
     * @return Whether or not the player touches a POI
     */
    public boolean touchesPOI()
    {
        return touchesEndCoord() || touchesItemCoord() || touchesNPCCoord();
    }
}
