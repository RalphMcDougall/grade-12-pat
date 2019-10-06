/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.UI;

import java.util.ArrayList;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.TrueTypeFont;
import src.Utility.GameClock;
import src.Utility.GameLogger;
import src.Utility.Tools;

/**
 * A text-box that can appear on the screen at any location with a given colour,
 * location and text.
 *
 * @author Ralph McDougall 23/4/2018
 */
public class HUDElement {

    // *****************************************************
    // PUBLIC CLASS FIELDS
    // *****************************************************
    
    // Which part of the parent this element is alligned with
    public static enum REFERENCE {
        TOP_LEFT, TOP_RIGHT, BOTTOM_LEFT, BOTTOM_RIGHT
    };

    // *****************************************************
    // PRIVATE FIELDS
    // *****************************************************
    
    // The HUDElement that this HUDElement uses for relative position on screen
    // null = top-left of the screen
    private HUDElement parentReference = null;

    // The fixed position and dimensions of the block
    private int topY = 0;
    private int leftX = 0;
    private int width = 0;
    private int height = 0;

    // Whether or not the element is visible
    private boolean visible = true;

    // How long the message being displayed must be locked for
    private int lockTime = 0;
    private long lockStartTime = 0;

    // The colours of the background box and the text
    private Color bgColor = Color.white;
    private Color fontColor = Color.black;

    // This contains each line of text to be displayed
    private ArrayList<String> text;

    // The maximum number of characters per line to stop characters appearing
    // outside of the box
    private int MAX_LINE_LENGTH;

    // *****************************************************
    // CONSTRUCTOR
    // *****************************************************
    
    /**
     * Default constructor
     */
    public HUDElement() {
        // Null constructor
    }

    /**
     * Initialise a new HUDElement object with a given size, location, text and
     * colour
     *
     * @param parent The HUDElement that this one should align itself with
     * @param reference The reference point of the parent to use to align this
     * object
     * @param xOffset The x-offset from the reference point
     * @param yOffset The y-offset from the reference point
     * @param blockWidth The width of the block on the screen
     * @param blockHeight The height of the block on the screen
     * @param backgroundColor The background colour of the text box
     * @param textDisplay The line of text to be displayed on the text box
     * @param textColor The colour of the text to be displayed
     */
    public HUDElement(HUDElement parent, REFERENCE reference,
            int xOffset, int yOffset, int blockWidth, int blockHeight,
            Color backgroundColor, String textDisplay, Color textColor) {
        parentReference = parent;
        width = blockWidth;
        height = blockHeight;

        // Align the new element with its parent
        switch (reference) {
            case TOP_LEFT:
                topY = parent.getTopY() + yOffset;
                leftX = parent.getLeftX() + xOffset;
                break;
            case TOP_RIGHT:
                topY = parent.getTopY() + yOffset;
                leftX = parent.getRightX() + xOffset;
                break;
            case BOTTOM_LEFT:
                topY = parent.getBottomY() + yOffset;
                leftX = parent.getLeftX() + xOffset;
                break;
            case BOTTOM_RIGHT:
                topY = parent.getBottomY() + yOffset;
                leftX = parent.getRightX() + xOffset;
                break;
            default:
                // Unknown reference point
                GameLogger.logError("Unknown reference point given for "
                        + "HUDElement.\nReference: " + reference);
                break;
        }

        bgColor = backgroundColor;
        fontColor = textColor;

        // Determine the maximum number of characters in a line
        MAX_LINE_LENGTH = (width - 10) / 12 + 3;

        // Turn the line of text into multiple lines that can be displayed
        text = Tools.splitText(textDisplay, MAX_LINE_LENGTH);

    }

    // *****************************************************
    // PUBLIC METHODS
    // *****************************************************
    
    /**
     * Get the y-coordinate of the top of the box
     *
     * @return The y-coordinate of the top of the box
     */
    public int getTopY() {
        return topY;
    }

    /**
     * Get the y-coordinate of the bottom of the box
     *
     * @return The y-coordinate of the bottom of the box
     */
    public int getBottomY() {
        return topY + height;
    }

    /**
     * Get the x-coordinate of the left of the box
     *
     * @return The x-coordinate of the left of the box
     */
    public int getLeftX() {
        return leftX;
    }

    /**
     * Get the x-coordinate of the right of the box
     *
     * @return The x-coordinate of the right of the box
     */
    public int getRightX() {
        return leftX + width;
    }

    /**
     * Draw the HUDElement text box onto the screen
     *
     * @param g The Graphics object to draw the HUDElement onto
     * @param font The font to use to draw the text
     */
    public void draw(Graphics g, TrueTypeFont font) {
        if (!visible) {
            // Don't draw anything, it isn't visible
            return;
        }

        // Draw the background box
        g.setColor(bgColor);
        g.fillRect(leftX, topY, width, height);

        // Draw the text
        int yOff = 0;
        for (String s : text) {
            font.drawString(leftX + 5, topY + 5 + yOff, s, fontColor);
            yOff += 22;
        }
    }

    /**
     * Set the text to be displayed on the text box
     *
     * @param newText The line of text to be displayed
     */
    public void setText(String newText) {
        if (locked()) {
            // It has been locked so nothing can change
            return;
        }

        // Split the new text up into as many lines as necessary
        text = Tools.splitText(newText, MAX_LINE_LENGTH);
    }

    /**
     * Get the current text being displayed on the text box
     *
     * @return A line of text containing everything being displayed
     */
    public String getText() {
        String result = "";

        // Join the text
        for (String s : text) {
            result += s;
        }

        return result;
    }

    /**
     * Set whether or not this HUDElement should be displayed on the screen
     *
     * @param vis Whether or not the HUDElement is visible
     */
    public void setVisible(boolean vis) {
        if (locked()) {
            // It has been locked, so nothing can change
            return;
        }
        visible = vis;
    }

    /**
     * Lock the HUDElement so that no changes in text displayed or visibility
     * can be made for a given amount of time
     *
     * @param time The time in milliseconds to lock the display
     */
    public void lockDisplay(int time) {
        lockTime = time;
        lockStartTime = GameClock.getMilliTime();
    }

    /**
     * Get whether or not the HUDElement is currently locked
     *
     * @return Whether or not the HUDElement is currently locked
     */
    public boolean locked() {
        return GameClock.getMilliTime() - lockStartTime < lockTime;
    }
}
