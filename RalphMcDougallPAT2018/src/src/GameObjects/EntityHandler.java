/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.GameObjects;

import src.Utility.GameLogger;
import java.util.ArrayList;
import org.newdawn.slick.Graphics;
import src.Utility.Camera;

/**
 * Handles all FOREGROUND entities.
 * Tiles are also entities, but they are handled separately in the Map class.
 * This is to improve performance.
 * 
 * This class draws all of the other entities and allows for easy access to all
 * entities.
 * 
 * @author Ralph McDougall 29/4/2018
 */
public class EntityHandler {
    
    // *****************************************************
    // PRIVATE FIELDS
    // *****************************************************
    
    // The list of all entities in the game
    private static ArrayList<Entity> allEntities;
    
    // The list of all entities that are also characters
    private static ArrayList<Character> allCharacters;
    
    // Lists of entities to remove and add to the list of all entities
    private static ArrayList<Entity> toRemove;
    private static ArrayList<Entity> toAdd;
    
    // Lists of characters to remove and add to the list of all entities
    private static ArrayList<Character> cToRemove;
    private static ArrayList<Character> cToAdd;
    
    // *****************************************************
    // PUBLIC METHODS
    // *****************************************************
    
    /**
     * Initialise all of the lists for the entity handler
     */
    public static void setupEntityHandler()
    {
        allEntities = new ArrayList<>();
        toRemove = new ArrayList<>();
        toAdd = new ArrayList<>();
        allCharacters = new ArrayList<>();
        
        cToAdd = new ArrayList<>();
        cToRemove = new ArrayList<>();
    }
    
    /**
     * Add a new Character to the character list.
     * 
     * @param c The Character to add to the list
     */
    public static void addCharacter(Character c)
    {
        cToAdd.add(c);
        addEntity(c);
    }
    
    /**
     * Remove a Character from the character list.
     * 
     * @param c The Character to remove.
     */
    public static void removeCharacter(Character c)
    {
        cToRemove.add(c);
        removeEntity(c);
    }
    
    /**
     * Get the number of characters in the character list
     * 
     * @return The number of characters
     */
    public static int getNumCharacters()
    {
        return allCharacters.size();
    }
    
    /**
     * Get the character at a given index
     * 
     * @param ind The index of character to find
     * @return The chosen character
     */
    public static Character getCharacter(int ind)
    {
        return allCharacters.get(ind);
    }
    
    /**
     * Add a new entity to the entity list
     * 
     * @param e The entity to add
     */
    public static void addEntity(Entity e)
    {
        toAdd.add(e);
    }
    
    /**
     * Remove an entity from the entity list
     * 
     * @param e The entity to remove
     */
    public static void removeEntity(Entity e)
    {
       toRemove.add(e);
    }
    
    /**
     * Get the number of entities in the list
     * 
     * @return The number of entities
     */
    public static int getNumEntities()
    {
        return allEntities.size();
    }
    
    /**
     * Get an entity at a specific index 
     * 
     * @param ind The index to return
     * @return The entity found
     */
    public static Entity getEntity(int ind)
    {
        return allEntities.get(ind);
    }
    
    /**
     * Draw all of the entities to the screen
     * 
     * @param g The Graphics object to draw the entities to
     * @param cam The camera to use to see which entities must be drawn
     */
    public static void drawEntities(Graphics g, Camera cam)
    {
        for (Entity e : allEntities)
        {
            e.draw(g, cam);
        }
    }
    
    /**
     * Update all of the entities present.
     * 
     * First add all entities that should be added to the list.
     * Then remove all entities that should be removed from the list.
     * 
     * This prevents entities being modified while new entities are added,
     * which causes reference problems.
     */
    public static void updateEntities()
    {
        // Add and remove entities as necessary
        allEntities.addAll(toAdd);
        toAdd.clear();
        allEntities.removeAll(toRemove);
        toRemove.clear();
        
        // Add and remove characters as necessary
        allCharacters.addAll(cToAdd);
        cToAdd.clear();
        allCharacters.removeAll(cToRemove);
        cToRemove.clear();
        
        // Update entities
        for (Entity e : allEntities)
        {
            e.update();
        }
    }
    
    /**
     * Remove all enemies from the entity handler
     */
    public static void removeAllEnemies()
    {
        GameLogger.logInfo("Removing all enemies from entity handler...");
        
        // Check all characters
        for (Character c : allCharacters)
        {
            // Only remove the character if it's in the enemy faction
            if (c.getFaction() == Character.FACTION.ENEMY)
            {
                removeCharacter(c);
            }
        }
        GameLogger.logInfo("Enemies removed from entity handler.");
    }
    
}
