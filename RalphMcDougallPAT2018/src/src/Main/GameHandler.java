/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.Main;

import src.GUI.GUINewCharacter;
import src.Utility.Tools;
import src.Utility.GameLogger;
import src.Utility.DBBridge;
import src.GUI.GUICharacterSelection;
import src.Player.User;
import src.NPC.NPCNameHandler;
import src.GUI.GameScreen;
import src.Item.ItemTypeHandler;
import src.Map.MapSegmentHandler;
import src.Projectile.ProjectileTypeHandler;
import src.GUI.GUICredits;
import src.GUI.GUIUpgradeCharacter;
import src.GUI.GUIHelp;
import src.GUI.GUICharacterMainMenu;
import src.GUI.GUIActiveQuests;
import src.Quest.QuestTypeHandler;
import src.Tile.TileTypeHandler;
import src.Enemy.EnemyTypeHandler;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.openal.SoundStore;
import org.newdawn.slick.opengl.InternalTextureLoader;

/**
 * This is the main class of the project. This class handles all of the screens
 * and resource loading to save time when the game is played.
 *
 * All resources are loaded at the beginning of the game to make the game load
 * as quickly as possible.
 *
 * This creates a better user experience.
 * 
 * NOTE:    Boss enemies will be added at a later date.
 *          It was determined that they would not add enough to the current
 *          game structure to validate the extra complexity.
 *
 * @author Ralph McDougall 14/4/2018
 */
public class GameHandler {

    // *****************************************************
    // CLASS CONSTANTS
    // *****************************************************
    
    // Enumerate of the possible screens
    public static enum SCREEN_ID {
        ACTIVE_QUESTS, CHARACTER_MAIN_MENU,
        CHARACTER_SELECTION, CREDITS, HELP, NEW_CHARACTER, UPGRADE_CHARACTER,
        GAME
    };
    
    // *****************************************************
    // PRIVATE FIELDS
    // *****************************************************
    
    // All of the screens that the program uses
    private static GUIActiveQuests          ACTIVE_QUESTS_SCREEN;
    private static GUICharacterMainMenu     CHARACTER_MAIN_MENU_SCREEN;
    private static GUICharacterSelection    CHARACTER_SELECTION_SCREEN;
    private static GUICredits               CREDITS_SCREEN;
    private static GUIHelp                  HELP_SCREEN;
    private static GUINewCharacter          NEW_CHARACTER_SCREEN;
    private static GUIUpgradeCharacter      UPGRADE_CHARACTER_SCREEN;
    private static GameScreen               GAME_SCREEN;
    private static AppGameContainer         APPGC;

    // Current screen being shown
    private static SCREEN_ID CURRENT_SCREEN;

    // Whether or not the actual game is running currently
    private static boolean GAME_RUNNING = false;

    // The connection to the database
    private static DBBridge DB;

    // The users statistic handling object
    private static User user;

    // *****************************************************
    // CONSTRUCTOR
    // *****************************************************
    
    /**
     * Constructor with no parameters
     */
    public GameHandler() {
        GameLogger.setFileName(Tools.getCurrentDateTime());
        // Users only need to see errors
        GameLogger.setFlagLevel(GameLogger.LEVEL.ERROR); 

        loadResources();
        initialiseScreens();

        // Initialise the user that is being used
        user = new User();

        // Set the current screen
        CURRENT_SCREEN = SCREEN_ID.CHARACTER_SELECTION;
        changeScreen(SCREEN_ID.CHARACTER_SELECTION);

        GameLogger.logInfo("GameHandler initialised.");
    }
    
    // *****************************************************
    //  PRIVATE METHODS
    // *****************************************************

    /**
     * Load all static data as well as data needed throughout the program
     */
    private static void loadResources() {
        GameLogger.logInfo("Loading static data...");

        loadDBBridge();
        loadTileTypes();
        loadMapSegments();
        loadItemTypes();
        loadNPCNames();
        loadProjectileTypes();
        loadQuestTypes();
        loadEnemyTypes();

        GameLogger.logInfo("Static data loaded.");
    }

