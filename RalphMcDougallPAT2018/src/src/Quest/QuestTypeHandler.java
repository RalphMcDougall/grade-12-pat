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
import src.Utility.Tools;

/**
 * This class loads all of the quest types from the database
 *
 * @author Ralph McDougall 26/6/2018
 */
public class QuestTypeHandler {
    
    // *****************************************************
    // PRIVATE FIELDS
    // *****************************************************
    
    // All of the quest types
    private static ArrayList<QuestType> allTypes;
    
    // *****************************************************
    // PUBLIC METHODS
    // *****************************************************
    
    /**
     * Load the quest types from the database
     * 
     * @throws SQLException SQL could not load the quest types
     */
    public static void load() throws SQLException
    {
        GameLogger.logInfo("Loading quest types from database...");
        
        allTypes = new ArrayList<QuestType>();
        
        // Query the database
        DBBridge db = GameHandler.getPersistentDB();
        String query = "SELECT QuestTypeID, QuestTypeDescriptor FROM tblQuestTypes";
        ResultSet rs = db.query(query);
        
        ArrayList< ArrayList<String> > data = DBBridge.processResultSet(rs);
        
        // Add the quest types to the list
        for (ArrayList<String> record : data)
        {
            int id = Integer.parseInt(record.get(0));
            String descriptor = record.get(1);
            allTypes.add(new QuestType(id, descriptor ));
        }
        
        GameLogger.logInfo("Quest types loaded successfully.");
        
    }
    
    /**
     * Get the quest type with the given ID
     * 
     * @param id The ID of the quest type to find
     * @return The quest type with the given ID
     */
    public static QuestType getQuestType(int id)
    {
        QuestType res = null;
        
        // Check each quest type for the one with the given ID
        for (QuestType q : allTypes)
        {
            if (q.getID() == id)
            {
                res = q;
                break;
            }
        }
        if (res == null)
        {
            GameLogger.logError("Unknown quest type ID: " + id);
        }
        return res;
    }
    
    /**
     * Get a random quest type
     * 
     * @return Random quest type
     */
    public static QuestType getRandomQuestType()
    {
        int ind = Tools.randomInt(0, allTypes.size());
        
        return allTypes.get(ind);
    }
}
