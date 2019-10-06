/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.NPC;

import src.GUI.GameScreen;
import src.Quest.Quest;
import src.Quest.QuestHandler;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import org.newdawn.slick.Image;
import src.GameObjects.Character;

/**
 * A Non-Player (friendly) Character. This gives the player quests that
 * they can complete for XP.
 * 
 * All NPCs are characters, hence NPC extends Character
 * 
 * @author Ralph McDougall 3/6/2018
 */
public class NPC extends Character {

    // *****************************************************
    // PRIVATE FIELDS
    // *****************************************************
    
    // The message for the NPC to display
    private String message = "";
    
    // The name of NPC
    private String name = "";
    
    // The quest that the NPC can issue
    private Quest quest;
    
    // Whether or not the player has spoken to the NPC
    private boolean spokenTo = false;
    
    // *****************************************************
    // CONSTRUCTOR
    // *****************************************************
    
    /**
     * Initialise a new NPC in the world
     * 
     * @param x The world x-coordinate of the NPC
     * @param y The world y-coordinate of the NPC
     * @throws SQLException The quest couldn't be loaded from the database
     */
    public NPC(double x, double y) throws SQLException {
        // Character constructor
        super(x, y, 1, new ArrayList<Image>(Arrays.asList(GameScreen.getCharacterSprite(0, 0)) ), Character.FACTION.NPC, 1);
        
        name = getRandomName();
        
        // Construct a new quest for the player
        quest = new Quest(name);
        
        message = quest.getQuestText();
        spokenTo = false;
    }
    
    // *****************************************************
    // PRIVATE METHODS
    // *****************************************************
    
    /**
     * Get a random name for the NPC. This comes from the list of possible
     * random names for an NPC to have.
     * 
     * @return The NPC name
     */
    private String getRandomName()
    {
        return NPCNameHandler.getRandomName();
    }
    
    // *****************************************************
    // PUBLIC METHODS
    // *****************************************************
    
    
    /**
     * Update the NPC here.
     * 
     * This does nothing as the NPC is static, but it is required for
     * this method to be implemented.
     */
    @Override
    public void update() {
        // Do nothing
    }
    
    /**
     * Just get the message that the NPC is going to display.
     * 
     * @return The NPC's message
     */
    public String getMessage()
    {
        return message;
    }
    
    /**
     * The player is speaking to the NPC. If the NPC hasn't been spoken to yet,
     * add the quest to the player's quests.
     * 
     * @throws SQLException Error adding quest to database
     */
    public void speakTo() throws SQLException
    {
        if (!spokenTo)
        {
            // Add the quest to the active quests since it hasn't been done yet
            QuestHandler.addQuest(quest);
            quest.insertQuestToDB();
        }
        spokenTo = true;
    }
}