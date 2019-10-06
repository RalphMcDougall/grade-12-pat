/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.Map;

import src.Quest.QuestHandler;
import src.Tile.Tile;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import org.newdawn.slick.Graphics;
import src.Utility.Camera;
import src.Main.Constants;
import src.GameObjects.EntityHandler;
import src.Utility.GameLogger;
import src.Item.Item;
import src.Item.ItemTypeHandler;
import src.NPC.NPC;
import src.Utility.Tools;

/**
 * Stores all details about the map, including what tile is in each position
 * and randomises the tile placement each time a new map gets generated.
 * 
 * @author Ralph McDougall 26/4/2018
 */
public class Map {
    
    // *****************************************************
    // PRIVATE FIELDS
    // *****************************************************
    
    // The 2D tileGrid of which map tile segments in the whole map are walkable
    private ArrayList< ArrayList<Boolean> > segmentWalkableGrid;
    
    // The 2D tileGrid of all of the segments of the whole map
    private ArrayList< ArrayList<MapSegment> > segmentGrid;
    
    // Dimensions of the tilegrid
    private int width;
    private int height;
    
    // The 2D tileGrid of each tile
    private  ArrayList< ArrayList<Tile> > tileGrid;
    
    // Coordinates of important locations on the segment tileGrid
    private int startX;
    private int startY;
    private int endX;
    private int endY;
    private int itemX;
    private int itemY;
    private int npcX;
    private int npcY;
    
    // The item that can be picked up
    private Item item;
    private boolean itemPickedUp = false;
    
    // The NPC to be on the map
    private NPC npc;
    
    // *****************************************************
    // CONSTRUCTOR
    // *****************************************************
    
    /**
     * Setup a new random map.
     * 
     * @throws SQLException SQL could not load some data from the database
     */
    public Map() throws SQLException
    {
        setupMap();
    }
    
    // *****************************************************
    // PRIVATE METHODS
    // *****************************************************
    
    /**
     * Run all of the methods to setup all of the details of the map
     * 
     * @throws SQLException SQL could not load some data from the database
     */
    private void setupMap() throws SQLException
    {
        GameLogger.logInfo("Setting up new map.");
        
        // The dimensions of the tile tileGrid are the dimensions of the
        // map tile segment tileGrid multiplied by the size of each map tile
        // segment
        width = Constants.MAP_WIDTH * Constants.TILE_SEGMENT_WIDTH;
        height = Constants.MAP_HEIGHT * Constants.TILE_SEGMENT_WIDTH;
        
        // Setup the map segments
        setupSegments();
        
        // Setup the Points of Interest of the map
        setupPOIs();
        
        // Setup the map segment tileGrid
        setupMapSegmentGrid();
        
        // Setup the map tileGrid
        setupTiles();
        
        // Put the item and NPC in the map
        placeItem();
        placeNPC();
        
        GameLogger.logInfo("Map setup completed.");
    }
    
    /**
     * Setup the map tile segment tileGrid
     * 
     * @throws SQLException SQL could not retrieve some data from the database
     */
    private void setupSegments() throws SQLException
    {
        GameLogger.logInfo("Creating tile segment grid for map.");
        // Initialise the segment tileGrid
        createBlankWalkableSegmentGrid();
        
        getStartCoordinates();
        
        // The start location must be walkable
        segmentWalkableGrid.get(startY).set(startX, true);
        
        // Fill out the rest of the segment tileGrid
        constructMaze();
        
    }
    
    /**
     * Create a new, blank, segment tileGrid that will be filled up in the
 maze construction algorithm.
     */
    private void createBlankWalkableSegmentGrid()
    {
        segmentWalkableGrid = new ArrayList<>();
        GameLogger.logInfo("Creating new blank segment grid.");
        for (int y = 0; y < Constants.MAP_HEIGHT; ++y)
        {
            ArrayList<Boolean> newRow = new ArrayList<Boolean>();
            for (int x = 0; x < Constants.MAP_WIDTH; ++x)
            {
                // Initialise all tile segments to "false" by default
                newRow.add(false);
            }
            segmentWalkableGrid.add(newRow);
        }
    }
    
