package battleship;

public class EmptySea extends Ship {

	public EmptySea() {
		this.length = 1;
	}
	
	/**
	 * Determines if the given row and column is empty.
	 * @return false for a ship of type Emptysea.
	 */
	@Override
	public boolean shootAt(int row, int column) {
		return false;
	}
	
	/**
	 * Determines if the ship is sunk.
	 * @return false for a ship of type Emptysea.
	 */
	@Override
	public boolean isSunk() {
		return false;
	}
	
	/**
	 * Determines if the ship is shot.
	 * @return "-" for a ship of type Emptysea if it has been shot.
	 */
	@Override
	public String toString() {
		return "-"; 
	}
	
	/**
	 * Gets length of the ship.
	 * @return the length of the Emptysea ship.
	 */
	@Override
	public int getLength() {
		return this.length;
	}
	
	/**
	 * Gets the type of the ship.
	 * @return "empty sea" for a ship of type Emptysea. 
	 */
	@Override
	public String getShipType() {
		return "empty sea";
	}
}
