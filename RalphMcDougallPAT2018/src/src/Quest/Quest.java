/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.Quest;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import src.GUI.GameScreen;
import src.Utility.DBBridge;
import src.Main.GameHandler;
import src.Utility.GameLogger;
import src.UI.HUDLayer;
import src.Utility.Tools;
import src.Player.User;

/**
 * Stores the details each quest. This allows the progress to be monitored
 * and the target to be tracked.
 *
 * @author Ralph McDougall 26/6/2018
 */
public class Quest {
    // *****************************************************
    // PRIVATE FIELDS
    // *****************************************************
    
    // Properties of the quest
    private int questID;
    private String issuerName;
    private int questTypeID;
    private String target;
    private int desiredTargetCount;
    private String questTypeDescription;
    private int progress;
    private int reward;
    
    // Whether or not the quest has been completed
    private boolean completed = false;
    
    // *****************************************************
    // CONSTRUCTOR
    // *****************************************************
    
    /**
     * Initialise the quest that has the given ID from the database
     * 
     * @param id The ID of the quest to get
     * @throws SQLException SQL couldn't retrieve the quest from the database
     */
    public Quest(int id) throws SQLException
    {
        GameLogger.logInfo("Construct quest with id: " + id);
        
        // Get the quest with a given ID
        DBBridge db = GameHandler.getPersistentDB();
        String query = "SELECT IssuerName, QuestType, QuestTargetDescription, DesiredTargetCount, QuestProgress, Reward FROM tblActiveQuests WHERE QuestID = " + id + ";";
        
        ResultSet rs = db.query(query);
        ArrayList<String> data = DBBridge.processResultSet(rs).get(0);
        
        // Initialise all of the properties
        questID =               id;
        issuerName =            data.get(0);
        questTypeID =           Integer.parseInt(data.get(1));
        questTypeDescription =  QuestTypeHandler.getQuestType(questTypeID).getDescriptor();
        target =                data.get(2);
        desiredTargetCount =    Integer.parseInt(data.get(3));
        progress =              Integer.parseInt(data.get(4));
        reward =                Integer.parseInt(data.get(5));
    }
    
    /**
     * Initialise a new quest with a given issuer name
     * 
     * @param issuer 
     * @throws SQLException 
     */
    public Quest(String issuer) throws SQLException
    {
        // Construct a new quest
        
        getNewQuestID();
        
        issuerName = issuer;
        
        // Get a random quest type
        QuestType q = QuestTypeHandler.getRandomQuestType();
        questTypeID = q.getID();
        questTypeDescription = q.getDescriptor();
        
        // Get a random target for the quest
        QuestTarget qt = q.getRandomTarget();
        target = qt.getTarget();
        desiredTargetCount = Tools.randomInt(1, qt.getMaxCount() + 1);
        
        // Get a random reward for the quest completion
        reward = (int) (1.0 * desiredTargetCount / qt.getMaxCount() * qt.getMaxReward());
        
    }
    
    // *****************************************************
    // PRIVATE METHODS
    // *****************************************************
    
    /**
     * Determine the next quest ID by looking at the maximum quest ID in use
     * @throws SQLException 
     */
    private void getNewQuestID() throws SQLException
    {
        GameLogger.logInfo("Getting new quest ID.");
        
        // The new quest ID is one more than the previous maximum ID
        DBBridge db = GameHandler.getPersistentDB();
        String query = "SELECT MAX(QuestID) + 1 FROM tblActiveQuests";
        ResultSet rs = db.query(query);
        try
        {
            // Get the new quest ID
            questID = Integer.parseInt(DBBridge.processResultSet(rs).get(0).get(0));
        }
        catch (NumberFormatException e)
        {
            // There are no quests yet
            questID = 1;
        }
    }
    
    // *****************************************************
    // PUBLIC METHODS
    // *****************************************************
    
    /**
     * Increase the number of targets gotten by 1
     */
    public void increaseProgress()
    {
        // Incrase the progress of the quest and check if it has been completed
        GameLogger.logInfo("Increasing progress on quest: " + questID + " to " + (progress + 1) + " / " + desiredTargetCount + ".");
        progress++;
        
        if (progress == desiredTargetCount)
        {
            // The quest has been completed
            completed = true;
            
            // Increase the user's XP
            User user = GameHandler.getUser();
            user.setXp(user.getXp() + reward);
            
            // Increase the number of quests completed by the user
            user.setNumQuestsCompleted(user.getNumQuestsCompleted() + 1);
            
            
            // Inform the user that the quest has been completed
            HUDLayer hud = GameScreen.getHUDLayer();
            hud.changeMessageBoxDisplay("Quest completed! You received " + reward + " XP.");
            hud.changeMessageBoxVisibility(true);
            hud.lockMessageBoxDisplay(3);
        }
    }
    
    /**
     * Update the database with the current quest progress and delete it if it
     * has been completed
     * 
     * @throws SQLException SQL could not communicate with the database
     */
    public void updateDB() throws SQLException
    {
        // Get the database and update it as required
        DBBridge db = GameHandler.getPersistentDB();
        
        if (completed)
        {
            // Remove the quest from the database since it has been completed
            String delete = "DELETE * FROM tblActiveQuests WHERE QuestID = " + questID + ";";
            db.update(delete);
        }
        else
        {
            // Update the values of the quest in the database
            String update = "UPDATE tblActiveQuests SET QuestProgress = " + progress + " WHERE QuestID = " + questID + ";";
            
            db.update(update);
        }
    }
    
    /**
     * Insert the new quest into the database
     * 
     * @throws SQLException SQL could not communicate with the database
     */
    public void insertQuestToDB() throws SQLException
    {
        // Insert the new quest into the database
        GameLogger.logInfo("Inserting new quest into database.");
        DBBridge db = GameHandler.getPersistentDB();
        String insert = "INSERT INTO tblActiveQuests(QuestID, UserID, IssuerName, QuestType, QuestTargetDescription, DesiredTargetCount, QuestProgress, Reward) VALUES (" + questID + ", " + GameHandler.getUser().getId() + ", '" + issuerName + "', " + questTypeID + ", '" + target + "', " + desiredTargetCount + ", " + progress + ", " + reward + ")";
        db.update(insert);
    }
    
    /**
     * Get the text to display that describes this quest.
     * 
     * @return Quest description text
     */
    public String getQuestText()
    {
        String result = "";
        result += issuerName + ": ";
        result += "If you ";
        result += questTypeDescription + " ";
        result += desiredTargetCount + " ";
        result += target;
        if (desiredTargetCount > 1)
        {
            result += "s";
        }
        
        result += ", I'll give you ";
        result += reward + " ";
        result += "XP.";
        
        return result;
    }
    
    /**
     * Get the name of the target of this quest.
     * 
     * @return Quest target name
     */
    public String getTarget() {
        return target;
    }

    /**
     * Get the number of targets to find for the quest to be completed.
     * 
     * @return Quest desired target count
     */
    public int getDesiredTargetCount() {
        return desiredTargetCount;
    }

    /**
     * Get a description of the type of quest that this is.
     * 
     * @return Quest type description
     */
    public String getQuestTypeDescription() {
        return questTypeDescription;
    }

    /**
     * Get the progress of the player for this quest.
     * 
     * @return Quest progress
     */
    public int getProgress() {
        return progress;
    }

    /**
     * Get the reward that will be given to the player upon completing
     * this quest.
     * 
     * @return Quest completion reward.
     */
    public int getReward() {
        return reward;
    }
    
    /**
     * Get whether or not this quest has been completed yet.
     * 
     * @return Quest completion state
     */
    public boolean completed()
    {
        return completed;
    }
}
