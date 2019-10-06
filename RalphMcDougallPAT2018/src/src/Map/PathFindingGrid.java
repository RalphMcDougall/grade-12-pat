/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.Map;

import java.util.LinkedList;
import java.util.Queue;
import src.Main.Constants.DIRECTION;
import src.GameObjects.Entity;
import src.GUI.GameScreen;
import src.Main.Constants;
import src.Utility.GameLogger;

/**
 * This represents the grid of directions for characters to move to 
 * find the player character
 * 
 * @author Ralph McDougall 1/7/2018
 */
public class PathFindingGrid {
    
    // *****************************************************
    // PRIVATE FIELDS
    // *****************************************************
    
    // The grid of nodes
    private PFNode[][] grid;
    
    // How many times the characters should move before covering one block on the map
    private int scale;
    private double moveDist;
    
    // *****************************************************
    // CONSTRUCTOR
    // *****************************************************
    
    /**
     * Initialise a new path finding grid with a given width, height and scale
     * 
     * @param mapWidth The width of the grid
     * @param mapHeight The height of the grid
     * @param moveScale The scale of the grid
     */
    public PathFindingGrid(int mapWidth, int mapHeight, int moveScale)
    {
        GameLogger.logInfo("Initialising path finding grid...");
        
        scale = moveScale;
        moveDist = 1.0 / scale;
        
        grid = new PFNode[mapHeight * scale][mapWidth * scale];
        
        // Fill up the grid with default nodes
        for(int y = 0; y < grid.length; ++y)
        {
            for (int x = 0; x < grid[0].length; ++x)
            {
                grid[y][x] = new PFNode(x, y);
            }
        }
        
        GameLogger.logInfo("Path finding grid initialised.");
    }
    
    // *****************************************************
    // PUBLIC METHODS
    // *****************************************************
    
    /**
     * Get the node that is at the center of a given entity
     * 
     * @param e The entity to find the node for
     * @return The node corresponding with the entity's position
     */
    public PFNode getCorrespondingNode(Entity e)
    {
        // Find the node at the centre of the entity (center y and center x)
        return grid[(int) ( (e.getY() + e.getSize() / 2) * scale)][(int) ( (e.getX() + e.getSize() / 2) * scale)];
    }
    
    /**
     * Start the path finding algorithm from the given entity. Perform
     * a Breath First Search (BFS) from the starting position and update
     * the distance traveled as you go along. Keep track of the direction
     * taken to move onto the node, the opposite of that direction is the
     * shortest direction to the player's character.
     * 
     * @param e The entity for the enemies to track
     */
    public void pathFind(Entity e)
    {
        
        // First reset the grid
        resetGrid();
        
        PFNode start = getCorrespondingNode(e);
        
        // Perform a breadth-first search to construct the shortest distance 
        // to every point in the grid
        // Dijkstra isn't necessary as it is an unweighted grid
        
        // This queue keeps track of which nodes must be visited next
        Queue<PFNode> q = new LinkedList<PFNode>();
        
        // Initialise the starting node and add it to the queue
        start.setDist(0);
        start.setInLine(true);
        
        q.add(start);
        
        // The offsets of the neighbours of each block
        int[][] offsets = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
        
        // Keep finding shorter passes until they have all been checked
        while (q.size() != 0)
        {
            PFNode curr = q.poll();
            
            if (curr.getDist() >= Constants.MAX_PATH_FINDING)
            {
                // Don't look any further since the path is long enough
                continue;
            }
            
            for (int i = 0; i < offsets.length; ++i)
            {
                // Get the coordinates of the neighbouring node
                int x = curr.getX() + offsets[i][0];
                int y = curr.getY() + offsets[i][1];
                
                if (!GameScreen.getMap().getTileWalkable(x / scale, y / scale))
                {
                    // The tile that is being moved onto is invalid so ignore
                    // this neighbour
                    continue;
                }
                
                if (grid[y][x].getDist() > curr.getDist() + moveDist )
                {
                    // The path to this neighbour is shorter than the previous
                    // best path, so add it to the queue
                    q.add(grid[y][x]);
                    grid[y][x].setDist(curr.getDist() + moveDist);
                    
                    // Get the new direction
                    switch (i)
                    {
                        case 0:
                            grid[y][x].setDir(DIRECTION.DOWN);
                            break;
                        case 1:
                            grid[y][x].setDir(DIRECTION.RIGHT);
                            break;
                        case 2:
                            grid[y][x].setDir(DIRECTION.UP);
                            break;
                        case 3:
                            grid[y][x].setDir(DIRECTION.LEFT);
                            break;
                        default:
                            GameLogger.logWarning("Unknown path finding direction.");
                            break;
                    }
                    
                    if ( (curr.getDist() == 0) || (grid[y][x].getDir() == curr.getDir()) )
                    {
                        // If the current node is in line, then so is this neighbour
                        grid[y][x].setInLine(curr.isInLine());
                    }
                }
            }
        }
        
    }
    
    /**
     * Reset all of the nodes in the grid
     */
    public void resetGrid()
    {
        for (int y = 0; y < grid.length; ++y)
        {
            for (int x = 0; x < grid[0].length; ++x)
            {
                grid[y][x].reset();
            }
        }
    }
    
    /**
     * Get the distance to move to get from one node to an adjacent node
     * 
     * @return The distance to move to get to an adjacent node
     */
    public double getMoveDist()
    {
        return moveDist;
    } 
}
