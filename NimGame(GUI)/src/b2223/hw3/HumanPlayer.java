package b2223.hw3;

import java.util.Map;
import java.util.Scanner;

/**
 * Implementation of a human player.
 * @author vanting
 */
public class HumanPlayer extends NimPlayer {

    public HumanPlayer(String name) {
        super(name);
    }

    @Override
    public Map.Entry<Integer, Integer> nextMove(int[] piles) {

        Scanner scanner = new Scanner(System.in);
        int pile, stones;
        do {
            System.out.print(name + "'s turn: choose a pile (1-" + piles.length + ") and number of stones to remove: ");
            pile = scanner.nextInt() - 1; // adjust index to start from 0
            stones = scanner.nextInt();
            if (pile < 0 || pile >= piles.length || stones <= 0 || stones > piles[pile]) {
                System.out.println("Invalid move. Please try again.");
            }
        } while (pile < 0 || pile >= piles.length || stones <= 0 || stones > piles[pile]);
        
        return Map.entry(pile, stones);
    }

}
