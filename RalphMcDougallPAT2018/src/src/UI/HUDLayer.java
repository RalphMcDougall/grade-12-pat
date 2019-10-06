/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.UI;

import java.awt.Font;
import java.util.ArrayList;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.TrueTypeFont;
import src.Main.Constants;
import src.Map.Map;
import src.Utility.GameLogger;

/**
 * Keep track of all of the Heads-Up-Display elements that could be displayed on
 * the screen.
 *
 * @author Ralph McDougall 23/4/2018
 */
public class HUDLayer {
    // *****************************************************
    // PRIVATE FIELDS
    // *****************************************************

    // Top-left corner of the screen
    private static final HUDElement NULL_POINT = new HUDElement();

    // All elements that need to be drawn to the screen
    private ArrayList<HUDElement> elements = new ArrayList<>();

    // The player stat boxes
    private HUDElement HEALTH_DISPLAY;
    private HUDElement DAMAGE_DISPLAY;
    private HUDElement FIRE_RATE_DISPLAY;
    private HUDElement SPEED_DISPLAY;

    // The message box informing the player of new items and quests
    private HUDElement MESSAGE_BOX;

    // The box to show when the player dies
    private HUDElement GAME_OVER_DISPLAY;

    // The font used to display the text
    private TrueTypeFont DISPLAY_FONT;

    // The minimap that needs to be displayed at the top-left corner
    private Minimap minimap;

    // *****************************************************
    // CONSTRUCTOR
    // *****************************************************
    
    /**
     * Initialise a new HUDLayer object and use the given map to set the minimap
     *
     * @param map The map to base the minimap off of
     */
    public HUDLayer(Map map) {
        GameLogger.logInfo("Creating HUD layer.");

        elements = new ArrayList<>();

        // Initialise the font
        DISPLAY_FONT = new TrueTypeFont(new Font("Arial", Font.PLAIN, 22), true);

        // Create the HUD elements
        HEALTH_DISPLAY = new HUDElement(NULL_POINT,
                HUDElement.REFERENCE.TOP_RIGHT, 30, 30, 150, 40, Color.green,
                "Health: 100", Color.white);
        elements.add(HEALTH_DISPLAY);

        DAMAGE_DISPLAY = new HUDElement(HEALTH_DISPLAY, HUDElement.REFERENCE.TOP_RIGHT,
                20, 0, 150, 40, Color.green,
                "Damage: 20", Color.white);
        elements.add(DAMAGE_DISPLAY);

        FIRE_RATE_DISPLAY = new HUDElement(DAMAGE_DISPLAY, HUDElement.REFERENCE.TOP_RIGHT,
                20, 0, 150, 40, Color.green,
                "Fire Rate: 8", Color.white);
        elements.add(FIRE_RATE_DISPLAY);

        SPEED_DISPLAY = new HUDElement(FIRE_RATE_DISPLAY, HUDElement.REFERENCE.TOP_RIGHT,
                20, 0, 150, 40, Color.green,
                "Speed: 3", Color.white);
        elements.add(SPEED_DISPLAY);

        GAME_OVER_DISPLAY = new HUDElement(NULL_POINT, HUDElement.REFERENCE.TOP_RIGHT,
                Constants.SCREEN_WIDTH / 2 - 75, Constants.SCREEN_HEIGHT / 2 - 20, 150, 40, Color.red,
                "YOU DIED!", Color.white);
        elements.add(GAME_OVER_DISPLAY);
        GAME_OVER_DISPLAY.setVisible(false);

        int msgBoxWidth = 600;
        int msgBoxHeight = 100;
        MESSAGE_BOX = new HUDElement(NULL_POINT, HUDElement.REFERENCE.TOP_RIGHT,
                Constants.SCREEN_WIDTH / 2 - msgBoxWidth / 2, Constants.SCREEN_HEIGHT - msgBoxHeight - 50, msgBoxWidth, msgBoxHeight, Color.black,
                "", Color.white);
        elements.add(MESSAGE_BOX);
        
        setMinimap(map);

    }

    /**
     * Display the HUD on the screen
     * 
     * @param g The Graphics object to draw the HUD onto
     */
    public void display(Graphics g) {
        // Draw each HUDElement
        for (HUDElement e : elements) {
            e.draw(g, DISPLAY_FONT);
        }

        // Draw the minimap
        minimap.draw(g);
    }

    /**
     * Set the text to be displayed on the message box
     * 
     * @param text The text to be displayed on the message box
     */
    public void changeMessageBoxDisplay(String text) {
        MESSAGE_BOX.setText(text);
    }

    /**
     * Set whether or not the message box should be drawn onto the screen
     * 
     * @param vis Whether or not the message box should be drawn onto the screen
     */
    public void changeMessageBoxVisibility(boolean vis) {
        MESSAGE_BOX.setVisible(vis);
    }

    /**
     * Lock the message box for a given amount of time (in seconds)
     * 
     * @param seconds The number of seconds to lock the display for
     */
    public void lockMessageBoxDisplay(double seconds) {
        MESSAGE_BOX.lockDisplay((int) seconds * 1000);
    }

    /**
     * Change the text to be displayed in the health text box
     * 
     * @param text The new health text
     */
    public void changeHealthDisplay(String text) {
        HEALTH_DISPLAY.setText(text);
    }

    /**
     * Change the text to be displayed in the damage text box
     * 
     * @param text The new damage text
     */
    public void changeDamageDisplay(String text) {
        DAMAGE_DISPLAY.setText(text);
    }

    /**
     * Change the text to be displayed in the fire rate text box
     * 
     * @param text The new fire rate text
     */
    public void changeFireRateDisplay(String text) {
        FIRE_RATE_DISPLAY.setText(text);
    }

    /**
     * Change the text to be displayed in the speed text box
     * 
     * @param text The new speed text
     */
    public void changeSpeedDisplay(String text) {
        SPEED_DISPLAY.setText(text);
    }

    /**
     * Set whether or not the game-over text box should be visible
     * 
     * @param b Whether or not the game-over text box should be visible
     */
    public void changeGameOverVisibility(boolean b) {
        GAME_OVER_DISPLAY.setVisible(b);
    }

    /**
     * Get the Minimap object being displayed on the screen
     * 
     * @return The Minimap object being displayed on the screen
     */
    public Minimap getMinimap() {
        return minimap;
    }

    /**
     * Set the minimap that is being displayed on the screen
     * 
     * @param map The map of the new minimap to be displayed
     */
    public void setMinimap(Map map) {
        GameLogger.logInfo("Updating minimap to new given minimap.");
        minimap = new Minimap(map);
    }
}
