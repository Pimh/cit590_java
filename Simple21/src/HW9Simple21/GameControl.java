package HW9Simple21;

import java.util.Scanner;
import java.util.Arrays;
import java.util.Random;

/**
 * This is a simplified version of a common card game, "21".<p>
 * In this game, the dealer deals two "cards" to each player,
 * one hidden, so that only the player who gets the card knows
 * what it is, and one face up, so that everyone can see it.
 * (Actually, what the other players see is the <i>total</i>
 * of each other player's visible cards, not the individual cards.)<p>
 *
 * The players then take turns requesting cards, trying to get
 * as close to 21 as possible, but not going over 21. These cards
 * will be visible to all players.  A player may pass (ask for no
 * more cards). Once a player has passed, he or she cannot later
 * ask for another card. When all players have passed, the
 * game ends.<p>
 *
 * The winner is the player who has come closest to 21 without
 * exceeding it. In the case of a tie, or if everyone goes over
 * 21, no one wins.<p>
 *
 * In this program, there are some number of computer players
 * and one human player. The game is only played once. 
 * 
 * @author David Matuszek
 * @author Pimkhuan(Pim) Hannanta-anan
 * @version 0
 */
public class GameControl {
    
    /** All the Player objects, including the Human player.
     * The number of players is set here; other places in the
     * program should use <code>players.length</code>. */
    Player[] players = new Player[4];
    
    /** passed[i] == true indicates that players[i] has passed. */
    Boolean[] passed = new Boolean[] {false, false, false, false};
    
    /** Used for getting input from the user. */
    Scanner scanner = new Scanner(System.in);
    
    /** A random number generator. */
    Random random = new Random();
      
    /**
     * The main method just creates a GameControl object and calls
     * its <code>run</code> method.
     * 
     * @param args Not used.
     */
    public static void main(String args[]) {    
        // Students: your code goes here. 
    	new GameControl().run();
    }
    
    /**
     * Prints a welcome method, then calls methods to perform each
     * of the following actions:
     * <ol>
     *   <li>Create the Players (one of them a Human),</li>
     *   <li>Deal the initial two cards to each player,</li>
     *   <li>Control the play of the game, and</li>
     *   <li>Print the final results.</li>
     * </ol>
     */
    void run() { 
        // Students: your code goes here.
    	System.out.println("Welcome to Simple21 game!");
    	
    	// Create the players
    	createPlayers(); 
    	
    	// Deal the initial two cards
    	deal();
    	
    	// Control the play of the game
    	controlPlay();
    	
    	// Print the final results
    	printResults(); 
    	
    }
    
    /**
     * Asks the human player for a name, and then creates a Human
     * object and all the other Player objects, saving them in the
     * <code>players</code> array. (Names of the other players may
     * be hardwired; don't ask the user for them.)
     */
    void createPlayers() {
        System.out.print("What is your name?  ");
        String humansName = scanner.next();
        // Students: your code goes here.
        Player human = new Human(humansName);
        players[0] = human; 
        
        for (int i = 1; i < players.length ; i++) {
    		Player player = new Player("Player" + i);
    		players[i] = player; 
    	}
    }
    
    /**
     * Deals the initial two cards (one hidden, one not hidden)
     * to each player.
     */
    void deal() { 
        // Students: your code goes here.
    	for (Player player : players) {
    		player.takeHiddenCard(nextCard());
    		player.takeVisibleCard(nextCard());
    	}
    }
    
    /**
     * Returns a random "card", represented by a integer between
     * 1 and 10, inclusive. The odds of returning a 10 are four
     * times as likely as any other value (because in an actual
     * deck of cards, 10, Jack, Queen, and King all count as 10).<br />
     * <b>Note:</b> The java.util package contains a <code>Random</code>
     * class, which is perfect for generating random numbers.
     *
     * @return a random integer in the range 1..10.
     */
    int nextCard() { 
        // Students: your code goes here.
    	int card = random.nextInt(13) + 1;
    	if (card > 10 && card < 14) card = 10;
   	 	return card;
    }

    /**
     * Gives each player in turn a chance to take a card, until all
     * players have passed. Prints a message when a player passes.
     * Once a player has passed, that player is not given another
     * chance to take a card.<br />
     * <b>Note:</b> The global array <code>passed</code> is used to
     * keep track of which players have passed.
     */
    void controlPlay() { 
        // Students: your code goes here.
    	while (Arrays.asList(passed).contains(false)) {
    		System.out.println();
    		for (int i = 0; i < players.length; i++) {
    			if (passed[i] == false) {
    				passed[i] = !players[i].offerCard(players);
    				if (passed[i] == false) {
    					int card = nextCard();
    					players[i].takeVisibleCard(card);
    				} else {
    					System.out.println(players[i].name + " passes.");
    				}
    			}
    		}
    	}
    }
    
    /**
     * Prints a summary at the end of the game, saying how many
     * points each player had, and who won.
     */
    void printResults() { 
        // Students: your code goes here.
    	System.out.println("\nGame Over!");
    	// Print players' total scores
    	for (Player player : players) {
    		int score = player.getScore();
    		System.out.println(player.name + " gets " + score + " points.");
    	}
    	
    	// Print who won
    	int winIdx = findWinningPlayer(); 
    	if (winIdx == -1) {
    		System.out.println("Nobody wins.");
    	} else {
    		String name = players[winIdx].name;
    		int winScore = players[winIdx].getScore();
    		System.out.println(name + " wins with " + winScore + " points!" );
    	}

    }

    /**
     * Determines who won the game, and returns it as an index into
     * the players array.
     * 
     * @return The index of the winner, or -1 if nobody won.
     */
    int findWinningPlayer() { 
        // Students: your code goes here.
    	int maxScore = 0;
    	int maxIdx = -1;
    	for (int i = 0; i < players.length; i++) {
    		if (players[i].getScore() >= maxScore && players[i].getScore() <= 21) {
    			if (players[i].getScore() == maxScore) {
    				maxIdx = -1; 
    			} else {
    				maxScore = players[i].getScore();
    				maxIdx = i; 
    			}    			
    		} 
    	}
    	return maxIdx; 
    }
}
