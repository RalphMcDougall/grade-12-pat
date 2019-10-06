/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.Utility;

/**
 * This class keeps the time at the start of an update cycle.
 * This ensures that all times used by elements of the game are the same
 * in a given update cycle.
 * 
 * @author Ralph McDougall 2/8/2018
 */
public class GameClock {
    // *****************************************************
    // PRIVATE FIELDS
    // *****************************************************
    
    // The system time of the start of the last update cycle in nanoseconds
    private static long currentTime = 0;
    
    // *****************************************************
    // PUBLIC METHODS
    // *****************************************************
    
    /**
     * Set the current time to the system time in nanoseconds at this point
     */
    public static void updateTime()
    {
        currentTime = System.nanoTime();
    }
    
    /**
     * Get the time in nanoseconds of the last update cycle start
     * 
     * @return The time in nanoseconds
     */
    public static long getNanoTime()
    {
        return currentTime;
    }
    
    /**
     * Get the time in milliseconds of the last update cycle start
     * 
     * @return The time in milliseconds
     */
    public static long getMilliTime()
    {
        return currentTime / 1000000;
    }
    
}