    /**
     * Connect to the persistent database and store it in the DB field
     */
    private static void loadDBBridge() {
        
        try {
            DB = new DBBridge("DSPersistent");
        } catch (SQLException ex) {
            GameLogger.logError("Unable to load the database! " + ex);
            JOptionPane.showMessageDialog(null, "The database could not be "
                    + "loaded. View logs for details.");

            // No point in continuing
            exit();
        }
    }

    /**
     * Load the tile types to be used in the game
     */
    private static void loadTileTypes() {
        try {
            TileTypeHandler.load();
        } catch (SQLException ex) {
            GameLogger.logError("Unable to load tile types from database! " + ex);
            JOptionPane.showMessageDialog(null, "Unable to load tile types! "
                    + "View logs for details.");

            // No point in continuing
            exit();
        }
    }
    
    /**
     * Load the map segments to be used in the game
     */
    private static void loadMapSegments() {
        try {
            MapSegmentHandler.load();
        } catch (FileNotFoundException ex) {
            GameLogger.logError("Unable to load map segments from file! " + ex);
            JOptionPane.showMessageDialog(null, "Unable to load map segments! "
                    + "View logs for details.");

            // No point in continuing
            exit();
        }
    }
    
    /**
     * Load the item types to be used in the game
     */
    private static void loadItemTypes() {
        try {
            ItemTypeHandler.load();
        } catch (SQLException ex) {
            GameLogger.logError("Unable to load item types from database! " + ex);
            JOptionPane.showMessageDialog(null, "Unable to load item types! "
                    + "View logs for details.");

            // No point in continuing
            exit();
        }
    }
    
    /**
     * Load the possible NPC names to be used in the game
     */

    private static void loadNPCNames() {
        try {
            NPCNameHandler.load();
        } catch (FileNotFoundException ex) {
            GameLogger.logError("Unable to load NPC names from file! " + ex);
            JOptionPane.showMessageDialog(null, "Unable to load NPC names! "
                    + "View logs for details.");

            // No point in continuing
            exit();
        }
    }

    /**
     * Load the projectile types to be used in the game
     */
    private static void loadProjectileTypes() {
        try {
            ProjectileTypeHandler.load();
        } catch (SQLException ex) {
            GameLogger.logError("Unable to load projectile types from database! " + ex);
            JOptionPane.showMessageDialog(null, "Unable to load projectile types from database! "
                    + "View logs for details.");

            // No point in continuing
            exit();
        }
    }

    /**
     * Load the quest types to be used in the game
     */
    private static void loadQuestTypes() {
        try {
            QuestTypeHandler.load();
        } catch (SQLException ex) {
            GameLogger.logError("Unable to load quest types from database! " + ex);
            JOptionPane.showMessageDialog(null, "Unable to load quest types from database! "
                    + "View logs for details.");

            // No point in continuing
            exit();
        }
    }

    /**
     * Load the enemy types to be used in the game
     */
    private static void loadEnemyTypes() {
        try {
            EnemyTypeHandler.load();
        } catch (SQLException ex) {
            GameLogger.logError("Unable to load enemy types from database! " + ex);
            JOptionPane.showMessageDialog(null, "Unable to load enemy types "
                    + "from database! View logs for details.");

            // No point in continuing
            exit();
        }
    }

    /**
     * Initialise all of the screens so that they don't need to be initialised
     * for the first time when they need to be opened.
     */
    private static void initialiseScreens() {
        GameLogger.logInfo("Initialising screens...");

        ACTIVE_QUESTS_SCREEN = new GUIActiveQuests();
        CHARACTER_MAIN_MENU_SCREEN = new GUICharacterMainMenu();
        CHARACTER_SELECTION_SCREEN = new GUICharacterSelection();
        CREDITS_SCREEN = new GUICredits();
        HELP_SCREEN = new GUIHelp();
        NEW_CHARACTER_SCREEN = new GUINewCharacter();
        UPGRADE_CHARACTER_SCREEN = new GUIUpgradeCharacter();
        GAME_SCREEN = new GameScreen(Constants.CAPTION);

        GameLogger.logInfo("Screens initialised successfully.");
    }
    
