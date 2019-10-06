/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.Map;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import src.Main.Constants;
import src.Utility.GameLogger;
import src.Utility.Tools;

/**
 * Loads all map segments given the formats provided by the text file
 * 
 * @author Ralph McDougall 14/6/2018
 */
public class MapSegmentHandler {
    // *****************************************************
    // PRIVATE FIELDS
    // *****************************************************
    
    // The list of all map segments in the text file
    private static ArrayList<MapSegment> allSegments;
    
    // *****************************************************
    // PUBLIC METHODS
    // *****************************************************
    
    /**
     * Load the map segments from the text file
     * 
     * @throws FileNotFoundException The file could not be opened
     */
    public static void load() throws FileNotFoundException
    {
        GameLogger.logInfo("Loading map segments from file...");
        allSegments = new ArrayList<MapSegment>();
        
        // Open the text file containing the map tile segment formats
        Scanner inFile = new Scanner(new File("src/resources/MapSegments.txt"));
        
        // Add each format to the list
        while (inFile.hasNextLine())
        {
            int[][] arr = new int[Constants.TILE_SEGMENT_WIDTH][Constants.TILE_SEGMENT_WIDTH];
            
            // Get each tile type in the grid
            for (int i = 0; i < Constants.TILE_SEGMENT_WIDTH; ++i)
            {
                String line = inFile.nextLine();
                String[] sLine = line.split(" ");
                for (int j = 0; j < Constants.TILE_SEGMENT_WIDTH; ++j)
                {
                    arr[i][j] = Integer.parseInt(sLine[j]);
                }
            }
            
            // Add the new map tile segment to the list of all segments
            allSegments.add(new MapSegment(arr));
            
            // The next line is blank, temporary variable to skip that line
            String blankLine = inFile.nextLine();
        }
        
        inFile.close();
        
        GameLogger.logInfo("Map segments loaded successfully.");
    }
    
    /**
     * Get a random map tile segment from the list of all map tile segments
     * 
     * @return A random map tile segment
     */
    public static MapSegment getRandomSegment()
    {
        return allSegments.get(Tools.randomInt(0, allSegments.size()));
    }
}
