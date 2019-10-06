/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.Player;

import src.Quest.QuestHandler;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import src.Utility.DBBridge;
import src.Main.GameHandler;
import src.Utility.GameLogger;

/**
 * This class handles all of the statistics of the currently loaded user.
 *
 * @author Ralph McDougall 25/6/2018
 */
public class User {
    // *****************************************************
    // PRIVATE FIELDS
    // *****************************************************
    
    // Statistics and properties of the user
    private String name = "";
    private int id = 0;
    private int xp = 0;
    private int health = 0;
    private int damage = 0;
    private int fireRate = 0;
    private int speed = 0;
    private int numDeaths = 0;
    private int numKills = 0;
    private int numQuestsCompleted = 0;
    private int numTimesHealthUpgraded = 0;
    private int numTimesDamageUpgraded = 0;
    private int numTimesFireRateUpgraded = 0;
    private int numTimesSpeedUpgraded = 0;
    
    // *****************************************************
    // CONSTRUCTOR
    // *****************************************************
    
    /**
     * Default constructor
     */
    public User()
    {
        // Empty constructor
    }
    
    /**
     * Initialise a new user with given statistics
     * 
     * @param userName Name of the user
     * @param userHealth Health of the user
     * @param userDamage Damage of the user
     * @param userFireRate Fire rate of the user
     * @param userSpeed Speed of the user
     * @throws SQLException User data couldn't be added to the database
     */
    public User(String userName, int userHealth, int userDamage, int userFireRate, int userSpeed) throws SQLException
    {
        name = userName;
        health = userHealth;
        damage = userDamage;
        fireRate = userFireRate;
        speed = userSpeed;
        
        // Add this new user to the database
        GameLogger.logInfo("Inserting new user into database.");
        DBBridge db = GameHandler.getPersistentDB();
        
        String insert = "INSERT INTO tblUsers(UserName, LastPlay, Health, Damage, FireRate, Speed) VALUES (\"" + name + "\", NOW(), " + health + ", " + damage + ", " + fireRate + ", " + speed + ");";
        
        db.update(insert);
        
        // Get the ID of the new user
        String query = "SELECT MAX(UserID) FROM tblUsers;";
        id = Integer.parseInt(DBBridge.processResultSet(db.query(query)).get(0).get(0));
        
        // Load the quests for this new user
        loadQuests();
        
        GameLogger.logInfo("User successfully added to database.");
    }
    
    /**
     * Initialise a new user with a given user ID
     * 
     * @param userID The ID of th user to load
     * @throws SQLException SQL couldn't load the user's data
     */
    public User (int userID) throws SQLException
    {
        // Get the user data
        
        GameLogger.logInfo("Getting data for user with ID: " + userID);
        
        id = userID;
        
        // Get the user data from the data where the ID is the given ID
        DBBridge db = GameHandler.getPersistentDB();
        String query = "SELECT UserName, XP, Health, Damage, FireRate, Speed, NumDeaths, NumKills, NumQuestsCompleted, NumTimesHealthUpgraded, NumTimesDamageUpgraded, NumTimesFireRateUpgraded, NumTimesSpeedUpgraded FROM tblUsers WHERE UserID = " + id + ";";
        ResultSet rs = db.query(query);
        
        ArrayList<String> data = DBBridge.processResultSet(rs).get(0);
        
        name = data.get(0);
        xp = Integer.parseInt(data.get(1));
        health = Integer.parseInt(data.get(2));
        damage = Integer.parseInt(data.get(3));
        fireRate = Integer.parseInt(data.get(4));
        speed = Integer.parseInt(data.get(5));
        numDeaths = Integer.parseInt(data.get(6));
        numKills = Integer.parseInt(data.get(7));
        numQuestsCompleted = Integer.parseInt(data.get(8));
        numTimesHealthUpgraded = Integer.parseInt(data.get(9));
        numTimesDamageUpgraded = Integer.parseInt(data.get(10));
        numTimesFireRateUpgraded = Integer.parseInt(data.get(11));
        numTimesSpeedUpgraded = Integer.parseInt(data.get(12));
        
        // Load the quests for the user
        loadQuests();
        
        GameLogger.logInfo("User data successfully retrieved.");
    }
    
    // *****************************************************
    // PUBLIC METHODS
    // *****************************************************
    
    /**
     * Update the statistics and properties of the current user in the database
     * 
     * @throws SQLException Couldn't update the values in the database
     */
    public void updateUserDBData() throws SQLException
    {
        GameLogger.logInfo("Updating the users statistic data.");
        
        // Update the database
        DBBridge db = GameHandler.getPersistentDB();
        String update = "UPDATE tblUsers SET LastPlay = NOW(), XP = " + xp + ", Health = " + health + ", Damage = " + damage + ", FireRate = " + fireRate + ", Speed = " + speed + ", NumDeaths = " + numDeaths + ", NumKills = " + numKills + ", NumQuestsCompleted = " + numQuestsCompleted + ", numTimesHealthUpgraded = " + numTimesHealthUpgraded + ", numTimesDamageUpgraded = " + numTimesDamageUpgraded + ", numTimesFireRateUpgraded = " + numTimesFireRateUpgraded + ", numTimesSpeedUpgraded = " + numTimesSpeedUpgraded + " WHERE UserID = " + id + ";";      
        db.update(update);
        
        GameLogger.logInfo("User statistic data updated.");
    }
    
