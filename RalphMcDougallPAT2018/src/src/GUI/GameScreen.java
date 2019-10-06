/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.GUI;

import src.Map.PathFindingGrid;
import src.Map.Map;
import src.Enemy.EnemyTypeHandler;
import src.Enemy.Enemy;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import src.Utility.Camera;
import src.GameObjects.EntityHandler;
import src.Main.Constants;
import src.Main.GameHandler;
import src.Utility.GameLogger;
import src.UI.HUDLayer;
import src.Player.PlayerCharacter;
import src.Utility.SpriteSheet;
import src.Utility.GameClock;
import src.Utility.Tools;

/**
 * The main class for the main game. This handles the initialisation of all
 * game objects and then handles all updates and rendering to the screen.
 * 
 * @author Ralph McDougall 23/4/2018
 */
public class GameScreen extends BasicGame {
    // *****************************************************
    // PRIVATE FIELDS
    // *****************************************************
    
    // Sprite sheet of the tiles
    private static SpriteSheet TILE_SHEET;
    private static SpriteSheet CHARACTER_SHEET;
    private static SpriteSheet ITEM_SHEET;
    private static SpriteSheet PROJECTILE_SHEET;
    
    // Keep track of how long ago the last update cycle was
    private static long lastUpdate = 0;
    
    // The number of updates the game should perform every second
    private static final int UPS = 120;
    private static int updatesCompleted = 0;
    
    // The map object
    private static Map map;
    
    // The camera object
    private static Camera camera;
    
    // The player character
    private static PlayerCharacter player;
    
    // The current input of the game
    private static Input currInput = null;
    
    // The variables controlling the timing of the game over
    private static boolean gameOver = false;
    private static long gameOverStart = 0;
    private static final int GAME_OVER_TIME = 2000; // Time in milliseconds
    
    // The path finding grid used for enemies to find the player
    private static PathFindingGrid pfg;
    
    // The path-finding algorithm should not run every update cycle, so
    // these variables make sure it does not happen too often
    private static int pathFindingCount = 0;
    private static final int PATH_FINDING_FREQUENCY = 30;
    
    // Controls when enemies may spawn
    private static long lastSpawnTime;     // Time in milliseconds
    private static long minTimeBetweenSpawns;   // Time in milliseconds
    
    // The Heads-Up-Display that is to be drawn onto the screen
    private static HUDLayer hudLayer;
    
    // *****************************************************
    // CONTRUCTOR
    // *****************************************************
    
    /**
     * Initialise the new game screen
     * 
     * @param title The caption that appears at the top of the window
     */
    public GameScreen(String title) {
        super(title);
    }
    
    // *****************************************************
    // PRIVATE METHODS
    // *****************************************************
    
    /**
     * Attempt to spawn a new enemy on the map if enough time has passed
     * 
     * Place the enemy in a random position if sufficient time has passed.
     */
    private static void spawnEnemy()
    {
        if(GameClock.getMilliTime() - lastSpawnTime > minTimeBetweenSpawns)
        {
            // Spawn a new enemy
            
            int x, y = 0;
            
            x = Tools.randomInt(0, map.getWidth());
            y = Tools.randomInt(0, map.getHeight());
            
            // Keep moving the coordinate of the enemy until a walkable
            // tile has been found. The enemy should also spawn off screen
            // So that it does not suddenly spawn in front of the player
            while (!map.getTileWalkable(x, y) || camera.onScreen(x, y))
            {
                x = Tools.randomInt(0, map.getWidth());
                y = Tools.randomInt(0, map.getHeight());
            }
            
            // The new enemy
            Enemy e = new Enemy(x, y, EnemyTypeHandler.getRandomType());
            
            // Record that an enemy was spawned
            lastSpawnTime = GameClock.getMilliTime();
        }
    }
    
    /**
     * Perform the path-finding algorithm so that the enemies can track
     * the player.
     * 
     * This should only be performed a few times per second as it is not
     * necessary for it to be every update cycle. If it were every update cycle,
     * it would slow the game down unnecessarily/
     */
    private static void performPathFinding()
    {
        // Only perform the path finding algorithm every 12 updates
        if (pathFindingCount == PATH_FINDING_FREQUENCY)
        {
            // Path find to the player
            pfg.pathFind(player);
            pathFindingCount = 0;
        }
        else
        {
            // Another update cycle has passed where an enemy was not spawned
            ++pathFindingCount;
        }
    }
    
