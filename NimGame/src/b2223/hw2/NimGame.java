package b2223.hw2;

import java.util.Map.Entry;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * Nim Game.
 * 
 * This Nim game has 3 piles and each pile has a random number of stones between
 * 1 to 7. The two players alternately take turns in removing one or more stones 
 * from a pile. The goal is to force the opponent to remove the last stone.
 * 
 * @author vanting
 */
public class NimGame {

    private final int[] piles;
    private boolean isFirstPlayerTurn;
    private final NimPlayer player1;     // first player
    private final NimPlayer player2;     // second player
    private static final int PILE_SIZE = 3;

    public NimGame(NimPlayer player1, NimPlayer player2) {
        Random random = new Random();
        this.piles = new int[PILE_SIZE];
        for (int i = 0; i < piles.length; i++) {
            this.piles[i] = random.nextInt(7) + 1; // generate random number between 1 and 7
        }
        this.isFirstPlayerTurn = true;
        this.player1 = player1;
        this.player2 = player2;
    }

    public void start() {
        System.out.println("Welcome to Nim Game!");
        System.out.println("The game starts with " + PILE_SIZE + " piles, and each has a random number of stones between 1 to 7.");
        printPiles();
        while (IntStream.of(piles).sum() > 0) {         // sum all piles
            playTurn();
            printPiles();
            isFirstPlayerTurn = !isFirstPlayerTurn;     // switch turn
        }
        System.out.println(isFirstPlayerTurn ? player1.getPlayerName()+ " won!" : player2.getPlayerName() + " won!");    // declare winnner
    }

    private void printPiles() {
        System.out.println("Current Piles:");
        for (int i = 0; i < piles.length; i++) {
            System.out.println("Pile " + (i + 1) + ": " + piles[i] + " stones | " + toBinaryString(piles[i], 3));
        }
    }

    private static String toBinaryString(int n, int length) {
        String binary = Integer.toBinaryString(n);
        int padding = length - binary.length();
        if (padding > 0) {
            return "0".repeat(padding) + binary;
        } else {
            return binary;
        }
    }

    private void playTurn() {
        if (isFirstPlayerTurn) {
            Entry<Integer, Integer> move = player1.nextMove(piles.clone());
            piles[move.getKey()] -= move.getValue();
            System.out.println(player1.getPlayerName() + " removed " + move.getValue() + " stones from pile " + (move.getKey() + 1));
        } else {
            Entry<Integer, Integer> move = player2.nextMove(piles.clone());
            piles[move.getKey()] -= move.getValue();
            System.out.println(player2.getPlayerName() + " removed " + move.getValue() + " stones from pile " + (move.getKey() + 1));
        }
    }

    public static void main(String[] args) {
        // You can play with your own AI player by creating the game with
        // an instance of your AI player class. 
        NimGame game = new NimGame(
                //new HumanPlayer("A"),       // e.g. new PlayerDDDDDDDD("Your Name")
                new Player56611807("CHAU Chun Yau(AI)"),
                new HumanPlayer("B"));
        game.start();   
    }
}
