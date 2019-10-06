/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.Quest;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import src.Utility.DBBridge;
import src.Main.GameHandler;
import src.Utility.GameLogger;

/**
 * This class tracks all of the player's active quests and updates the values as required
 *
 * @author Ralph McDougall 26/6/2018
 */
public class QuestHandler {
    // *****************************************************
    // PRIVATE FIELDS
    // *****************************************************
    
    // Active quests
    private static ArrayList<Quest> quests;
    
    // *****************************************************
    // PUBLIC METHODS
    // *****************************************************
    
    /**
     * Set the list of active quests to a new list
     */
    public static void initialiseQuestHandler()
    {
        // Clear the quest list for the new user
        quests = new ArrayList<Quest>();
    }
    
    /**
     * Load all of the user's active quests from the database
     * 
     * @param userID The ID of the current user
     * @throws SQLException SQL could not retrieve the quests from the database
     */
    public static void loadQuests(int userID) throws SQLException
    {
        // Get all quests
        DBBridge db = GameHandler.getPersistentDB();
        String query = "SELECT QuestID FROM tblActiveQuests WHERE UserID = " + userID + ";";
        ResultSet rs = db.query(query);
        
        ArrayList< ArrayList<String> > data = DBBridge.processResultSet(rs);
        
        // Add the quests found to the list of active quests
        for (ArrayList<String> record : data)
        {
            addQuest(new Quest(Integer.parseInt(record.get(0))));
        }
    }
    
    /**
     * Add a new quest to the list of active quests
     * 
     * @param q The new quest to add
     */
    public static void addQuest(Quest q)
    {
        quests.add(q);
    }
    
    /**
     * Update the values for each active quest in the database
     * 
     * @throws SQLException SQL could not update the database
     */
    public static void updateDB() throws SQLException
    {
        for (Quest q : quests)
        {
            q.updateDB();
        }
    }
    
    /**
     * Get the number of active quests currently saved
     * 
     * @return The number of active quests
     */
    public static int getNumQuests()
    {
        return quests.size();
    }
    
    /**
     * Get the quest with a given index
     * 
     * @param ind The index of the quest to find
     * @return The quest with at the given index
     */
    public static Quest getQuest(int ind)
    {
        if (ind < 0 || ind > getNumQuests())
        {
            // Not a valid index
            GameLogger.logError("Invalid quest index: " + ind);
            return null;
        }
        return quests.get(ind);
    }
    
    /**
     * An event has occurred in the game that might increase the progress
     * of some quests. Check if it does.
     * Look through each quest and see if the type and target match.
     * 
     * @param eventType The type of event that took place
     * @param target The target of the event that took place
     */
    public static void logEvent(String eventType, String target)
    {
        // Look through all of the quests to check if they need to be increased
        for (Quest q : quests)
        {
            if (q.getQuestTypeDescription().equals(eventType) && q.getTarget().equals(target))
            {
                // The event matches the quest currently being checked
                q.increaseProgress();
            }
        }
    }
    
}
