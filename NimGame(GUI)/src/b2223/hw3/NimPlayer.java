package b2223.hw3;

import java.util.Map.Entry;

/**
 *
 * @author vanting
 */
public abstract class NimPlayer {
    
    // Player's name to be displayed in screen
    protected String name;

    public NimPlayer(String name) {
        this.name = name;
    }
    
    public String getPlayerName() {
        return name;
    }
    
    /**
     * Given the current piles, determine the next move.
     * 
     * Note that the argument 'piles' is a copy. Modifying piles has no effect to the game. 
     *  
     * @param piles     piles[i] is the no. of stones in the i-th pile
     * @return          the first value is the pile no.; the second value is the no. of stones to be removed
     */
    public abstract Entry<Integer, Integer> nextMove(int[] piles);
    
}
