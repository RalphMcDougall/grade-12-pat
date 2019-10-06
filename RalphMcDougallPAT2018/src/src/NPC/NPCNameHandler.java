/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.NPC;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import src.Utility.GameLogger;
import src.Utility.Tools;

/**
 * Loads all of the potential names that NPCs can have from the text file
 * 
 * @author Ralph McDougall 3/6/2018
 */
public class NPCNameHandler {
    
    // *****************************************************
    // PRIVATE FIELDS
    // *****************************************************
    
    // All of the names that the NPCs can have
    private static ArrayList<String> names;
    
    // *****************************************************
    // PUBLIC METHODS
    // *****************************************************
    
    /**
     * Load the NPC names from the text file.
     * 
     * @throws FileNotFoundException The NPC name file could not be found
     */
    public static void load() throws FileNotFoundException
    {
        GameLogger.logInfo("Loading NPC names from file...");
        
        // Initialise the list
        names = new ArrayList<>();
        
        // Open the text file and load the names that appear on each line
        Scanner inFile = new Scanner(new File("src/resources/NPCNames.txt"));
        
        while (inFile.hasNextLine())
        {
            String n = inFile.nextLine();
            names.add(n);
        }
        
        inFile.close();
        
        GameLogger.logInfo("NPC names loaded successfully.");
    }
    
    /**
     * Get the name at the given index in the names list
     * 
     * @param ind The index of the name to find
     * @return The name at the index
     */
    public static String getName(int ind)
    {
        return names.get(ind);
    }
    
    /**
     * Get a random name from the list of names
     * 
     * @return The random name
     */
    public static String getRandomName()
    {
        return getName(Tools.randomInt(0, names.size()));
    }
}