    /**
     * Load all of the player's quests from the database
     * 
     * @throws SQLException Quests couldn't be loaded from the database
     */
    public void loadQuests() throws SQLException
    {
        // Reset the quest handler
        QuestHandler.initialiseQuestHandler();
        
        // Load the quests of the user with the current ID
        QuestHandler.loadQuests(id);
    }

    /**
     * Get the name of the user
     * 
     * @return User's name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Set the name of the user
     * 
     * @param name New name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the ID of the user
     * 
     * @return User's ID
     */
    public int getId() {
        return id;
    }
    
    /**
     * Set the ID of the user
     * 
     * @param id New ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get the XP of the user
     * 
     * @return User's XP
     */
    public int getXp() {
        return xp;
    }

    /**
     * Set the XP of the user
     * 
     * @param xp New XP
     */
    public void setXp(int xp) {
        this.xp = xp;
    }

    /**
     * Get the health of the user
     * 
     * @return User's health
     */
    public int getHealth() {
        return health;
    }

    /**
     * Set the health of the user
     * 
     * @param health New health
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * Get the damage of the user
     * 
     * @return User's damage
     */
    public int getDamage() {
        return damage;
    }

    /**
     * Set the damage of the user
     * 
     * @param damage New damage
     */
    public void setDamage(int damage) {
        this.damage = damage;
    }

    /**
     * Get the fire rate of the user
     * 
     * @return User's fire rate
     */
    public int getFireRate() {
        return fireRate;
    }

    /**
     * Set the fire rate of the user
     * 
     * @param fireRate New fire rate
     */
    public void setFireRate(int fireRate) {
        this.fireRate = fireRate;
    }

    /**
     * Get the speed of the user
     * 
     * @return User's speed
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * Set the speed of the user
     * 
     * @param speed New speed
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /**
     * Get the number of times the user has died
     * 
     * @return User's death count
     */
    public int getNumDeaths() {
        return numDeaths;
    }
    
    /**
     * Set the number of deaths of the user
     * 
     * @param numDeaths New number of deaths
     */
    public void setNumDeaths(int numDeaths) {
        this.numDeaths = numDeaths;
    }

    /**
     * Get the number of enemies killed by the user
     * 
     * @return User's kill count
     */
    public int getNumKills() {
        return numKills;
    }

    /**
     * Set the number of kills of the user
     * 
     * @param numKills New number of kills
     */
    public void setNumKills(int numKills) {
        this.numKills = numKills;
    }

    /**
     * Get the number of quests completed by the user
     * 
     * @return Number of quests completed
     */
    public int getNumQuestsCompleted() {
        return numQuestsCompleted;
    }

    /**
     * Set the number of quests completed of the user
     * 
     * @param numQuestsCompleted New number of quests completed
     */
    public void setNumQuestsCompleted(int numQuestsCompleted) {
        this.numQuestsCompleted = numQuestsCompleted;
    }

    /**
     * Get the number of times the health has been upgraded
     * 
     * @return Number of times health was upgraded
     */
    public int getNumTimesHealthUpgraded() {
        return numTimesHealthUpgraded;
    }
    
    /**
     * Set the number of times health has been upgraded of the user
     * 
     * @param numTimesHealthUpgraded New number of times health upgraded
     */
    public void setNumTimesHealthUpgraded(int numTimesHealthUpgraded) {
        this.numTimesHealthUpgraded = numTimesHealthUpgraded;
    }

    /**
     * Get the number of times the damage has been upgraded
     * 
     * @return Number of times damage was upgraded
     */
    public int getNumTimesDamageUpgraded() {
        return numTimesDamageUpgraded;
    }

    /**
     * Set the number of times damage has been upgraded of the user
     * 
     * @param numTimesDamageUpgraded New number of times damage upgraded
     */
    public void setNumTimesDamageUpgraded(int numTimesDamageUpgraded) {
        this.numTimesDamageUpgraded = numTimesDamageUpgraded;
    }

    /**
     * Get the number of times the fire rate has been upgraded
     * 
     * @return Number of times fire rate was upgraded
     */
    public int getNumTimesFireRateUpgraded() {
        return numTimesFireRateUpgraded;
    }

    /**
     * Set the number of times fire rate has been upgraded of the user
     * 
     * @param numTimesFireRateUpgraded New number of times fire rate upgraded
     */
    public void setNumTimesFireRateUpgraded(int numTimesFireRateUpgraded) {
        this.numTimesFireRateUpgraded = numTimesFireRateUpgraded;
    }

    /**
     * Get the number of times the speed has been upgraded
     * 
     * @return Number of times speed was upgraded
     */
    public int getNumTimesSpeedUpgraded() {
        return numTimesSpeedUpgraded;
    }

    /**
     * Set the number of times speed has been upgraded of the user
     * 
     * @param numTimesSpeedUpgraded New number of times speed upgraded
     */
    public void setNumTimesSpeedUpgraded(int numTimesSpeedUpgraded) {
        this.numTimesSpeedUpgraded = numTimesSpeedUpgraded;
    }
}