    /**
     * Initialise all of the sprite sheets used in the game
     */
    private static void loadSpriteSheets()
    {
        GameLogger.logInfo("Initialising sprite sheets...");
        try {
            // Initialise the tile sprite sheet
            TILE_SHEET = new SpriteSheet("TileSpriteSheet", Constants.TILE_SCREEN_SIZE);
            
            // Initialise the character sprite sheet
            CHARACTER_SHEET = new SpriteSheet("CharacterSpriteSheet", Constants.TILE_SCREEN_SIZE);
            
            // Initialise the item sprite sheet
            ITEM_SHEET = new SpriteSheet("ItemSpriteSheet", Constants.TILE_SCREEN_SIZE);
            
            // Initialise the projectile sprite sheet
            PROJECTILE_SHEET = new SpriteSheet("ProjectileSpriteSheet", Constants.TILE_SCREEN_SIZE);
            
            
        } catch (SlickException | RuntimeException ex) {
            // An error occurred
            GameLogger.logError("Unable to load sprite sheet! " + ex);
            
            JOptionPane.showMessageDialog(null,
                    "Sprite sheets could not be loaded! "
                            + "View logs for details.");
            
            // No point in continuing
            GameHandler.exit();
        }
        GameLogger.logInfo("Sprite sheets initialised successfully.");
    }

    /**
     * Load the Heads-Up-Display that the user can see
     */
    private static void loadHUD()
    {
        GameLogger.logInfo("Loading HUD...");
        hudLayer = new HUDLayer(map);
    }
    
    /**
     * Check if sufficient time has passed for another update cycle to happen.
     * This prevents the game from updating too many times per second which
     * could slow down the game and cause the FPS to drop as there is more time
     * between renders.
     * 
     * @return Get whether or not the update cycle may proceed
     */
    private static boolean canUpdate()
    {
        return (GameClock.getNanoTime() - lastUpdate >= 1000000000 / UPS);
    }
    
    /**
     * Update the text in the Heads-Up-Display
     */
    private static void updateHUD()
    {
        // If the character is overlapping a POI, prompt them to interact with it
        if (player.touchesPOI())
        {
            hudLayer.changeMessageBoxDisplay("Press [E] to interact.");
            hudLayer.changeMessageBoxVisibility(true);
        }
        else
        {
            // Attempt to remove the message box at all times
            // This stops the screen from becoming too cluttered
            hudLayer.changeMessageBoxVisibility(false);
        }
        
        // Update the HUD Element text
        hudLayer.changeDamageDisplay("Damage: " + player.getDamage());
        hudLayer.changeFireRateDisplay("Fire Rate: " + player.getFireRate());
        hudLayer.changeHealthDisplay("Health: " + player.getHealth());
        hudLayer.changeSpeedDisplay("Speed: " + player.getSpeed());
        
        // Update the mini-map player coordinates
        hudLayer.getMinimap().setPlayerCoords(player.getX(), player.getY());
    }
    
    /**
     * Log how much of the system resources is currently being used by
     * the game. This is useful for looking for reasons for problems
     * occurring in the logs.
     * 
     * The system usage not be constantly displayed. Preferably, only 20
     * seconds or so.
     */
    private static void logSystemUsage()
    {
        if (updatesCompleted % (UPS * 20) == 0) // Log system resource usage every 20 seconds
        { 
            GameLogger.logInfo("*********************************");
            GameLogger.logInfo("Available processors (cores): " + Runtime.getRuntime().availableProcessors());
            GameLogger.logInfo("Memory use (MB): " + Tools.getNumMB(Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) + " / " + Tools.getNumMB(Runtime.getRuntime().maxMemory()));
            GameLogger.logInfo("Number of entities: " + EntityHandler.getNumEntities());
            GameLogger.logInfo("*********************************");
        }
    }
    
    // *****************************************************
    // PUBLIC METHODS
    // *****************************************************
    
