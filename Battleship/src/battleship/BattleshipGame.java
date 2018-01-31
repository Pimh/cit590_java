package battleship;

import java.util.Scanner;
import java.lang.Integer;

public class BattleshipGame {
	
	public static void main(String[] args) {
		new BattleshipGame().main();
	}
	
	void main() {
		boolean playAgain = true;
		Scanner scanner = new Scanner(System.in);
		while (playAgain) {
			Ocean ocean = new Ocean();

			// Create initial ship board
			ocean.placeAllShipsRandomly();
			System.out.println("Welcome to the Battleship game!\n");
			ocean.print();
			
			while (!(ocean.isGameOver())) {
				// Accept shots from the user
				int[] firedLoc = acceptUserInput(scanner); 

				// Shoot at the input location
				boolean isHit = ocean.shootAt(firedLoc[0], firedLoc[1]);

				// Display the results
				displayShotResult( isHit, ocean, firedLoc);
				ocean.print();
			}

			// Print the final scores
			System.out.println("Congrats, you have sunk all the ships!");
			System.out.println("The total number of shots is " + ocean.getShotsFired());

			// Ask the user if wish to play again
			playAgain = isPlayAgain(scanner);
		}
		scanner.close();
	}
	
	/**
	 * Accepts input from the player
	 * @param scanner A scanner object for accepting user input.
	 * @return the row number and column number the player wishes to fire.
	 */
	public int[] acceptUserInput(Scanner scanner) {
		System.out.print("Enter the location to be fired (row #, column #): ");
		String input = scanner.nextLine();
		int[] firedLoc;
		try { firedLoc = getFiredLocation(input); }
		catch (NumberFormatException e) { 
			System.out.println("Incorrect input format! "
					+ "Please re-enter the location using the specified format");
			firedLoc = acceptUserInput(scanner);
		}
		catch (ArrayIndexOutOfBoundsException e) { 
			System.out.println("Index out of bound "
					+ "Please re-enter the location between 0-9");
			firedLoc = acceptUserInput(scanner);
		}
		return firedLoc;
	}
	
	/**
	 * Gets the location being fired. 
	 * @param input The string input entered by the player.
	 * @return the row number and column number the player wishes to fire.
	 */
	public int[] getFiredLocation(String input) {
		int row = Integer.parseInt(input.split(",")[0].replace("(","").trim()); 
		int column = Integer.parseInt(input.split(",")[1].replace(")","").trim());
		if ((row < 0) || (row > 9) || (column < 0) || (column > 9)) {
			throw new ArrayIndexOutOfBoundsException();
		}
		int[] location = {row, column};
		return location; 
	}
	
	/**
	 * Displays the shot results.
	 * @param isHit The parameter determining if the shot hits a ship.
	 * @param ocean The ocean being shot. 
	 * @param firedLoc The location in the ocean being fired. 
	 */
	public void displayShotResult(boolean isHit, Ocean ocean, int[]firedLoc) {
		Ship ship = ocean.ships[firedLoc[0]][firedLoc[1]];
		if (isHit) { 
			System.out.println("Nice! it's a hit");
			if (ship.isSunk()) System.out.println("You just sank a " + ship.getShipType());
		} else {
			if (ship.isSunk()) System.out.println("This ship has been sunk.");
			else System.out.println("Too bad! it's a miss");
		}
		System.out.println();
	}
	
	/**
	 * Prompts the user if he/she wishes to play the game again
	 * @param scanner The scanner accepting user's input.
	 * @return true if the player wishes to play again, and false otherwise. 
	 */
	public boolean isPlayAgain(Scanner scanner) {
		System.out.print("Would you like to play again (Y/N): ");
		String input = scanner.nextLine();
		boolean response;
		try {
			if (input.toLowerCase().trim().equals("n")) return false;
			else if (input.toLowerCase().trim().equals("y")) return true;
			else {
				throw new Exception("Incorrect input! Enter your response again.");
			}
		}
		catch (Exception e) {
			response = isPlayAgain(scanner);
		}
		return response; 
	}
		
}
