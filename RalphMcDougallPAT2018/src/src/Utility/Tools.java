/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.Utility;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import src.Main.Constants;

/**
 * Helper methods that can be used throughout the program
 *
 * @author Ralph McDougall 18/4/2018
 */
public class Tools {

    /**
     * Return the current date and time that this method is called in a string
     * format
     *
     * @return String format of the date and time that this is called
     */
    public static String getCurrentDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        Date date = new Date();

        return dateFormat.format(date);
    }

    /**
     * Split a given piece of text into multiple lines of text, none having
     * length greater than the supplied amount. Words should not be split
     * halfway.
     *
     * @param text The text that is to be split
     * @param splitLength The number of characters per line
     * @return A list of the lines of text
     */
    public static ArrayList<String> splitText(String text, int splitLength) {
        ArrayList<String> result = new ArrayList<String>();

        // The text should be split into lines that don't split words
        // so words and lines should be considered separately
        String currentLine = "";
        String currentWord = "";

        for (int i = 0; i < text.length(); ++i) {
            currentWord += text.charAt(i);

            if (text.charAt(i) == ' ') {
                // A new word needs to be made
                if (currentLine.length() + currentWord.length() <= splitLength) {
                    // The new word won't be cut off by a new line
                    currentLine += currentWord;
                    currentWord = "";
                } else {
                    // The word will get cut off so make a new line
                    result.add(currentLine);
                    currentLine = currentWord;
                    currentWord = "";
                }
            }

            // The line is at the maximum length
            if (currentLine.length() == splitLength) {
                result.add(currentLine);
                currentLine = currentWord;
            }
        }

        // Add any remaining characters to the line
        currentLine += currentWord;

        if (currentLine.length() != 0) {
            // There is still a line that needs to be added on
            result.add(currentLine);
        }

        return result;
    }

    /**
     * Get the number of megabytes represented by a given number of bytes
     *
     * @param numBytes The number of bytes to be converted to megabytes
     * @return A string in the format of the number of megabytes represented
     */
    public static String getNumMB(long numBytes) {
        DecimalFormat df = new DecimalFormat();
        df.applyPattern("###,###.###");

        return df.format(numBytes / (1024.0 * 1024));
    }

    /**
     * Get a random integer in the interval [lowerBound, upperBound)
     *
     * @param lowerBound The inclusive lower bound
     * @param upperBound The exclusive upper bound
     * @return The random number in the given interval
     */
    public static int randomInt(int lowerBound, int upperBound) {
        return (int) (Math.random() * (upperBound - lowerBound)) + lowerBound;
    }

    /**
     * Account for any floating point precision errors
     *
     * @param a The number that is to be checked for floating point precision
     * errors
     * @return The new value of a after fixing any precision errors
     */
    public static double fixPrecision(double a) {

        int numValidPrecisions = Constants.TILE_SCREEN_SIZE;

        // The decimal part of a
        double aDec = a - ((int) a);

        // Best approximation
        double newADec = -100;
        
        // Check each fraction in [0, 1] with a given denominator
        for (int i = 0; i <= numValidPrecisions; ++i) {
            double prec = 1.0 * i / numValidPrecisions;

            if (Math.abs(aDec - newADec) > Math.abs(aDec - prec)) {
                // The decimal precision found is more accurate than the
                // previous best approximation
                newADec = prec;
            }
        }

        return ((int) a) + newADec;

    }

    /**
     * Check whether a given number is an integer
     * 
     * @param a The number that needs to be verified
     * @return Whether or not the number is an integer
     */
    public static boolean isInt(double a) {
        // Check if the number is within a small value of a known integer
        return Math.abs(a - (int) a) <= 0.01 || Math.abs(( (int) a ) - a) <= 0.01;
    }

}
