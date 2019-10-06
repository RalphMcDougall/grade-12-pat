/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.Utility;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import src.Main.GameHandler;

/**
 * The class that logs all activities and writes it to a file.
 *
 * This is mainly for debugging during development. It can also provide the user
 * details of what went wrong if they experience problems.
 *
 * @author Ralph McDougall 18/4/2018
 */
public class GameLogger {
    // *****************************************************
    // PUBLIC CLASS CONSTANTS
    // *****************************************************

    // The different priority levels of messages
    public static enum LEVEL {
        ERROR, WARNING, INFO
    };

    // *****************************************************
    // PRIVATE CLASS FIELDS
    // *****************************************************
    
    // The name of the file being written to
    private static String fileName = "";
    private static String path = "src/logs/";
    private static String extension = ".txt";

    // The number of times to add messages before flushing to the file
    private static int flushFrequency = 20;
    private static int currentNumMessages = 0;

    // The list containing all messages up to this point
    private static ArrayList<String> log = new ArrayList<String>();

    // The current priority level that causes system output
    private static LEVEL flagLevel = LEVEL.INFO;

    // *****************************************************
    // PRIVATE CLASS METHODS
    // *****************************************************
    
    /**
     * Log a message with a given level of importance
     *
     * @param importance The priority of the new message
     * @param msg The message to be logged
     */
    private static void log(LEVEL importance, String msg) {
        // Log a message to the list of all messages
        boolean display = false;

        // Check whether or not it is necessary to display
        // the message on standard output
        switch (flagLevel) {
            case ERROR:
                // If the priority level is "ERROR", then only error messages
                // should be displayed
                if (importance == LEVEL.ERROR) {
                    display = true;
                }
                break;
            case WARNING:
                // If the priority level is "WARNING", then error messages
                // and warning messages should be displayed
                if (importance == LEVEL.WARNING || importance == LEVEL.ERROR) {
                    display = true;
                }
                break;
            case INFO:
                // If the priority level is "INFO", then all messages
                // should be displayed
                if (importance == LEVEL.INFO || importance == LEVEL.WARNING
                        || importance == LEVEL.ERROR) {
                    display = true;
                }
                break;
            default:
                // Unknown flag
                System.out.println("Unknown flag: " + flagLevel);
                break;
        }

        // Find the appropriate prefix for the message
        String lvl = "";
        switch (importance) {
            case ERROR:
                lvl = "ERROR";
                break;
            case WARNING:
                lvl = "WARNING";
                break;
            case INFO:
                lvl = "INFO";
                break;
            default:
                lvl = "*UNKNOWN PRIORITY LEVEL*";
                break;
        }

        // Construct the new message
        String logMsg = "<" + Tools.getCurrentDateTime() + "> " + lvl
                + " : " + msg;

        if (display) {
            // Display the message to standard output
            System.out.println(logMsg);
        }

        // Log the error message
        log.add(logMsg);
        currentNumMessages++;

        if (currentNumMessages % flushFrequency == 0) { // Flush to the file at regular intervals
            flush();
        }
    }

    // *****************************************************
    // PUBLIC CLASS FIELDS
    // *****************************************************
    
    /**
     * Set the name of the file that is being written to
     *
     * @param name The name of the file being written to
     */
    public static void setFileName(String name) {
        fileName = name;
        try {
            // Make the new file
            PrintWriter writer = new PrintWriter(path + fileName + extension,
                    "UTF-8");
            writer.close();
        } catch (IOException ex) {
            // There was a problem setting the file
            JOptionPane.showMessageDialog(null, "The file " + (path
                    + fileName + extension) + " could not be created!\n");
            System.out.println(ex);

            // No point in continuing
            GameHandler.exit();
        }
    }

    /**
     * Log an "INFO" level message
     *
     * @param s The message to log
     */
    public static void logInfo(String s) {
        log(LEVEL.INFO, s);
    }

    /**
     * Log an "WARNING" level message
     *
     * @param s The message to log
     */
    public static void logWarning(String s) {
        log(LEVEL.WARNING, s);
    }

    /**
     * Log an "ERROR" level message
     *
     * @param s The message to log
     */
    public static void logError(String s) {
        log(LEVEL.ERROR, s);

        // Flush to the file after an error is detected
        flush();
    }

    /**
     * Set the priority flag level
     *
     * @param lvl The new flag level
     */
    public static void setFlagLevel(LEVEL lvl) {
        flagLevel = lvl;
    }

    /**
     * Get the priority flag level
     *
     * @return The flag level
     */
    public static LEVEL getFlagLevel() {
        return flagLevel;
    }

    /**
     * Flush the messages to a text file
     */
    public static void flush() {
        // Construct the message for the output file
        String output = "";
        for (int i = 0; i < log.size(); ++i) {
            output += log.get(i) + "\r\n";
        }
        try {
            // Attempt to write to the file
            BufferedWriter writer = new BufferedWriter(new FileWriter(path
                    + fileName + extension));
            writer.write(output);
            writer.close();
        } catch (IOException e) {
            // The file was unavailable
            JOptionPane.showMessageDialog(null, "Unable to flush to "
                    + "file.\n" + e.toString());
            System.out.println("Unable to flush logger to file :(");
        }
    }
    
    /**
     * Set a new frequency for which the log must be flushed
     * 
     * @param newFreq The new frequency to flush to the file
     */
    public static void setFlushFrequency(int newFreq) {
        flushFrequency = newFreq;
    }

}
