/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.Quest;

/**
 * The target of a quest as well as the maximum count and the maximum reward
 * 
 * @author Ralph McDougall 26/6/2018
 */
public class QuestTarget {
    // *****************************************************
    // PRIVATE FIELDS
    // *****************************************************
    
    // The name of the target of a quest
    private String target;
    
    // The maximum number of times the target should be found
    private int maxCount;
    
    // The maximum reward for the completion of a quest with this target
    private int maxReward;

    // *****************************************************
    // CONSTRUCTOR
    // *****************************************************
    
    /**
     * Initialise a new quest target 
     * @param target The name of the target
     * @param maxCount The maximum number of times the target can be found
     * @param maxReward The maximum reward for completing a quest with this target
     */
    public QuestTarget(String target, int maxCount, int maxReward) {
        this.target = target;
        this.maxCount = maxCount;
        this.maxReward = maxReward;
    }

    // *****************************************************
    // PUBLIC METHODS
    // *****************************************************
    
    /**
     * Get the name of the target
     * 
     * @return The name of the target
     */
    public String getTarget() {
        return target;
    }

    /**
     * Get the maximum count of the target being found
     * 
     * @return The maximum count of the target
     */
    public int getMaxCount() {
        return maxCount;
    }

    /**
     * The maximum reward for completing a quest with this target
     * 
     * @return The reward
     */
    public int getMaxReward() {
        return maxReward;
    }
}