    /**
     * Start the actual game where the user can control the character and
     * pick up items.
     * 
     * @throws SlickException 
     */
    
    private static void startMainGame() throws SlickException {
        GameLogger.logInfo("Starting game.");

        // Open the screen where the game runs
        try {
            GameLogger.logInfo("Initialising app game container");
            APPGC = new AppGameContainer(GAME_SCREEN);
            APPGC.setDisplayMode(Constants.SCREEN_WIDTH, 
                    Constants.SCREEN_HEIGHT, false);
            APPGC.setForceExit(false);
            APPGC.setShowFPS(true);
            
            APPGC.start();
        } catch (SlickException ex) {
            GameLogger.logError("Unable to load app game container! " + ex);
            JOptionPane.showMessageDialog(null, "App game container could not "
                    + "be loaded! View logs for details.");
            return;
        }
    }

    /**
     * End the main game and clear system resources.
     * 
     * @throws SlickException 
     */
    
    private static void endGame() throws SlickException {
        GameLogger.logInfo("Closing game.");
        
        // This is necessary for the game to be run multiple times 
        InternalTextureLoader.get().clear();
        SoundStore.get().clear();
        new Graphics() {
            public void resetDefaultFont() {
                DEFAULT_FONT = null;
            }
        }.resetDefaultFont();

        APPGC.exit();
    }
    
    // *****************************************************
    // PUBLIC METHODS
    // *****************************************************
    