    /**
     * Initialise all of the game resources and game objects.
     * This method runs automatically when the game is initialised
     * because of the Slick2D library.
     * 
     * @param gc The GameContainer that this game is contained in
     * @throws SlickException Slick2D couldn't start the game.
     */
    @Override
    public void init(GameContainer gc) throws SlickException {
        // Initialise the new game in this method
        GameLogger.logInfo("Initialising new game...");
        
        // Load the sprite sheets
        loadSpriteSheets();
        
        // Setup the entity handler
        EntityHandler.setupEntityHandler();
        
        try {
            // Create a new map
            resetMap();
        } catch (SQLException ex) {
            GameLogger.logError("Error while loading map data from database! \n" + ex);
            JOptionPane.showMessageDialog(null, "Unable to get all map data from database! (Probably NPC quest construction). See logs for details.");
            
            // Go back to the previous screen
            exit();
        }
        
        
        // Initialise the player
        player = new PlayerCharacter(map.getPlayerStartX(), map.getPlayerStartY() );
        
        
        // Init path finding grid
        pfg = new PathFindingGrid(map.getWidth(), map.getHeight(), 1);
        pfg.pathFind(player);
        
        // Create a new camera
        camera = new Camera(player,
                Constants.SCREEN_WIDTH / Constants.TILE_SCREEN_SIZE + 1, 
                Constants.SCREEN_HEIGHT / Constants.TILE_SCREEN_SIZE + 1);
        
        // Load the HUD
        loadHUD();
        
        GameLogger.logInfo("New game initialised successfully.");
        
    }

    /**
     * The method that updates all of the systems in the game.
     * This should run about 120 times every second.
     * 
     * Thanks to Slick2D, this runs every tick and then the render method
     * runs.
     * 
     * @param gc The GameContainer containing the current game
     * @param i The time since the last update (I think)
     * @throws SlickException Slick2D could not update the game systems
     */
    @Override
    public void update(GameContainer gc, int i) throws SlickException {
        
        // Indicate when this update cycle would start
        GameClock.updateTime();
        
        if (!canUpdate())
        {
            // Not enough time has passed to update again
            return;
        }
        
        // Record that this update cycle started at this tim
        lastUpdate = GameClock.getNanoTime();
        
        if (gameOver)
        {
            // The game is over, freeze the screen for 2 seconds to show the player the game is over
            if (GameClock.getMilliTime() - gameOverStart <= GAME_OVER_TIME)
            {
                return;
            }
            else
            {
                exit();
            }
        }
        
        // Update the current input object
        Input input = gc.getInput();
        currInput = input;
        
        // Update all non-tile entities. Tiles do not need to be updated
        EntityHandler.updateEntities();
        
        // Move the camera so that the player is in the center of the screen
        camera.reallign(player);
        
        // Perform path-finding algorithm
        performPathFinding();
        
        // Add an enemy if possible
        spawnEnemy();
        
        // Update the Heads-Up-Display that the user sees
        updateHUD();
        
        logSystemUsage();
        
        // Increase the number of update cycles that have been completed
        updatesCompleted++;
    }
    
    /**
     * Renders all of the graphics onto the screen. This runs straight after
     * the update method runs thanks to Slick2D
     * 
     * @param gc The GameContainer that contains the current game
     * @param grphcs The Graphics object where the images are drawn onto
     * @throws SlickException Slick2D could not render as something went wrong
     */
    @Override
    public void render(GameContainer gc, Graphics grphcs) throws SlickException {
        
        // Render tiles
        map.drawMap(grphcs, camera);
        
        // Draw the map item
        if (!map.itemPickedUp())
        {
            // The player hasn't picked up the item yet, so draw it
            map.getItem().draw(grphcs, camera);
        }
        
        // Render entities
        EntityHandler.drawEntities(grphcs, camera);
        
        // Render HUD
        hudLayer.display(grphcs);
    }
    
    /**
     * Exit the main game and go back to the previous screen
     */
    public static void exit()
    {
        GameLogger.logInfo("Exiting main game.");
        // Go back to the character main menu screen
        GameHandler.changeScreen(GameHandler.SCREEN_ID.CHARACTER_MAIN_MENU);
    }
    