    /**
     * Choose the starting map tile segment
     */
    private void getStartCoordinates()
    {
        // The starting coordinates should not be on the edge of the map
        // so lee-way of 1 segment is given on either side
        startX = Tools.randomInt(1, Constants.MAP_WIDTH - 1);
        startY = Tools.randomInt(1, Constants.MAP_HEIGHT - 1);
    }
    
    /**
     * Setup the tile type tileGrid based off of the map tile segment tileGrid
     */
    private void setupTiles()
    {
        GameLogger.logInfo("Setting up map tiles.");
        
        tileGrid = new ArrayList< ArrayList<Tile> >();
        // Setup all of the tiles IDs
        
        for(int y = 0; y < height; ++y)
        {
            ArrayList<Tile> line = new ArrayList<Tile>();
            for (int x = 0; x < width; ++x)
            {
                // Get the segment coordinate that corresponds with the
                // current (x, y) coordinate on the map
                int segmentX = x / Constants.TILE_SEGMENT_WIDTH;
                int segmentY = y / Constants.TILE_SEGMENT_WIDTH;
                
                // Get the tile type ID of the tile in the map tile segment
                // corresponding with this segment
                int id = segmentGrid.get(segmentY).get(segmentX).getTileID(x % 5, y % 5);
                
                // Special tile segments need special tiles
                if (segmentX == endX && segmentY == endY)
                {
                    // End segment has tiles with ID = 3
                    id = 3;
                }
                if (segmentX == itemX && segmentY == itemY)
                {
                    // The item segment has tiles with ID = 4
                    id = 4;
                }
                if (segmentX == npcX && segmentY == npcY)
                {
                    // The NPC segment has tiles with ID = 5
                    id = 5;
                }
                
                line.add(new Tile(x, y, id));
            }
            tileGrid.add(line);
        }
        GameLogger.logInfo("Map tile setup completed.");
        
    }
    
    /**
     * Construct the maze of map tile segments
     * 
     * @throws SQLException SQL could not retrieve some data from the database
     */
    private void constructMaze() throws SQLException
    {
        GameLogger.logInfo("Starting maze construction.");
        
        /**
         * Nodes contain a random score as well as a x and y coordinate
         * The random score allows the nodes to be handled in a random order.
         * This creates random maps every time this algorithm is done.
         * Note: The priority queue moves the node with the lowest random
         *       score to the front
         */
        
        // A new comparison function that allows for nodes with a lower score
        // to appear first in the queue
        Comparator customComparator = new Comparator<ArrayList<Integer> >() {

        @Override
        public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
            return o1.get(0) < o2.get(0) ? -1 : 1;
        }
    };
        
        // The queue of all nodes that could potentially be handled
        PriorityQueue< ArrayList<Integer> > constructionQueue = new PriorityQueue<>(customComparator);
        
        // Add all of the neighbours of the starting location
        addNeighbours(startX, startY, constructionQueue);
        
        // Do this while more nodes need to be handled
        while (constructionQueue.size() != 0)
        {
            // The node currently on the front of the queue
            ArrayList<Integer> node = constructionQueue.poll();
            
            // Get the values associated with the node on the front
            // of the queue
            int score = node.get(0);
            int nodeX = node.get(1);
            int nodeY = node.get(2);
            
            // A big score is very unfavourable so we ignore that node
            if (score <= 1000000 && nodeValid(nodeX, nodeY))
            {
                // The node is valid and should be included in the segment tileGrid
                segmentWalkableGrid.get(nodeY).set(nodeX, true);
                
                // Add the neighbours of the neighbouring node to the queue
                addNeighbours(nodeX, nodeY, constructionQueue);
            }
            
        }
        
        // Get the number of dead-ends in the current maze
        ArrayList< ArrayList<Integer> > deadEnds = getDeadEnds();
        GameLogger.logInfo("Number of dead-ends: " + deadEnds.size());
        