    /**
     * Close the current screen and change it to the new screen provided
     *
     * @param newScreen The enum value of the new screen to be displayed
     */
    public static void changeScreen(SCREEN_ID newScreen) {
        GameLogger.logInfo("Changing screen to: " + newScreen);

        // Find the current screen
        JFrame cScreen = null;

        switch (CURRENT_SCREEN) {
            case ACTIVE_QUESTS:
                cScreen = ACTIVE_QUESTS_SCREEN;
                break;
            case CHARACTER_MAIN_MENU:
                cScreen = CHARACTER_MAIN_MENU_SCREEN;
                break;
            case CHARACTER_SELECTION:
                cScreen = CHARACTER_SELECTION_SCREEN;
                break;
            case CREDITS:
                cScreen = CREDITS_SCREEN;
                break;
            case HELP:
                cScreen = HELP_SCREEN;
                break;
            case NEW_CHARACTER:
                cScreen = NEW_CHARACTER_SCREEN;
                break;
            case UPGRADE_CHARACTER:
                cScreen = UPGRADE_CHARACTER_SCREEN;
                break;
            case GAME:
                // The game screen is the exception
                cScreen = null;
                GAME_RUNNING = false;
                break;
            default:
                // The current screen is not one of the valid screens
                GameLogger.logError("Current screen is invalid: "
                        + CURRENT_SCREEN);
                JOptionPane.showMessageDialog(null, "Invalid current screen! "
                        + "View logs for details.");
                // No point in continuing
                exit();
        }

        // Attempt to make the current screen invisible
        if (cScreen != null) {
            // Close the normal JFrame GUIs
            try {
                cScreen.setVisible(false);
                //CHARACTER_MAIN_MENU_SCREEN.setVisible(false);
            } catch (NullPointerException ex) {
                GameLogger.logError("The current screen (" + cScreen
                        + ") could not be made invisible. \n" + ex);
                System.out.println("The current screen could not be made "
                        + "invisible. View logs for details.");
                return;
            }
        } else {
            // Close the game window
            try {
                endGame();
            } catch (SlickException ex) {
                GameLogger.logError("Unable to end game properly! \n" + ex);
                JOptionPane.showMessageDialog(null, "Unable to close game "
                        + "properly! View logs for details.");
            }
        }

        GAME_RUNNING = false;

        // Make the new screen visible
        JFrame nScreen = null;
        switch (newScreen) {
            case ACTIVE_QUESTS:
                ACTIVE_QUESTS_SCREEN.loadQuests();
                nScreen = ACTIVE_QUESTS_SCREEN;
                break;
            case CHARACTER_MAIN_MENU:
                CHARACTER_MAIN_MENU_SCREEN.displayUserData();
                nScreen = CHARACTER_MAIN_MENU_SCREEN;
                break;
            case CHARACTER_SELECTION:
                try {
                    CHARACTER_SELECTION_SCREEN.loadCharacterList();
                } catch (SQLException ex) {
                    GameLogger.logError("Unable to load character "
                            + "list! \n" + ex);
                    JOptionPane.showMessageDialog(null, "Unable to load "
                            + "character list! See details in logs.");

                    // No point in continuing
                    exit();
                }
                nScreen = CHARACTER_SELECTION_SCREEN;
                break;
            case CREDITS:
                nScreen = CREDITS_SCREEN;
                break;
            case HELP:
                nScreen = HELP_SCREEN;
                break;
            case NEW_CHARACTER:
                NEW_CHARACTER_SCREEN.resetVariables();
                NEW_CHARACTER_SCREEN.updateLabels();
                nScreen = NEW_CHARACTER_SCREEN;
                break;
            case UPGRADE_CHARACTER:
                UPGRADE_CHARACTER_SCREEN.getUserData();

                nScreen = UPGRADE_CHARACTER_SCREEN;
                break;
            case GAME:
                // The game screen is the exception
                GAME_RUNNING = true;
                nScreen = null;
                break;
            default:
                // The new screen is not one of the valid screens
                JOptionPane.showMessageDialog(null, "Invalid new screen!");
                break;
        }

        if (!GAME_RUNNING) {
            
            // The new screen isn't the game screen and neither is the previous one
            try {
                
                if (CURRENT_SCREEN != SCREEN_ID.GAME)
                {
                    nScreen.setLocation(cScreen.getX(), cScreen.getY());
                }
                nScreen.setVisible(true);
                
                // Set the new screen as the current screen
                CURRENT_SCREEN = newScreen;
            } catch (NullPointerException ex) {
                GameLogger.logError("The new screen could not be "
                        + "made visible! \n" + ex);
                JOptionPane.showMessageDialog(null, "The new screen "
                        + "could not be made visible! View logs for details.");
                
                // Make old screen visible again
                cScreen.setVisible(true);
            }
        } else {
            // Try to start a new run
            try {
                CURRENT_SCREEN = SCREEN_ID.GAME;
                startMainGame();
            } catch (SlickException ex) {
                GameLogger.logError("Unable to start a new game! \n" + ex);
                JOptionPane.showMessageDialog(null, "Unable to start a new "
                        + "game! View logs for details.");
                cScreen.setVisible(true);
                return;
            }
        }
    }

    /**
     * Exit the whole game and end the game process.
     */
    public static void exit() {

        GameLogger.logInfo("Exiting game.");
        GameLogger.flush();

        System.exit(0);
    }

    /**
     * Get the database used by the game to store persistent data
     * @return The DBBridge object used by the program
     */
    public static DBBridge getPersistentDB() {
        return DB;
    }

    /**
     * Get the user object that is used to store the player's stats
     * @return The current user object
     */
    public static User getUser() {
        return user;
    }
    
    /**
     * Set the user that is currently playing
     * @param u The new user
     */
    public static void setUser(User u) {
        user = u;
    }

    /**
     * The main method that runs at the start of program execution
     * @param args 
     */
    public static void main(String[] args) {
        GameHandler gh = new GameHandler();
    }

}