    /**
     * Get the sub image that is located at a given position on the tile
     * sprite sheet.
     * 
     * @param x The x-coordinate of the image to find
     * @param y The y-coordinate of the image to find
     * @return The image at the desired coordinate
     */
    public static Image getTileSprite(int x, int y)
    {
        return TILE_SHEET.getImage(x, y);
    }
    
    /**
     * Get the sub image that is located at a given position on the item
     * sprite sheet.
     * 
     * @param x The x-coordinate of the image to find
     * @param y The y-coordinate of the image to find
     * @return The image at the desired coordinate
     */
    public static Image getItemSprite(int x, int y)
    {
        return ITEM_SHEET.getImage(x, y);
    }
    
    /**
     * Get the sub image that is located at a given position on the projectile
     * sprite sheet.
     * 
     * @param x The x-coordinate of the image to find
     * @param y The y-coordinate of the image to find
     * @return The image at the desired coordinate
     */
    public static Image getProjectileSprite(int x, int y)
    {
        return PROJECTILE_SHEET.getImage(x, y);
    }
    
    /**
     * Get the sub image that is located at a given position on the character
     * sprite sheet.
     * 
     * @param x The x-coordinate of the image to find
     * @param y The y-coordinate of the image to find
     * @return The image at the desired coordinate
     */
    public static Image getCharacterSprite(int x, int y)
    {
        return CHARACTER_SHEET.getImage(x, y);
    }
    
    /**
     * Get the PathFindingGrid object used to help enemies track the player
     * 
     * @return The PathFindingGrid used by the game
     */
    public static PathFindingGrid getPathFindingGrid()
    {
        return pfg;
    }
    
    /**
     * Get the Input object that the game uses to track user inputs
     * 
     * @return Input object used by the game
     */
    public static Input getInput()
    {
        return currInput;
    }
    
    /**
     * Get the number of updates performed by the game every second
     * 
     * @return The number of updates performed per second
     */
    public static int getUPS()
    {
        return UPS;
    }
    
    /**
     * Start the Game-Over process that shows the user a "You died" message
     */
    public static void startGameOver()
    {
        // Start the game-over process
        gameOver = true;
        gameOverStart = GameClock.getMilliTime();
        hudLayer.changeGameOverVisibility(true);
        
        EntityHandler.removeAllEnemies();
        GameLogger.logInfo("Starting game over screen.");
    }
    
    /**
     * Increase the spawn rate by a fixed rate to make it more difficult
     * for the player as they proceed to more and more levels.
     */
    public static void increaseDifficulty()
    {
        minTimeBetweenSpawns *= 0.75;
    }
    
    /**
     * Reset all of the variables used in controlling the game as a new
     * map is to be made.
     * 
     * @throws SQLException SQL could not retrieve some data from the database
     */
    public static void resetMap() throws SQLException
    {
        // Reset variables
        gameOver = false;
        gameOverStart = 0;
    
        pathFindingCount = 0;
    
        lastSpawnTime = 0;
        minTimeBetweenSpawns = 2000;
        
        EntityHandler.removeAllEnemies();
        
        // Reset the map
        loadNewMap();
        try
        {
            hudLayer.setMinimap(map);
        }
        catch (NullPointerException ex)
        {
            GameLogger.logWarning("HUDLayer not yet initialised, minimap could not be set to current map.");
        }
    }
    
    /**
     * Get the map that the player is playing on in the game.
     * 
     * @return The Map object being used by the game.
     */
    public static Map getMap()
    {
        return map;
    }
    
    /**
     * Load a new map
     * 
     * @throws SQLException SQL could not load some data from the database
     */
    public static void loadNewMap() throws SQLException
    {
        if (map != null)
        {
            // The old map's NPC is still present so first remove it from 
            // the game
            EntityHandler.removeCharacter(map.getNPC());
        }
        // Make a new map
        map = new Map();
    }
    
    /**
     * Get the HUDLayer object used to display information to the user.
     * 
     * @return The HUDLayer object being used by the game.
     */
    public static HUDLayer getHUDLayer()
    {
        return hudLayer;
    }
    
}
