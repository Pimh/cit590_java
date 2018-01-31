package battleship;

import java.util.Random;

/**
 * @author PimH
 *
 */
/**
 * @author PimH
 *
 */
public class Ocean {
	protected Ship[][] ships = new Ship[10][10];
	int shotsFired;
	int hitCount; 
	private boolean[][] firedBoard = initiateFiredBoard();
	
	public Ocean() {
		for (int i = 0; i < ships.length; i++) 
			for (int j = 0; j < ships[i].length; j++) 
				ships[i][j] = new EmptySea(); 
		
		this.shotsFired = 0;
		this.hitCount = 0; 
	}
	
	/**
	 * Places ships in this ocean
	 */
	public void placeAllShipsRandomly() {
		
		// Place battleship
		Ship bShip = new Battleship();
		createShip(bShip, this); 
		
		// Place cruisers
		for (int i = 0; i < 2; i++) {
			Ship cruiser = new Cruiser();
			createShip(cruiser, this); 
		}
		
		// Place destroyers
		for (int i = 0; i < 3; i++) {
			Ship destroyer = new Destroyer();
			createShip(destroyer, this); 
		}
		
		// Place submarines
		for (int i = 0; i < 4; i++) {
			Ship submarine = new Submarine();
			createShip(submarine, this); 
		}
	}
	
	/**
	 * Creates a ship to be placed in this ocean
	 * @param ship The ship to be created.
	 * @param ocean The ocean in which the ship will be placed.
	 */
	private void createShip(Ship ship, Ocean ocean) {
		boolean done = false;
		while (!done) {
			int row = generateRandomLocation()[0];
			int column = generateRandomLocation()[1];
			boolean horizontal = generateRandomDirection();
			if (ship.okToPlaceShipAt(row, column, horizontal, ocean)) {
				ship.placeShipAt(row, column, horizontal, ocean);
				done = true;
			}
		}
	}
	
	/**
	 * Generated a random location within the ocean
	 * @return The row and column numbers being randoming generated.
	 */
	private int[] generateRandomLocation() {
		Random random = new Random();
		int row = random.nextInt(10);
		int column = random.nextInt(10);
		
		int[] loc = {row, column};
		return loc; 
	}
	
	/**
	 * Randomly generates the orientation of the ship.
	 * @return true if the orientation generated is horizontal, and false otherwise.
	 */
	private boolean generateRandomDirection() {
		Random random = new Random();
		int dir = random.nextInt(2);
		if (dir == 0) return true;
		else return false;
	}
	
	/**
	 * Determines if the given location is already occupied.
	 * @param row The row number of the location.
	 * @param column The column number of the location.
	 * @return true if the location is occupied, and false otherwise.
	 */
	public boolean isOccupied(int row, int column) {
		if (ships[row][column] instanceof EmptySea) return false;
		else return true; 
	}
	
	/**
	 * Fires a shot at the given location.
	 * @param row The row number of the location being shot.
	 * @param column The column number of the location being shot.
	 * @return true if the shot hits any ships, and false otherwise. 
	 */
	public boolean shootAt(int row, int column) {
		this.shotsFired++;
		updateBoard(this.firedBoard, row, column);
		if (isOccupied(row, column)) {
			if (!(ships[row][column].isSunk())) this.hitCount++; 
			return ships[row][column].shootAt(row, column);
		} else return false; 
	}
	
	/**
	 * Gets the total number of fires that has been shot.
	 * @return the total number of fires that has been shot.
	 */
	public int getShotsFired() {
		return this.shotsFired;
	}
	
	/**
	 * Gets the total number of shots that are on target.
	 * @return the total number of shots on the ships.
	 */
	public int getHitCount() {
		return this.hitCount;
	}
	
	/**
	 * Determines if the game is over.
	 * @return true if all the ships are sunk, and false otherwise.
	 */
	public boolean isGameOver() {
		for (int i = 0; i < ships.length; i++) {
			for (int j = 0; j < ships[i].length; j++) {
				if (!(ships[i][j] instanceof EmptySea)) {
					if (!(ships[i][j].isSunk())) return false;
				}
			}
		}
		return true; 
	}
	
	/**
	 * Gets the array of ships in this ocean.
	 * @return the ship array of this ocean.
	 */
	public Ship[][] getShipArray() {
		return this.ships; 
	}
	
	/**
	 * Prints a map showing the locations that have been shot, hit, and contained 
	 * sunk ships.
	 */
	public void print() {
		for (int i = 0; i < 11; i++) {
			for (int j = 0; j < 11; j++) {
				if (i == 0) {
					if (j == 0) System.out.print("  ");
					else System.out.print(j-1 + " ");
				} else if (j == 0) {
					System.out.print(i-1 + " ");
				} else {
					// TO BE COMPLETED
					if (!(firedBoard[i-1][j-1])) {
						System.out.print(". ");
					} else {
						String str = ships[i-1][j-1].toString();
						System.out.print(str + " ");
					}
				}
			}
			System.out.println("");
		}
	}
	
	/**
	 * Creates an initial firing map determining which locations have been shot.
	 * @return a boolean 2D array determining which locations have been shot.
	 */
	private boolean[][] initiateFiredBoard() {
		boolean[][] board = new boolean[10][10];
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				board[i][j] = false;
			}
		}
		return board; 
	}
	
	/**
	 * Updates the firing map after each shot.
	 * @param board The firing map.
	 * @param row The row number being shot.
	 * @param column the column number being shot. 
	 */
	private void updateBoard(boolean[][] board, int row, int column) {
		board[row][column] = true;
	}
}
