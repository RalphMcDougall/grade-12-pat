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
 * Stores the descriptions of quest types as well as all targets of the quest type
 *
 * @author Ralph McDougall 26/6/2018
 */
public class QuestType {
    // *****************************************************
    // PRIVATE FIELDS
    // *****************************************************
    
    // ID of the quest type
    private int id;
    
    // Whether the quest type is for "finding" or "killing"
    private String descriptor;
    
    // This stores the target description and the maximum count
    private ArrayList<QuestTarget> targets;
    
    // *****************************************************
    // CONSTRUCTOR
    // *****************************************************
    
    /**
     * Initialise a new quest type with a given ID and descriptor
     * 
     * @param _id The ID of the quest type
     * @param _descriptor The description of the quest type
     * @throws SQLException SQL couldn't load the quest types
     */
    public QuestType(int _id, String _descriptor) throws SQLException
    {
        id = _id;
        descriptor = _descriptor;
        
        loadTargets();
    }
    
    // *****************************************************
    // PRIVATE METHODS
    // *****************************************************
    
    /**
     * Load all targets of this given quest type from the database
     * 
     * @throws SQLException SQL couldn't load the targets of this quest type 
     */
    private void loadTargets() throws SQLException
    {
        GameLogger.logInfo("Load the targets of the quest type from the database.");
        targets = new ArrayList<>();
        
        // Get the targets
        DBBridge db = GameHandler.getPersistentDB();
        String query = "SELECT TargetDescription, MaxTargetCount, MaxReward FROM tblQuestTargets WHERE QuestTypeID = " + id + ";";
        ResultSet rs = db.query(query);
        
        ArrayList< ArrayList<String> > data = DBBridge.processResultSet(rs);
        
        // Add all of the targets to the list
        for (ArrayList<String> record : data)
        {
            targets.add(new QuestTarget(record.get(0), Integer.parseInt(record.get(1)), Integer.parseInt(record.get(2))));
        }
    }

    // *****************************************************
    // PUBLIC METHODS
    // *****************************************************
    
    /**
     * Get the quest type ID
     * 
     * @return Quest type ID
     */
    public int getID()
    {
        return id;
    }
    
    /**
     * Get the descriptor for this quest type
     * 
     * @return Quest type descriptor
     */
    public String getDescriptor()
    {
        return descriptor;
    }
    
    /**
     * Get a random target of this quest type
     * 
     * @return Random quest type target
     */
    public QuestTarget getRandomTarget()
    {
        int ind = Tools.randomInt(0, targets.size());
        
        return targets.get(ind);
    }
    
}