        if (deadEnds.size() < 4)
        {
            // There aren't enough dead-ends in the map to guarentee that
            // all points of interest can be placed. Reconstruct the maze.
            // The item, end and NPC segments are necessary and the starting
            // segment might be a dead-end, so 4 dead-ends are necessary
            GameLogger.logInfo("Too few dead-ends created in maze construction. Reconstructing maze.");
            setupMap();
        }
        
    }
    
    /**
     * Check whether or not a given map tile segment node is a valid location.
     * 
     * @param x The x-coordinate of the node to check
     * @param y The y-coordinate of the node to check
     * @return Whether or not (x, y) is valid
     */
    private boolean nodeValid(int x, int y)
    {        
        // Add some cushioning around the border of the map
        if (x <= 0 || x >= (Constants.MAP_WIDTH - 1))
        {
            return false;
        }
        if (y <= 0 || y >= (Constants.MAP_HEIGHT - 1))
        {
            return false;
        }
        
        // If the tile is already included, no point including it again
        if (segmentWalkableGrid.get(y).get(x) == true)
        {
            return false;
        }
        
        // Make sure the tile only has 1 valid neighbour to keep it a tree
        if (getNeighbourCount(x, y) > 1)
        {
            return false;
        }
        
        return true;
        
    }
    
    /**
     * Add all of the neighbours of a given coordinate to the queue
     * used for the maze construction.
     * 
     * @param x The x-coordinate of the node whose neighbours should be added
     * @param y The y-coordinate of the node whose neighbours should be added
     * @param queue The priority queue used for the construction of the maze
     */
    private void addNeighbours(int x, int y, PriorityQueue queue)
    {
        // Add all of the neighbours of (x, y) to the queue
        ArrayList<Integer> up =     getNewNodeList(x, y - 1);
        ArrayList<Integer> left =   getNewNodeList(x - 1, y);
        ArrayList<Integer> down =   getNewNodeList(x, y + 1);
        ArrayList<Integer> right =  getNewNodeList(x + 1, y);
        
        queue.add(up);
        queue.add(left);
        queue.add(down);
        queue.add(right);
    }
    
    /**
     * Get a list containing 3 numbers, the score for a coordinate as well
     * as the coordinate itself.
     * 
     * @param x The x-coordinate of the coordinate given
     * @param y The y-coordinate of the coordinate given
     * @return A list of 3 values, the score, the x coordinate and the y coordinate
     */
    private ArrayList<Integer> getNewNodeList(int x, int y)
    {
        ArrayList<Integer> arr = new ArrayList<Integer>();
        
        // The number of nodes surrounding this one that are walkable
        int surrCount = getSurroundingCount(x, y);
        // How good of a choice this node is (lower is better)
        // The favourability function used is supposed to favour lower surrounding counts
        int favourability = (int) Math.pow(surrCount, 2) + surrCount / 2;
        
        // Construct the list
        arr.add(Tools.randomInt(0, 1000000) * favourability);
        arr.add(x);
        arr.add(y);
        
        return arr;
    }
    
    /**
     * Get the number of walkable segments surrounding a given segment
     * 
     * @param x The x-coordinate of the given coordinate
     * @param y The y-coordinate of the given coordinate
     * @return The number of walkable segments surrounding the given one
     */
    private int getNeighbourCount(int x, int y)
    {
        // Return the number of valid neighbours of this coordinate
        int counter = 0;
        
        if (x > 0)
        {
            // Check if the left neighbour is valid
            if (segmentWalkableGrid.get(y).get(x - 1))
            {
                counter++;
            }
        }
        if (x <= (Constants.MAP_WIDTH - 1))
        {
            // Check if the right neighbour is valid
            if (segmentWalkableGrid.get(y).get(x + 1))
            {
                counter++;
            }
        }
        
        if (y > 0)
        {
            // Check if the top neighbour is valid
            if (segmentWalkableGrid.get(y - 1).get(x))
            {
                counter++;
            }
        }
        if (y <= (Constants.MAP_HEIGHT - 1))
        {
            // Check if the bottom neighbour is valid
            if (segmentWalkableGrid.get(y + 1).get(x))
            {
                counter++;
            }
        }
        
        return counter;
    }
    
    /**
     * Get the number of open blocks in the 3x3 square centered on this
     * coordinate, excluding this square.
     * 
     * @param x The x-coordinate of the desired node
     * @param y The y-coordinate of the desired node
     * @return The number of open surrounding segments
     */
    private int getSurroundingCount(int x, int y)
    {
        // Return the number of open blocks in the 3x3 square centred on this square, excluding this square
        int counter = 0;
        
        for (int relY = -1; relY <= 1; ++relY)
        {
            for (int relX = -1; relX <= 1; ++relX)
            {
                // Make sure that the neighbour being checked is still on
                // the map
                if ((x + relX) == -1)
                {
                    continue;
                }
                if ((x + relX) == Constants.MAP_WIDTH)
                {
                    continue;
                }
                if ((y + relY) == -1)
                {
                    continue;
                }
                if ((y + relY) == Constants.MAP_HEIGHT)
                {
                    continue;
                }
                // Check if the surrounding segment is walkable
                if (segmentWalkableGrid.get(y + relY).get(x + relX))
                {
                    ++counter;
                }
            }
        }
        
        return counter;
    }
    
    /**
     * Find the coordinates of the location to place the NPC and the item
     * for this map.
     * The POIs should be placed at dead-end segments.
     */
    private void setupPOIs()
    {
        GameLogger.logInfo("Starting to setup Points of Interest.");
        
        // Get all of the dead-ends
        ArrayList< ArrayList<Integer> > deadEnds = getDeadEnds();
        
        // The index of the dead-end to use for the end
        int endInd = Tools.randomInt(0, deadEnds.size());
        
        // The index of the dead-end to use for the item
        int itemInd = Tools.randomInt(0, deadEnds.size());
        while (itemInd == endInd)
        { // Make sure it hasn't been used yet
            itemInd = Tools.randomInt(0, deadEnds.size());
        }
        
        // The index of the dead-end to use for the NPC
        int npcInd = Tools.randomInt(0, deadEnds.size());
        while (npcInd == itemInd || npcInd == endInd)
        { // Make sure it hasn't been used yet
            npcInd = Tools.randomInt(0, deadEnds.size());
        }
        
        // Get the coordinates of the points of interest
        endX = deadEnds.get(endInd).get(0);
        endY = deadEnds.get(endInd).get(1);
        
        itemX = deadEnds.get(itemInd).get(0);
        itemY = deadEnds.get(itemInd).get(1);
        
        npcX = deadEnds.get(npcInd).get(0);
        npcY = deadEnds.get(npcInd).get(1);
        GameLogger.logInfo("Points of Interest setup completed.");
    }
    
    /**
     * Check if the map tile segment given can be visited
     * 
     * @param x The x-coordinate of the map tile segment to check
     * @param y The y-coordinate of the map tile segment to check
     * @param visited 2D array of which segments have been visited
     * @return Whether or not the given map tile segment can be visited
     */
    private boolean canExplore(int x, int y, Boolean[][] visited)
    {
        return (!visited[y][x] && segmentWalkableGrid.get(y).get(x));
    }
    
    /**
     * Place a new item in the map
     */
    private void placeItem()
    {
        itemPickedUp = false;
        item = new Item(itemX * Constants.TILE_SEGMENT_WIDTH + (Constants.TILE_SEGMENT_WIDTH / 2), itemY * Constants.TILE_SEGMENT_WIDTH + (Constants.TILE_SEGMENT_WIDTH / 2), ItemTypeHandler.getRandomItemID());
    }
    
    /**
     * Place a new NPC in the map
     * 
     * @throws SQLException SQL could not retrieve data from the database
     */
    private void placeNPC() throws SQLException
    {
        if (npc != null)
        {
            // Remove the NPC from the entity handler list before adding a new one
            EntityHandler.removeCharacter(npc);
        }
        // Place the new NPC
        npc = new NPC(npcX * Constants.TILE_SEGMENT_WIDTH + (Constants.TILE_SEGMENT_WIDTH / 2), npcY * Constants.TILE_SEGMENT_WIDTH + (Constants.TILE_SEGMENT_WIDTH / 2));
    }
    
    /**
     * Fill up the map segment tileGrid using the maze that was constructed to
 determine which map tile segments are walkable and then place an
 appropriate map tile segment there
     */
    private void setupMapSegmentGrid() {
        segmentGrid = new ArrayList<>();
        
        ArrayList<MapSegment> line;
        for (int y = 0; y < Constants.MAP_HEIGHT; ++y)
        {
            line = new ArrayList<MapSegment>();
            for (int x = 0; x < Constants.MAP_WIDTH; ++x)
            {
                MapSegment segment = MapSegmentHandler.getRandomSegment();
                while (segment.getWalkable() != segmentWalkableGrid.get(y).get(x))
                {
                    // Keep looking for a map tile segment until one with the
                    // correct walkable state is found
                    segment = MapSegmentHandler.getRandomSegment();
                }
                // Add the new segment to the tileGrid
                line.add(segment);
            }
            segmentGrid.add(line);
        }
        
    }
    
    /**
     * Get the dead-ends of the maze that was constructed
     * 
     * @return A list of dead-ends coordinates
     */
    private ArrayList< ArrayList< Integer > > getDeadEnds()
    {
        // Get the dead ends of the tileGrid
        
        GameLogger.logInfo("Starting to get dead ends.");
        
        // The final result
        ArrayList< ArrayList< Integer > > deadEnds = new ArrayList<>();
        
        // Which nodes have been visited
        Boolean[][] visited = new Boolean[Constants.MAP_HEIGHT][Constants.MAP_WIDTH];
        
        // Initialise all nodes to unvisited
        for(int y = 0; y < Constants.MAP_HEIGHT; ++y)
        {
            for (int x = 0; x < Constants.MAP_WIDTH; ++x)
            {
                visited[y][x] = false;
            }
        }
        
        // The queue of nodes to check
        LinkedList<ArrayList<Integer>> q = new LinkedList<>();
        ArrayList<Integer> start = new ArrayList<>();
        start.add(startX);
        start.add(startY);
        q.add(start);
        
        // Breadth First Search all of the nodes to find all dead-ends
        while (q.size() != 0)
        {
            // Get the node that is currently on the front of the queue
            ArrayList<Integer> currNode = q.poll();
            
            int x = currNode.get(0);
            int y = currNode.get(1);
            
            if (getNeighbourCount(x, y) == 1)
            {
                // There is only one neighbouring cell, so it's a dead-end
                deadEnds.add(currNode);
            }
            else
            {
                // Add its walkable neighbours to the queue
                
                if (canExplore(x, y - 1, visited))
                {
                    // UP
                    ArrayList<Integer> arr = new ArrayList<>();
                    arr.add(x);
                    arr.add(y - 1);
                    q.add(arr);
                }
                if (canExplore(x, y + 1, visited))
                {
                    // DOWN
                    ArrayList<Integer> arr = new ArrayList<>();
                    arr.add(x);
                    arr.add(y + 1);
                    q.add(arr);
                }
                if (canExplore(x - 1, y, visited))
                {
                    // LEFT
                    ArrayList<Integer> arr = new ArrayList<>();
                    arr.add(x - 1);
                    arr.add(y);
                    q.add(arr);
                }
                if (canExplore(x + 1, y, visited))
                {
                    // RIGHT
                    ArrayList<Integer> arr = new ArrayList<>();
                    arr.add(x + 1);
                    arr.add(y);
                    q.add(arr);
                }
            }
            
            visited[y][x] = true;
        }
        
        GameLogger.logInfo("All dead ends found.");
        
        return deadEnds;
    }
    
    // *****************************************************
    // PUBLIC METHODS
    // *****************************************************
    
    /**
     * Draw the tiles of the map onto the screen
     * 
     * @param g The Graphics object where the map should be drawn onto
     * @param c The Camera object to use to determine where on the screen each
     *          tile is
     */
    public void drawMap(Graphics g, Camera c)
    {
        // Only draw the tiles that are actually within the camera limits
        for (int y = (int) c.getWorldY() - 1; y < (int) c.getWorldY() + c.getWorldHeight() + 1; ++y)
        {
            for (int x = (int) c.getWorldX() - 1; x < (int) c.getWorldX() + c.getWorldWidth() + 1; ++x)
            {
                if (y < 0 || x < 0) // The tileGrid doesn't extend that far
                {
                    continue;
                }
                if (x >= width || y >= height) // The tileGrid doesn't go that far
                {
                    continue;
                }
                tileGrid.get(y).get(x).draw(g, c);
            }
        }
    }
    
    /**
     * Get whether or not a given coordinate is a walkable tile
     * 
     * @param x The x-coordinate of the tile to check
     * @param y The y-coordinate of the tile to check
     * @return Whether or not the tile is walkable
     */
    public boolean getTileWalkable(int x, int y)
    {
        return tileGrid.get(y).get(x).walkable();
    }
    
    /**
     * Get the x-coordinate for where on the tile grid the player starts
     */
    public int getPlayerStartX()
    {
        return startX * Constants.TILE_SEGMENT_WIDTH + (Constants.TILE_SEGMENT_WIDTH - 1) / 2;
    }
    
    /**
     * Get the y-coordinate for where on the tile grid the player starts
     */
    public int getPlayerStartY()
    {
        // Get the y-coordinate for where on the tile tileGrid the player starts
        return startY * Constants.TILE_SEGMENT_WIDTH + (Constants.TILE_SEGMENT_WIDTH - 1) / 2;
    }
    
    /**
     * Get the coordinate for where on the tile grid the center of the end segment is
     */
    public ArrayList<Integer> getEndCoord()
    {
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(endX * Constants.TILE_SEGMENT_WIDTH + (Constants.TILE_SEGMENT_WIDTH / 2));
        arr.add(endY * Constants.TILE_SEGMENT_WIDTH + (Constants.TILE_SEGMENT_WIDTH / 2));
        return arr;
    }
    
    /**
     * Get the coordinate for where on the tile grid the item goes
     */
    public ArrayList<Integer> getItemCoord()
    {
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(itemX * Constants.TILE_SEGMENT_WIDTH + (Constants.TILE_SEGMENT_WIDTH / 2));
        arr.add(itemY * Constants.TILE_SEGMENT_WIDTH + (Constants.TILE_SEGMENT_WIDTH / 2));
        return arr;
    }
    
    /**
     * Get the coordinate for where on the tile grid the NPC goes
     */
    public ArrayList<Integer> getNPCCoord()
    {
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(npcX * Constants.TILE_SEGMENT_WIDTH + (Constants.TILE_SEGMENT_WIDTH / 2));
        arr.add(npcY * Constants.TILE_SEGMENT_WIDTH + (Constants.TILE_SEGMENT_WIDTH / 2));
        return arr;
    }
    
    /**
     * Get the coordinate for where on the tile grid the player starts
     */
    public ArrayList<Integer> getStartCoord()
    {
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(startX * Constants.TILE_SEGMENT_WIDTH + (Constants.TILE_SEGMENT_WIDTH / 2));
        arr.add(startY * Constants.TILE_SEGMENT_WIDTH + (Constants.TILE_SEGMENT_WIDTH / 2));
        return arr;
    }
    
    /**
     * Get whether or not the item has been picked up by the player
     * 
     * @return Whether or not the item was picked up
     */
    public boolean itemPickedUp()
    {
        return itemPickedUp;
    }
    
    /**
     * Get the item that has been placed in the map.
     * 
     * @return The item in the map
     */
    public Item getItem()
    {
        return item;
    }
    
    /**
     * The player has picked up the item. The event should be flagged with
     * the quest handler.
     */
    public void pickUpItem()
    {
        // Don't show the item anymore
        itemPickedUp = true;
        
        // Log the event of picking up an item
        QuestHandler.logEvent("find", item.getName());
    }
    
    /**
     * Get the NPC that is in the map.
     * 
     * @return The map's NPC
     */
    public NPC getNPC()
    {
        return npc;
    }
    
    /**
     * Get the width of the map in number of tiles
     * 
     * @return Width of the map in tiles
     */
    public int getWidth()
    {
        return width;
    }
    
    /**
     * Get the height of the map in number of tiles
     * 
     * @return Height of the map in tiles
     */
    public int getHeight()
    {
        return height;
    }
    
}
